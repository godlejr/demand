package com.demand.site.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.File;
import com.demand.site.common.entity.Report;
import com.demand.site.common.entity.ReportFile;
import com.demand.site.common.entity.User;
import com.demand.site.common.flag.FileFlag;
import com.demand.site.common.flag.ReportFlag;
import com.demand.site.common.flag.UserFlag;
import com.demand.site.common.util.AwsS3Util;
import com.demand.site.repository.file.FileRepository;
import com.demand.site.repository.report.ReportRepository;
import com.demand.site.repository.reportfile.ReportFileRepository;
import com.demand.site.service.ReportService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private ReportFileRepository reportFileRepository;

	@Autowired
	AwsS3Util awsS3Util;

	@Value("#{aws['aws.s3.domain']}")
	private String S3_DOMAIN_URL;

	@Value("#{aws['aws.s3.file.report.url']}")
	private String REPORT_FILE_URL;

	@Override
	public void saveReport(User user, boolean isNotification, String title, String content, MultipartFile[] files)
			throws Exception {

		Report report = new Report();
		report.setUser(user);
		report.setTitle(title);
		report.setContent(content);

		if (isNotification) {
			report.setType(ReportFlag.NOTIFICATION_TYPE);
		}

		reportRepository.saveAndFlush(report);

		for (int i = 0; i < files.length; i++) {

			MultipartFile multipartFile = files[i];
			String originalName = multipartFile.getOriginalFilename();
			String ext = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
			long size = multipartFile.getSize();
			InputStream inputStream = multipartFile.getInputStream();

			String storageName = awsS3Util.getStorageFileNameByUploadingFileAndFileNameToTheLocation(REPORT_FILE_URL,
					inputStream, originalName);

			File file = new File();
			file.setType(FileFlag.REPORT_FILE_TYPE);
			file.setStorageName(storageName);
			file.setOriginalName(originalName);
			file.setExt(ext);
			file.setSize((int) size);

			fileRepository.saveAndFlush(file);

			ReportFile reportFile = new ReportFile();
			reportFile.setReport(report);
			reportFile.setFile(file);

			reportFileRepository.saveAndFlush(reportFile);

		}

	}

	@Override
	public Page<Report> getReportsBySearchAndPageable(String search, Pageable pageable) throws Exception {

		return reportRepository.findAllBySearchAndPageable(search, pageable);
	}

	@Override
	public Map<String, Report> getPrevPresentNextReportMapsById(long id) throws Exception {

		Map<String, Report> reportMap = new HashMap<String, Report>();

		Report report = reportRepository.findById(id);
		int hits = report.getHits();
		report.setHits(hits + 1);

		reportMap.put("report", reportRepository.saveAndFlush(report));

		Report prevReport = reportRepository.findFirstByIdLessThanOrderByIdDesc(id);
		Report nextReport = reportRepository.findFirstByIdGreaterThanOrderByIdAsc(id);

		reportMap.put("prevReport", prevReport);
		reportMap.put("nextReport", nextReport);
		return reportMap;
	}

	@Override
	public void deleteReport(User user, long id) throws Exception {

		Report report = reportRepository.findById(id);
		User reportUser = report.getUser();
		long reportUserId = reportUser.getId();
		long userId = user.getId();
		int userLevel = user.getLevel();

		if (reportUserId == userId || userLevel == UserFlag.ADMIN_LEVEL) {

			List<ReportFile> reportFiles = report.getReportFiles();
			for (ReportFile reportFile : reportFiles) {
				File file = reportFile.getFile();
				String storageName = file.getStorageName();
				fileRepository.delete(file);
				awsS3Util.deleteFileByLocationAndStorageFileName(REPORT_FILE_URL, storageName);
			}

			reportRepository.delete(report);

		}

	}

	@Override
	public Report getReportById(long id) throws Exception {
		return reportRepository.findById(id);
	}

	@Override
	public void editReport(User user, long id, boolean isNotification, String title, String content,
			String[] deletedFileStorageNames, MultipartFile[] files) throws Exception {
		Report report = reportRepository.findById(id);
		report.setTitle(title);
		report.setContent(content);
		if (isNotification) {
			report.setType(ReportFlag.NOTIFICATION_TYPE);
		}else{
			report.setType(ReportFlag.NORMAL_TYPE);
		}

		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile multipartFile = files[i];
				String originalName = multipartFile.getOriginalFilename();
				String ext = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
				long size = multipartFile.getSize();
				InputStream inputStream = multipartFile.getInputStream();

				String storageName = awsS3Util.getStorageFileNameByUploadingFileAndFileNameToTheLocation(
						REPORT_FILE_URL, inputStream, originalName);

				File file = new File();
				file.setType(FileFlag.REPORT_FILE_TYPE);
				file.setStorageName(storageName);
				file.setOriginalName(originalName);
				file.setExt(ext);
				file.setSize((int) size);

				fileRepository.saveAndFlush(file);

				ReportFile reportFile = new ReportFile();
				reportFile.setReport(report);
				reportFile.setFile(file);

				reportFileRepository.saveAndFlush(reportFile);

			}
		}

		if (deletedFileStorageNames != null) {
			for (int i = 0; i < deletedFileStorageNames.length; i++) {
				String storageName = deletedFileStorageNames[i];
				awsS3Util.deleteFileByLocationAndStorageFileName(REPORT_FILE_URL, storageName);
				fileRepository.deleteByStorageNameAndType(storageName, FileFlag.REPORT_FILE_TYPE);
			}
		}
	}

}

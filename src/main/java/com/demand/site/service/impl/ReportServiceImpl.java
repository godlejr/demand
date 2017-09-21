package com.demand.site.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.File;
import com.demand.site.common.entity.Report;
import com.demand.site.common.entity.ReportFile;
import com.demand.site.common.entity.User;
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
			report.setType(1);
		}

		reportRepository.saveAndFlush(report);

		for (int i = 0; i < files.length; i++) {

			MultipartFile multipartFile = files[i];
			String originalName = multipartFile.getOriginalFilename();
			String ext = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
			long size = multipartFile.getSize();
			InputStream inputStream = multipartFile.getInputStream();

			String storageName = awsS3Util.selectEncryptedFileNameByUploadingFileAndFileNameToTheLocation(
					REPORT_FILE_URL, inputStream, originalName);

			File file = new File();
			file.setType(4);
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

}

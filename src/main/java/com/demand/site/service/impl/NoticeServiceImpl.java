package com.demand.site.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.File;
import com.demand.site.common.entity.Notice;
import com.demand.site.common.entity.NoticeCategory;
import com.demand.site.common.entity.NoticeFile;
import com.demand.site.common.entity.Report;
import com.demand.site.common.entity.ReportFile;
import com.demand.site.common.entity.User;
import com.demand.site.common.flag.FileFlag;
import com.demand.site.common.flag.NoticeFlag;
import com.demand.site.common.flag.ReportFlag;
import com.demand.site.common.flag.UserFlag;
import com.demand.site.common.util.AwsS3Util;
import com.demand.site.repository.file.FileRepository;
import com.demand.site.repository.notice.NoticeRepository;
import com.demand.site.repository.noticefile.NoticeFileRepository;
import com.demand.site.service.NoticeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private NoticeFileRepository noticeFileRepositorsy;

	@Autowired
	private AwsS3Util awsS3Util;

	@Value("#{aws['aws.s3.file.notice.url']}")
	private String NOTICE_FILE_URL;

	@Override
	public void saveNotice(User user, boolean isNotification, long noticeCategoryId, String title, String content,
			MultipartFile[] files) throws Exception {
		Notice notice = new Notice();
		notice.setUser(user);
		notice.setTitle(title);
		notice.setContent(content);

		if (isNotification) {
			notice.setType(NoticeFlag.NOTIFICATION_TYPE);
		}

		NoticeCategory noticeCategory = new NoticeCategory();
		noticeCategory.setId(noticeCategoryId);

		notice.setNoticeCategory(noticeCategory);

		noticeRepository.saveAndFlush(notice);

		for (int i = 0; i < files.length; i++) {

			MultipartFile multipartFile = files[i];
			String originalName = multipartFile.getOriginalFilename();
			String ext = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
			long size = multipartFile.getSize();
			InputStream inputStream = multipartFile.getInputStream();

			String storageName = awsS3Util.getStorageFileNameByUploadingFileAndFileNameToTheLocation(NOTICE_FILE_URL,
					inputStream, originalName);

			File file = new File();
			file.setType(FileFlag.NOTICE_FILE_TYPE);
			file.setStorageName(storageName);
			file.setOriginalName(originalName);
			file.setExt(ext);
			file.setSize((int) size);

			fileRepository.saveAndFlush(file);

			NoticeFile noticeFile = new NoticeFile();
			noticeFile.setNotice(notice);
			noticeFile.setFile(file);

			noticeFileRepositorsy.saveAndFlush(noticeFile);

		}
	}

	@Override
	public Page<Notice> getNoticesByNoticeCategoryIdAndSearchAndPageable(long noticeCategoryId, String search,
			Pageable pageable) throws Exception {
		return noticeRepository.findAllByNoticeCategoryIdAndSearchAndPageable(noticeCategoryId, search, pageable);
	}

	@Override
	public Notice getNoticeById(long id) throws Exception {
		return noticeRepository.findOne(id);
	}

	@Override
	public void editNotice(User user, long id, boolean isNotification, long noticeCategoryId, String title,
			String content, String[] deletedFileStorageNames, MultipartFile[] files) throws Exception {
		Notice notice = noticeRepository.findOne(id);
		notice.setTitle(title);
		notice.setContent(content);
		if (isNotification) {
			notice.setType(NoticeFlag.NOTIFICATION_TYPE);
		}else{
			notice.setType(NoticeFlag.NORMAL_TYPE);
		}
		
		NoticeCategory noticeCategory = new NoticeCategory();
		noticeCategory.setId(noticeCategoryId);

		notice.setNoticeCategory(noticeCategory);

		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile multipartFile = files[i];
				String originalName = multipartFile.getOriginalFilename();
				String ext = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
				long size = multipartFile.getSize();
				InputStream inputStream = multipartFile.getInputStream();

				String storageName = awsS3Util.getStorageFileNameByUploadingFileAndFileNameToTheLocation(
						NOTICE_FILE_URL, inputStream, originalName);

				File file = new File();
				file.setType(FileFlag.NOTICE_FILE_TYPE);
				file.setStorageName(storageName);
				file.setOriginalName(originalName);
				file.setExt(ext);
				file.setSize((int) size);

				fileRepository.saveAndFlush(file);

				NoticeFile noticeFile = new NoticeFile();
				noticeFile.setNotice(notice);
				noticeFile.setFile(file);

				noticeFileRepositorsy.saveAndFlush(noticeFile);
			}
		}

		if (deletedFileStorageNames != null) {
			for (int i = 0; i < deletedFileStorageNames.length; i++) {
				String storageName = deletedFileStorageNames[i];
				awsS3Util.deleteFileByLocationAndStorageFileName(NOTICE_FILE_URL, storageName);
				fileRepository.deleteByStorageNameAndType(storageName, FileFlag.NOTICE_FILE_TYPE);
			}
		}
	}

	@Override
	public void deleteNotice(User user, long id) throws Exception {
		Notice notice = noticeRepository.findOne(id);
		int userLevel = user.getLevel();

		if (userLevel == UserFlag.EMPLOYEE_LEVEL || userLevel == UserFlag.ADMIN_LEVEL) {

			List<NoticeFile> noticeFiles = notice.getNoticeFiles();
			for (NoticeFile noticeFile : noticeFiles) {
				File file = noticeFile.getFile();
				String storageName = file.getStorageName();
				fileRepository.delete(file);
				awsS3Util.deleteFileByLocationAndStorageFileName(NOTICE_FILE_URL, storageName);
			}
			noticeRepository.delete(notice);

		}
	}

}

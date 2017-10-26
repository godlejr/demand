package com.demand.site.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.File;
import com.demand.site.common.entity.Notice;
import com.demand.site.common.entity.NoticeCategory;
import com.demand.site.common.entity.NoticeFile;
import com.demand.site.common.entity.User;
import com.demand.site.common.flag.FileFlag;
import com.demand.site.common.flag.NoticeFlag;
import com.demand.site.common.util.AwsS3Util;
import com.demand.site.repository.file.FileRepository;
import com.demand.site.repository.notice.NoticeRepository;
import com.demand.site.repository.noticefile.NoticeFileRepository;
import com.demand.site.service.NoticeService;

@Service
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
	
}

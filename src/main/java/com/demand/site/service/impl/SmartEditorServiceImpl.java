package com.demand.site.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demand.site.common.flag.FileFlag;
import com.demand.site.common.util.AwsS3Util;
import com.demand.site.service.SmartEditorService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SmartEditorServiceImpl implements SmartEditorService {

	@Autowired
	AwsS3Util awsS3Util;

	@Value("#{aws['aws.s3.domain']}")
	private String S3_DOMAIN_URL;

	@Value("#{aws['aws.s3.image.report.url']}")
	private String REPORT_IMAGE_URL;

	@Value("#{aws['aws.s3.video.report.url']}")
	private String REPORT_VIDEO_URL;

	@Value("#{aws['aws.s3.image.news.url']}")
	private String NEWS_IMAGE_URL;

	@Value("#{aws['aws.s3.video.news.url']}")
	private String NEWS_VIDEO_URL;

	@Value("#{aws['aws.s3.image.notice.url']}")
	private String NOTICE_IMAGE_URL;

	@Value("#{aws['aws.s3.video.notice.url']}")
	private String NOTICE_VIDEO_URL;

	@Override
	public String getFileUrlByUploadingImage(int fileFlag, InputStream inputStream, String fileName) throws Exception {
		String location = "";

		if (fileFlag == FileFlag.REPORT_PHOTO_FILE) {
			location = REPORT_IMAGE_URL;
		} else if (fileFlag == FileFlag.REPORT_VIDEO_FILE) {
			location = REPORT_VIDEO_URL;
		}

		if (fileFlag == FileFlag.NEWS_PHOTO_FILE) {
			location = NEWS_IMAGE_URL;
		} else if (fileFlag == FileFlag.NEWS_VIDEO_FILE) {
			location = NEWS_VIDEO_URL;
		}

		if (fileFlag == FileFlag.NOTICE_PHOTO_FILE) {
			location = NOTICE_IMAGE_URL;
		} else if (fileFlag == FileFlag.NOTICE_VIDEO_FILE) {
			location = NOTICE_VIDEO_URL;
		}

		String storageFileName = awsS3Util.getStorageFileNameByUploadingFileAndFileNameToTheLocation(location,
				inputStream, fileName);

		return S3_DOMAIN_URL + "/" + location + "/" + storageFileName;
	}
}

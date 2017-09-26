package com.demand.site.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demand.site.common.flag.FileFlag;
import com.demand.site.common.util.AwsS3Util;
import com.demand.site.service.SmartEditorService;

@Service
public class SmartEditorServiceImpl implements SmartEditorService {

	@Autowired
	AwsS3Util awsS3Util;

	@Value("#{aws['aws.s3.domain']}")
	private String S3_DOMAIN_URL;

	@Value("#{aws['aws.s3.image.report.url']}")
	private String REPORT_IMAGE_URL;

	@Value("#{aws['aws.s3.video.report.url']}")
	private String REPORT_VIDEO_URL;

	@Override
	public String getFileUrlByUploadingImage(int fileFlag, InputStream inputStream, String fileName) throws Exception {
		String location = "";
		
		if(fileFlag == FileFlag.REPORT_PHOTO_FILE){
			location = REPORT_IMAGE_URL;
		}else{
			location = REPORT_VIDEO_URL;
		}
		
		String storageFileName = awsS3Util.getStorageFileNameByUploadingFileAndFileNameToTheLocation(location,
				inputStream, fileName);

		return S3_DOMAIN_URL + "/" + location + "/" + storageFileName;
	}
}

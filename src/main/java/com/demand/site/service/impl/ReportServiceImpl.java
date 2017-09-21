package com.demand.site.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.User;
import com.demand.site.common.util.AwsS3Util;
import com.demand.site.service.ReportService;

@Service 	
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	AwsS3Util awsS3Util;

	@Value("#{aws['aws.s3.domain']}")
	private String S3_DOMAIN_URL;

	@Value("#{aws['aws.s3.file.report.url']}")
	private String REPORT_FILE_URL;

	@Override
	public void saveReport(User user, boolean isNotification, String title, String content, MultipartFile[] files) throws Exception {
		
		
		for(int i = 0; i< files.length ; i++){
			
			MultipartFile file =  files[i];
			String fileName = file.getOriginalFilename();
			InputStream  inputStream = file.getInputStream();
			
			String encryptedFileName = awsS3Util.selectEncryptedFileNameByUploadingFileAndFileNameToTheLocation(REPORT_FILE_URL,
					inputStream, fileName);
		}
		
	}

	
}

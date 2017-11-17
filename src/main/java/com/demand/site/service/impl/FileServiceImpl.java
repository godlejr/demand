package com.demand.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demand.site.common.entity.File;
import com.demand.site.common.flag.FileFlag;
import com.demand.site.common.util.AwsS3Util;
import com.demand.site.repository.file.FileRepository;
import com.demand.site.service.FileService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FileServiceImpl implements FileService{
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private AwsS3Util awsS3Util;


	@Value("#{aws['aws.s3.file.news.url']}")
	private String NEWS_FILE_URL;
	
	@Override
	public void deleteFile(long id) throws Exception {
		File file = fileRepository.findOne(id);
		String storageName = file.getStorageName();
			
		fileRepository.delete(id);
		
		int type = file.getType();
		if(type == FileFlag.NEWS_FILE_TYPE){
			awsS3Util.deleteFileByLocationAndStorageFileName(NEWS_FILE_URL, storageName);	
		}
	}
	
	 
}

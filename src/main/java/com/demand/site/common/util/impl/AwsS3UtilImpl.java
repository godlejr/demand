package com.demand.site.common.util.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.demand.site.common.util.AwsS3Util;

@Service
public class AwsS3UtilImpl implements AwsS3Util {

	@Autowired
	private AmazonS3Client amazonS3Client;

	@Value("#{aws['aws.s3.bucket']}")
	private String BUCKET;

	@Override
	public void deleteFileByLocationAndEncryptedFileName(String location, String fileName)
			throws AmazonServiceException, AmazonClientException {
		amazonS3Client.deleteObject(new DeleteObjectRequest(BUCKET, location + "/" + fileName));
	}

	@Override
	public String selectEncryptedFileNameByUploadingFileAndFileNameToTheLocation(String location,
			InputStream inputStream, String fileName)
			throws AmazonServiceException, IllegalStateException, IOException {
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		String encryptedFileName = System.currentTimeMillis() + "." + ext;

		ObjectMetadata objectMetadata = new ObjectMetadata();

		PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, location + "/" + encryptedFileName,
				inputStream, objectMetadata);

		putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
		amazonS3Client.putObject(putObjectRequest);

		System.out.println(encryptedFileName);

		return encryptedFileName;
	}

}

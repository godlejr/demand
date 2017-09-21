package com.demand.site.common.util;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

/**
 * AWS S3 관련 서비스 레이어 인터페이스
 * 
 * @author dongjooKim
 */
public interface AwsS3Util {

	/**
	 * AWS S3 버켓에서 파일이름으로 파일을 삭제하는 함수
	 * 
	 * @author dongjooKim
	 * @param location
	 * @param fileName
	 * 
	 * @throws AmazonServiceException
	 * @throws AmazonClientException
	 */
	void deleteFileByLocationAndEncryptedFileName(String location, String fileName)
			throws AmazonServiceException, AmazonClientException;

	/**
	 * 파일이름을 통해 파일 inputStream을 AWS S3 파일 업로드하고 파일이름을 리턴하는 함수
	 * 
	 * @author dongjooKim
	 * @param location
	 * @param inputStream
	 * @param fileName
	 * 
	 * @throws AmazonServiceException
	 * @throws IllegalStateException
	 * @throws IOException
	 * 
	 * @return String
	 */
	String selectEncryptedFileNameByUploadingFileAndFileNameToTheLocation(String location, InputStream inputStream,
			String fileName) throws AmazonServiceException, IllegalStateException, IOException;
}

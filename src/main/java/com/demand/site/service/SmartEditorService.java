package com.demand.site.service;

import java.io.InputStream;

public interface SmartEditorService {

	String getFileUrlByUploadingImage(int fileFlag, InputStream inputStream, String fileName) throws Exception;

}

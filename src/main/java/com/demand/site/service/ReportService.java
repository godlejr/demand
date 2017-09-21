package com.demand.site.service;

import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.User;

public interface ReportService {


	void saveReport(User user, boolean isNotification, String title, String content, MultipartFile[] files) throws Exception;

}

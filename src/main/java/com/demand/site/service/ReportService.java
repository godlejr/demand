package com.demand.site.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.Report;
import com.demand.site.common.entity.User;

public interface ReportService {

	void saveReport(User user, boolean isNotification, String title, String content, MultipartFile[] files)
			throws Exception;

	Page<Report> getReportsBySearchAndPageable(String search, Pageable pageable) throws Exception;

	Map<String, Report> getPrevPresentNextReportMapsById(long id) throws Exception;

	void deleteReport(User user, long id) throws Exception;

	Report getReportById(long id) throws Exception;

	void updateReport(User user, long id, boolean isNotification, String title, String content,
			String[] deletedFileStorageNames, MultipartFile[] files) throws Exception;

	Page<Report> getReportsByUserIdAndPageable(long userId, Pageable pageable) throws Exception;

}

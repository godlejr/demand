package com.demand.site.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.Report;
import com.demand.site.common.entity.User;

public interface ReportService {

	void saveReport(User user, boolean isNotification, String title, String content, MultipartFile[] files)
			throws Exception;

	Page<Report> getReportsBySearchAndPageable(String search, Pageable pageable) throws Exception;

}

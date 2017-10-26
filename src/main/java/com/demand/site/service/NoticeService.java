package com.demand.site.service;

import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.User;

public interface NoticeService {

	void saveNotice(User user, boolean isNotification, long noticeCategoryId, String title, String content,
			MultipartFile[] files) throws Exception;

}

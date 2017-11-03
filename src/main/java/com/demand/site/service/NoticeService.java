package com.demand.site.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.Notice;
import com.demand.site.common.entity.User;

public interface NoticeService {

	void saveNotice(User user, boolean isNotification, long noticeCategoryId, String title, String content,
			MultipartFile[] files) throws Exception;

	Page<Notice> getNoticesByNoticeCategoryIdAndSearchAndPageable(long noticeCategoryId, String search,
			Pageable pageable) throws Exception;

	Notice getNoticeById(long id) throws Exception;

	void updateNotice(User user, long id, boolean isNotification, long noticeCategoryId, String title, String content,
			String[] deletedFileStorageNames, MultipartFile[] files) throws Exception;

	void deleteNotice(User user, long id) throws Exception;

}

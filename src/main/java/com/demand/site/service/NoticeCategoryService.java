package com.demand.site.service;

import java.util.List;

import com.demand.site.common.entity.NoticeCategory;

public interface NoticeCategoryService {

	List<NoticeCategory> getNoticeCategories() throws Exception;

	NoticeCategory getNoticeCategoryById(long id) throws Exception;

}

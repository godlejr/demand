package com.demand.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demand.site.common.entity.NoticeCategory;
import com.demand.site.repository.noticecategory.NoticeCategoryRepository;
import com.demand.site.service.NoticeCategoryService;

@Service
public class NoticeCategoryServiceImpl implements NoticeCategoryService {

	@Autowired
	private NoticeCategoryRepository noticeCategoryRepository;

	@Override
	public List<NoticeCategory> getNoticeCategories() throws Exception {
		return noticeCategoryRepository.findAll();
	}

	@Override
	public NoticeCategory getNoticeCategoryById(long id) throws Exception {
		return noticeCategoryRepository.findOne(id);
	}
	
	

}

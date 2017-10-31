package com.demand.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demand.site.common.entity.NoticeCategory;
import com.demand.site.repository.noticecategory.NoticeCategoryRepository;
import com.demand.site.service.NoticeCategoryService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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

package com.demand.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demand.site.common.entity.QuestionCategory;
import com.demand.site.repository.questioncategory.QuestionCategoryRepository;
import com.demand.site.service.QuestionCategoryService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QuestionCategoryServiceImpl implements QuestionCategoryService {

	@Autowired
	QuestionCategoryRepository questionCategoryRepository;

	@Override
	public QuestionCategory getQuestionCategoryById(long id) throws Exception {
		return questionCategoryRepository.findOne(id);
	}

	@Override
	public List<QuestionCategory> getQuestionCategories() throws Exception {
		return questionCategoryRepository.findAll();
	}

}

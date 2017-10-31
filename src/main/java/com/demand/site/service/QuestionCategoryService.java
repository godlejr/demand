package com.demand.site.service;

import java.util.List;

import com.demand.site.common.entity.QuestionCategory;

public interface QuestionCategoryService {
	QuestionCategory getQuestionCategoryById(long id) throws Exception;

	List<QuestionCategory> getQuestionCategories() throws Exception;

}

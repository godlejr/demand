package com.demand.site.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demand.site.common.entity.Question;

public interface QuestionService {

	void saveQuestion(Question question) throws Exception;

	Question getCheckedQuestionByIdAndPassword(long id, String password) throws Exception;

	Page<Question> getQuestionsBySearchAndQuestionCategoryIdAndPageable(String search, long questionCategoryId,
			Pageable pageable) throws Exception;

	Question getQuestionById(long id) throws Exception;

	Page<Question> getQuestionsByQuestionCategoryIdAndPageableAndIdNot(long id, long questionCategoryId,
			Pageable pageable) throws Exception;

	void deleteQuestionById(long id) throws Exception;

	void updateQuestion(Question prevQuestion) throws Exception;
}
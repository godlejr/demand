package com.demand.site.repository.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demand.site.common.entity.Question;

public interface QuestionRepositoryCustom {

	Page<Question> findAllByQuestionCategoryIdAndPageable(String search, long questionCategoryId, Pageable pageable)
			throws Exception;

	Page<Question> findAllByQuestionCategoryIdAndPageable(long questionCategoryId, Pageable pageable) throws Exception;

}

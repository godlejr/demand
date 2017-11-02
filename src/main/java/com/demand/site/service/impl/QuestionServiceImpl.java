package com.demand.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demand.site.common.entity.Question;
import com.demand.site.common.entity.QuestionCategory;
import com.demand.site.repository.question.QuestionRepository;
import com.demand.site.repository.questioncategory.QuestionCategoryRepository;
import com.demand.site.service.QuestionService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuestionCategoryRepository questionCategoryRepository;

	@Override
	public Page<Question> getQuestionsBySearchAndQuestionCategoryIdAndPageable(String search, long questionCategoryId,
			Pageable pageable) throws Exception {
		return questionRepository.findAllByQuestionCategoryIdAndPageable(search, questionCategoryId, pageable);
	}

	@Override
	public Question getCheckedQuestionByIdAndPassword(long id, String password) throws Exception {
		return questionRepository.findByIdAndPassword(id, password);
	}

	@Override
	public void saveQuestion(Question question, long questionCategoryId) throws Exception {
		QuestionCategory questionCategory = questionCategoryRepository.findOne(questionCategoryId);
		question.setQuestionCategory(questionCategory);
		questionRepository.saveAndFlush(question);
	}

	@Override
	public Question getQuestionById(long id) throws Exception {
		return questionRepository.findOne(id);
	}

	@Override
	public Page<Question> getQuestionsByQuestionCategoryIdAndPageableAndIdNot(long id, long questionCategoryId, Pageable pageable)
			throws Exception {
		return questionRepository.findAllByQuestionCategoryIdAndPageableAndIdNot(id,questionCategoryId, pageable);
	}

}

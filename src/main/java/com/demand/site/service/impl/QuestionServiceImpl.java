package com.demand.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demand.site.common.entity.Answer;
import com.demand.site.common.entity.Question;
import com.demand.site.common.entity.QuestionAnswer;
import com.demand.site.common.entity.QuestionCategory;
import com.demand.site.repository.answer.AnswerRepository;
import com.demand.site.repository.question.QuestionRepository;
import com.demand.site.repository.questionanswer.QuestionAnswerRepository;
import com.demand.site.repository.questioncategory.QuestionCategoryRepository;
import com.demand.site.service.QuestionService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuestionCategoryRepository questionCategoryRepository;

	@Autowired
	private QuestionAnswerRepository questionAnswerRepository;

	@Autowired
	private AnswerRepository answerRepository;

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
	public void saveQuestion(Question question) throws Exception {
		long questionCategoryId = question.getQuestionCategoryId();
		QuestionCategory questionCategory = questionCategoryRepository.findOne(questionCategoryId);
		question.setQuestionCategory(questionCategory);
		question.buildEncryptPasswordWithSHA1();
		questionRepository.saveAndFlush(question);
	}

	@Override
	public Question getQuestionById(long id) throws Exception {
		return questionRepository.findOne(id);
	}

	@Override
	public Page<Question> getQuestionsByQuestionCategoryIdAndPageableAndIdNot(long id, long questionCategoryId,
			Pageable pageable) throws Exception {
		return questionRepository.findAllByQuestionCategoryIdAndPageableAndIdNot(id, questionCategoryId, pageable);
	}

	@Override
	public void deleteQuestionById(long questionId) throws Exception {
		QuestionAnswer questionAnswer = questionAnswerRepository.findByQuestionId(questionId);
		if (questionAnswer != null) {
			Answer answer = questionAnswer.getAnswer();
			long answerId = answer.getId();

			answerRepository.delete(answerId);

		}
		questionRepository.delete(questionId);
	}

	@Override
	public void updateQuestion(Question prevQuestion) throws Exception {
		long prevQuestionCategoryId = prevQuestion.getQuestionCategoryId();
		QuestionCategory questionCategory = questionCategoryRepository.findOne(prevQuestionCategoryId);
		
		long prevQuestionId = prevQuestion.getId();
		String prevTitle = prevQuestion.getTitle();
		String prevContent = prevQuestion.getContent();
		String prevWriter = prevQuestion.getWriter();
		String prevPasswordNotEncrypted = prevQuestion.getPasswordNotEncrypted();
		int prevType = prevQuestion.getType();
		
		Question question = questionRepository.findOne(prevQuestionId);
		question.setQuestionCategory(questionCategory);
		question.setPasswordNotEncrypted(prevPasswordNotEncrypted);
		question.buildEncryptPasswordWithSHA1();
		question.setWriter(prevWriter);
		question.setTitle(prevTitle);
		question.setContent(prevContent);
		question.setType(prevType);
		
		questionRepository.saveAndFlush(question);
	}
	

}

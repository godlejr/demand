package com.demand.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demand.site.common.entity.Answer;
import com.demand.site.common.entity.Question;
import com.demand.site.common.entity.QuestionAnswer;
import com.demand.site.common.entity.User;
import com.demand.site.repository.answer.AnswerRepository;
import com.demand.site.repository.question.QuestionRepository;
import com.demand.site.repository.questionanswer.QuestionAnswerRepository;
import com.demand.site.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionAnswerRepository QuestionAnswerRepository;

	@Override
	public void saveAnswer(Answer answer, User user) throws Exception {
		answer.setUser(user);
		long questionId = answer.getQuestionId();
		Question question = questionRepository.findOne(questionId);

		Answer persistedAnswer = answerRepository.saveAndFlush(answer);

		QuestionAnswer questionAnswer = new QuestionAnswer();
		questionAnswer.setAnswer(persistedAnswer);
		questionAnswer.setQuestion(question);

		QuestionAnswerRepository.saveAndFlush(questionAnswer);
	}

	@Override
	public void deleteAnswerById(long id) throws Exception {
		answerRepository.delete(id);
	}

	@Override
	public void updateAnswer(Answer answer, User user) throws Exception {
		answer.setUser(user);
		answerRepository.saveAndFlush(answer);
	}

	@Override
	public Answer getAnswerById(long id) throws Exception {
		return answerRepository.findOne(id);
	}

}

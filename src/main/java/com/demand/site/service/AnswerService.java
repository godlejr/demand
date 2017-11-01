package com.demand.site.service;

import com.demand.site.common.entity.Answer;
import com.demand.site.common.entity.Question;
import com.demand.site.common.entity.User;

public interface AnswerService {

	void saveAnswer(Answer answer, User user) throws Exception;

}

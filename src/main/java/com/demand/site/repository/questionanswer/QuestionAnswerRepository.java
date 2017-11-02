package com.demand.site.repository.questionanswer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demand.site.common.entity.QuestionAnswer;

public interface QuestionAnswerRepository  extends JpaRepository<QuestionAnswer, Long>{

	QuestionAnswer findByQuestionId(long questionId);

}

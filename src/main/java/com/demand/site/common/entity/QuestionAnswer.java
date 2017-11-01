package com.demand.site.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "QUESTION_ANSWERS")
public class QuestionAnswer extends Base {
	
	@OneToOne
	@JoinColumn(name = "QUESTION_ID")
	@JsonManagedReference
	private Question question;
	
	@OneToOne
	@JoinColumn(name = "ANSWER_ID")
	@JsonManagedReference
	private Answer answer;

	public QuestionAnswer() {
		super();
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

}

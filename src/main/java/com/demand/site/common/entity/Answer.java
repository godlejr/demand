package com.demand.site.common.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ANSWERS")
public class Answer extends Base {

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	@JsonManagedReference
	private User user;

	@NotEmpty(message = "제목을 입력해주세요.")
	private String title;

	@NotEmpty(message = "내용을 입력해주세요.")
	private String content;
	private int type;

	@OneToOne(mappedBy = "answer", fetch = FetchType.LAZY)
	@JsonBackReference
	private QuestionAnswer questionAnswer;

	@Transient
	private long questionId;

	public Answer() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public QuestionAnswer getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(QuestionAnswer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String getCustomCreatedAt() {
		return calculateDate(this.getCreatedAt());
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String calculateDate(String dateTime) {
		String message = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;

		try {
			date = simpleDateFormat.parse(dateTime);
			long currentTime = System.currentTimeMillis();
			long registeredTime = date.getTime();
			long differentOfTime = (currentTime - registeredTime) / 1000;

			if (differentOfTime < 60) {
				message = differentOfTime + "초전";
			} else if ((differentOfTime /= 60) < 60) {
				message = differentOfTime + "분전";
			} else if ((differentOfTime /= 60) < 24) {
				message = (differentOfTime) + "시간전";
			} else if ((differentOfTime /= 24) < 7) {
				message = (differentOfTime) + "일전";
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.M.d");
				message = dateFormat.format(date);
			}
		} catch (ParseException e) {
			message = "알수없음";
		}
		return message;
	}

}

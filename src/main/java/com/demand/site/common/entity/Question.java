package com.demand.site.common.entity;

import java.security.MessageDigest;
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

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotEmpty;

import com.demand.site.common.annotation.QuestionPassword;
import com.demand.site.common.annotation.QuestionWriter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "QUESTIONS")
public class Question extends Base {

	@ManyToOne
	@JoinColumn(name = "QUESTION_CATEGORY_ID")
	@JsonManagedReference
	private QuestionCategory questionCategory;

	@NotEmpty(message = "작성자명을 입력해주세요.")
	@QuestionWriter
	private String writer;

	private String password;

	@NotEmpty(message = "비밀번호을 입력해주세요.")
	@QuestionPassword
	@Transient
	private String passwordNotEncrypted;

	@NotEmpty(message = "제목을 입력해주세요.")
	private String title;

	@NotNull(message = "내용을 입력해주세요.")
	private String content;

	private int type;
	private int hits;

	@Transient
	private boolean privacy;

	@Transient
	@NotNull(message = "카테고리를 선택해주세요.")
	private long questionCategoryId;

	@Formula("(select CASE WHEN count(qa.id) > 0 THEN true ELSE false END  from question_answers qa where qa.question_id = id)")
	private boolean answerCheck;

	@OneToOne(mappedBy = "question", fetch = FetchType.LAZY)
	@JsonBackReference
	private QuestionAnswer questionAnswer;

	public Question() {
		super();
	}

	public Question(QuestionCategory questionCategory, String writer, String password, String title, String content,
			int type, int hits) {
		super();
		this.questionCategory = questionCategory;
		this.writer = writer;
		this.password = password;
		this.title = title;
		this.content = content;
		this.type = type;
		this.hits = hits;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}

	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {

		if (privacy) {
			type = 1;
		} else {
			type = 0;
		}

		this.privacy = privacy;
	}

	public long getQuestionCategoryId() {
		return questionCategoryId;
	}

	public void setQuestionCategoryId(long questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
	}

	public boolean isAnswerCheck() {
		return answerCheck;
	}

	public void setAnswerCheck(boolean answerCheck) {
		this.answerCheck = answerCheck;
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

	public String getPasswordNotEncrypted() {
		return passwordNotEncrypted;
	}

	public void setPasswordNotEncrypted(String passwordNotEncrypted) {
		this.passwordNotEncrypted = passwordNotEncrypted;
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

	@Override
	public long getId() {
		return super.getId();
	}

	@Override
	public void setId(long id) {
		super.setId(id);
	}

	@Override
	public String getCreatedAt() {
		return super.getCreatedAt();
	}

	@Override
	public void setCreatedAt(String createdAt) {
		super.setCreatedAt(createdAt);
	}

	@Override
	public String getUpdatedAt() {
		return super.getUpdatedAt();
	}

	@Override
	public void setUpdatedAt(String updatedAt) {
		super.setUpdatedAt(updatedAt);
	}

	public void buildEncryptPasswordWithSHA1() throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(this.passwordNotEncrypted.getBytes(), 0, this.passwordNotEncrypted.length());
		this.password = String.format("%064x", new java.math.BigInteger(1, messageDigest.digest()));
	}

}
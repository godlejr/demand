package com.demand.site.common.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "QUESTION_CATEGORIES")
public class QuestionCategory extends Base {
	private String name;

	@OneToMany(mappedBy = "questionCategory", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Question> questions = new ArrayList<Question>();

	public QuestionCategory() {
		super();
	}

	public QuestionCategory(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
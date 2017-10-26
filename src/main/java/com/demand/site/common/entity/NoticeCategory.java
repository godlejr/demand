package com.demand.site.common.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "NOTICE_CATEGORIES")
public class NoticeCategory extends Base {
	private String name;

	@OneToMany(mappedBy = "noticeCategory", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Notice> notices = new ArrayList<Notice>();

	public NoticeCategory() {
		super();
	}

	public NoticeCategory(String name) {
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
package com.demand.site.common.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "NOTICES")
public class Notice extends Base {

	@ManyToOne
	@JoinColumn(name = "NOTICE_CATEGORY_ID")
	@JsonManagedReference
	private NoticeCategory noticeCategory;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	@JsonManagedReference
	private User user;

	private String title;
	private String content;
	private int hits;
	private int type;

	@OneToMany(mappedBy = "notice", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<NoticeFile> noticeFiles = new ArrayList<NoticeFile>();

	public Notice() {
		super();
	}

	public Notice(NoticeCategory noticeCategory, User user, String title, String content, int hits) {
		super();
		this.noticeCategory = noticeCategory;
		this.user = user;
		this.title = title;
		this.content = content;
		this.hits = hits;
	}

	public NoticeCategory getNoticeCategory() {
		return noticeCategory;
	}

	public void setNoticeCategory(NoticeCategory noticeCategory) {
		this.noticeCategory = noticeCategory;
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

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public List<NoticeFile> getNoticeFiles() {
		return noticeFiles;
	}

	public void setNoticeFiles(List<NoticeFile> noticeFiles) {
		this.noticeFiles = noticeFiles;
	}

}
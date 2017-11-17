package com.demand.site.common.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "FILES")
public class File extends Base {
	private int type;
	private String originalName;
	private String storageName;
	private String ext;
	private int size;

	@OneToMany(mappedBy = "file", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<ReportFile> reportFiles = new ArrayList<ReportFile>();

	@OneToMany(mappedBy = "avatarFile", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<User> users = new ArrayList<User>();

	@OneToMany(mappedBy = "file", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<NoticeFile> noticeFiles = new ArrayList<NoticeFile>();

	@OneToMany(mappedBy = "file", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<NewsFile> newsFiles = new ArrayList<NewsFile>();

	@OneToOne(mappedBy = "avatarFile", fetch = FetchType.LAZY)
	@JsonBackReference
	private News news;

	public File() {
		super();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String orginalName) {
		this.originalName = orginalName;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<ReportFile> getReportFiles() {
		return reportFiles;
	}

	public void setReportFiles(List<ReportFile> reportFiles) {
		this.reportFiles = reportFiles;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<NoticeFile> getNoticeFiles() {
		return noticeFiles;
	}

	public void setNoticeFiles(List<NoticeFile> noticeFiles) {
		this.noticeFiles = noticeFiles;
	}

	public List<NewsFile> getNewsFiles() {
		return newsFiles;
	}

	public void setNewsFiles(List<NewsFile> newsFiles) {
		this.newsFiles = newsFiles;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
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

	public String getFileSizeFormatted() {
		return byteCalculation(String.valueOf(this.size));
	}

	public String byteCalculation(String bytes) {
		String retFormat = "0";
		Double size = Double.parseDouble(bytes);

		String[] sizeFormats = { "bytes", "KB", "MB", "GB", "TB", "PB" };

		if (bytes != "0") {
			int indext = (int) Math.floor(Math.log(size) / Math.log(1024));
			DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
			double ret = ((size / Math.pow(1024, Math.floor(indext))));
			retFormat = decimalFormat.format(ret) + " " + sizeFormats[indext];
		} else {
			retFormat += " " + sizeFormats[0];
		}

		return retFormat;
	}

}

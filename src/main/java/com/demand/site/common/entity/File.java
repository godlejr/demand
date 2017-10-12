package com.demand.site.common.entity;

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

}

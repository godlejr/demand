package com.demand.site.common.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "NEWS")
public class News extends Base {

	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToOne
	@JsonManagedReference
	@JoinColumn(name = "AVATAR_FILE_ID")
	private File avatarFile;

	@OneToMany(mappedBy = "news", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<NewsFile> newsFiles = new ArrayList<NewsFile>();

	private String title;
	private String content;

	public News() {
		super();
	}

	public News(User user, File avatarFile, List<NewsFile> newsFiles, String title, String content) {
		super();
		this.user = user;
		this.avatarFile = avatarFile;
		this.newsFiles = newsFiles;
		this.title = title;
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(File avatarFile) {
		this.avatarFile = avatarFile;
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

	public List<NewsFile> getNewsFiles() {
		return newsFiles;
	}

	public void setNewsFiles(List<NewsFile> newsFiles) {
		this.newsFiles = newsFiles;
	}

	public String getCustomCreatedAt() {
		return calculateDate(this.getCreatedAt());
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
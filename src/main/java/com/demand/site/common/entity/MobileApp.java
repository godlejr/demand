package com.demand.site.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MOBILE_APPS")
public class MobileApp extends Base {

	private String name;
	private String intro;
	private String downloadUrl;
	private String agreement;
	private String privacy;

	public MobileApp() {
		super();
	}

	public MobileApp(String name, String intro, String downloadUrl) {
		super();
		this.name = name;
		this.intro = intro;
		this.downloadUrl = downloadUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

}
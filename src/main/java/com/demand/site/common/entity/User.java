package com.demand.site.common.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.demand.site.common.annotation.Password;
import com.demand.site.common.annotation.RegistrationNo;
import com.demand.site.common.embeddable.Address;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USERS")
public class User extends Base {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "이메일을 입력하세요.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;

	@Password
	private String password;

	@Transient
	@NotNull(message = "비밀번호가 일치하지 않습니다.")
	private String passwordConfirm;

	@NotEmpty(message = "이름 입력하세요.")
	@NotNull
	private String name;

	@RegistrationNo
	private String registrationNo;

	@Transient
	@AssertTrue(message = "이메일 중복확인을 해주세요.")
	private boolean emailCheck;

	@Embedded
	private Address address;
	private String secondEmail;
	private String phone;
	private String ntisRegistrationNo;
	private String finalUniversity;
	private String major;

	@ManyToOne
	@JoinColumn(name = "POSITION_CATEGORY_ID")
	@JsonManagedReference
	private PositionCategory positionCategory;

	@ManyToOne
	@JoinColumn(name = "EDUCATION_STATUS_CATEGORY_ID")
	@JsonManagedReference
	private EducationStatusCategory educationStatusCategory;

	@ManyToOne
	@JoinColumn(name = "DEGREE_CATEGORY_ID")
	@JsonManagedReference
	private DegreeCategory degreeCategory;

	private int level;
	private int checked;

	private String joinedAt;
	private String resignedAt;

	@ManyToOne
	@JoinColumn(name = "AVATAR_FILE_ID")
	@JsonManagedReference
	private File avatarFile;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Report> reports = new ArrayList<Report>();

	public User() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getSecondEmail() {
		return secondEmail;
	}

	public void setSecondEmail(String secondEmail) {
		this.secondEmail = secondEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNtisRegistrationNo() {
		return ntisRegistrationNo;
	}

	public void setNtisRegistrationNo(String ntisRegistrationNo) {
		this.ntisRegistrationNo = ntisRegistrationNo;
	}

	public boolean isEmailCheck() {
		return emailCheck;
	}

	public void setEmailCheck(boolean emailCheck) {
		this.emailCheck = emailCheck;
	}

	public String getFinalUniversity() {
		return finalUniversity;
	}

	public void setFinalUniversity(String finalUniversity) {
		this.finalUniversity = finalUniversity;
	}

	public PositionCategory getPositionCategory() {
		return positionCategory;
	}

	public void setPositionCategory(PositionCategory positionCategory) {
		if (this.positionCategory != null) {
			this.positionCategory.getUsers().remove(this);
		}

		this.positionCategory = positionCategory;
		this.positionCategory.getUsers().add(this);
	}

	public EducationStatusCategory getEducationStatusCategory() {
		return educationStatusCategory;
	}

	public void setEducationStatusCategory(EducationStatusCategory educationStatusCategory) {
		if (this.educationStatusCategory != null) {
			this.educationStatusCategory.getUsers().remove(this);
		}

		this.educationStatusCategory = educationStatusCategory;
		this.educationStatusCategory.getUsers().add(this);
	}

	public DegreeCategory getDegreeCategory() {
		return degreeCategory;
	}

	public void setDegreeCategory(DegreeCategory degreeCategory) {
		if (this.degreeCategory != null) {
			this.degreeCategory.getUsers().remove(this);
		}

		this.degreeCategory = degreeCategory;
		this.degreeCategory.getUsers().add(this);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public File getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(File avatarFile) {
		if (this.avatarFile != null) {
			this.avatarFile.getUsers().remove(this);
		}

		this.avatarFile = avatarFile;
		this.avatarFile.getUsers().add(this);
	}

	public String getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(String joinedAt) {
		this.joinedAt = joinedAt;
	}

	public String getResignedAt() {
		return resignedAt;
	}

	public void setResignedAt(String resignedAt) {
		this.resignedAt = resignedAt;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
		checkPassword();
	}

	private void checkPassword() {
		if (!password.equals(passwordConfirm)) {
			passwordConfirm = null;
		}
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
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

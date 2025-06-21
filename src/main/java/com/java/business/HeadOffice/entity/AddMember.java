package com.java.business.HeadOffice.entity;


import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class AddMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberid;

	private String name;

	private Long phone;

	private String email;

	private Date dob;

	private String password;


	private String gender;

	private Long aadharno;

	private String panNo;

	@Column(length=1000)
	private String address;

	@Column(name = "date")
	private Date date;

   private long roleId;


	@Column(name = "description")
	private String description;



	public Long getMemberid() {
		return memberid;
	}



	public void setMemberid(Long memberid) {
		this.memberid = memberid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getPhone() {
		return phone;
	}



	public void setPhone(Long phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}







	public Date getDob() {
		return dob;
	}



	public void setDob(Date dob) {
		this.dob = dob;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public Long getAadharno() {
		return aadharno;
	}



	public void setAadharno(Long aadharno) {
		this.aadharno = aadharno;
	}



	public String getPanNo() {
		return panNo;
	}



	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public long getRoleId() {
		return roleId;
	}



	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public AddMember() {
		super();
	}


}

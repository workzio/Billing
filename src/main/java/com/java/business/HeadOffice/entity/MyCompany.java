package com.java.business.HeadOffice.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="mycompany")
public class MyCompany {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mycompanyid;
	
	@NotNull(message="Please Enter the companyname")
	@Size(min=1, max=40)
	@Column(name="companyname", nullable = false)
	private String companyname;

	@NotNull(message="please Enter the Phonenumber")
	@Size(min=10,max=10)
	@Column(name="phoneno1",nullable=false)
	private long phoneno1;
	
	@NotNull(message="please Enter the Phonenumber")
	@Size(min=10,max=10)
	@Column(name="phoneno2",nullable=false)
	private long phoneno2;


	@NotNull(message="Please Enter Email")
	@Column(name="email", nullable=false)
	private String email ;

	@NotNull(message="Please Enter District")
	@Size(min=10,max=40)
	@Column(name="district",nullable=false)
	private String district ;

	@NotNull(message="Please Enter State")
	@Size(min=7,max=30)
	@Column(name="state", nullable=false)
	private String state;

	@NotNull(message = "Please Enter country")

	@Column(name = "country", nullable = false)
	private String country;


	@NotNull(message="Please Enter the pincode")
	@Size(min=2,max=12)
	@Column(name="pincode", nullable=false)
	private int pincode ;
	
	@NotNull(message="Please Enter the code")
	@Size(min=2,max=12)
	@Column(name="code", nullable=false)
	private int code ;

	@NotNull(message ="Please Enter GST/UIN")
	@Size(min=2,max=20)
	@Column(name="gstno", nullable=false)
	private String gstno;

	@NotNull(message ="Please Enter Address")
	@Size(min=20, max=400)
    @Column(name="address",nullable=false)
	private String address;
	
	@NotNull(message ="Please Enter location")
	 @Column(name="location",nullable=false)
	private String location;


//	
//	@NotNull(message = "Please Enter date")
//	@Column(name="date",nullable=false)
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date date;
	
	
	@NotNull(message="Please Enter the bankname")
	@Size(min=1, max=40)
	@Column(name="bankname", nullable = false)
	private String bankname;
	
	@NotNull(message="Please Enter the account_no")
	@Size(min=2,max=15)
	@Column(name="account_no", nullable=false)
	private Long account_no  ;
	
	@NotNull(message="Please Enter the ifsc_code")
	@Size(min=2,max=10)
	@Column(name="ifsc_code", nullable=false)
	private String ifsc_code ;
	
	@NotNull(message="Please Enter the branch")
	@Size(min=2,max=25)
	@Column(name="branch", nullable=false)
	private String branch ;

	public long getMycompanyid() {
		return mycompanyid;
	}

	public void setMycompanyid(long mycompanyid) {
		this.mycompanyid = mycompanyid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public long getPhoneno1() {
		return phoneno1;
	}

	public void setPhoneno1(long phoneno1) {
		this.phoneno1 = phoneno1;
	}

	public long getPhoneno2() {
		return phoneno2;
	}

	public void setPhoneno2(long phoneno2) {
		this.phoneno2 = phoneno2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getGstno() {
		return gstno;
	}

	public void setGstno(String gstno) {
		this.gstno = gstno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public Long getAccount_no() {
		return account_no;
	}

	public void setAccount_no(Long account_no) {
		this.account_no = account_no;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "MyCompany [mycompanyid=" + mycompanyid + ", companyname=" + companyname + ", phoneno1=" + phoneno1
				+ ", phoneno2=" + phoneno2 + ", email=" + email + ", district=" + district + ", state=" + state
				+ ", country=" + country + ", pincode=" + pincode + ", code=" + code + ", gstno=" + gstno + ", address="
				+ address + ", location=" + location + ", bankname=" + bankname + ", account_no="
				+ account_no + ", ifsc_code=" + ifsc_code + ", branch=" + branch + "]";
	}

	public MyCompany(long mycompanyid,
			@NotNull(message = "Please Enter the companyname") @Size(min = 1, max = 40) String companyname,
			@NotNull(message = "please Enter the Phonenumber") @Size(min = 10, max = 10) long phoneno1,
			@NotNull(message = "please Enter the Phonenumber") @Size(min = 10, max = 10) long phoneno2,
			@NotNull(message = "Please Enter Email") String email,
			@NotNull(message = "Please Enter District") @Size(min = 10, max = 40) String district,
			@NotNull(message = "Please Enter State") @Size(min = 7, max = 30) String state,
			@NotNull(message = "Please Enter country") String country,
			@NotNull(message = "Please Enter the pincode") @Size(min = 2, max = 12) int pincode,
			@NotNull(message = "Please Enter the code") @Size(min = 2, max = 12) int code,
			@NotNull(message = "Please Enter GST/UIN") @Size(min = 2, max = 20) String gstno,
			@NotNull(message = "Please Enter Address") @Size(min = 20, max = 400) String address,
			@NotNull(message = "Please Enter location") String location,
			@NotNull(message = "Please Enter the bankname") @Size(min = 1, max = 40) String bankname,
			@NotNull(message = "Please Enter the account_no") @Size(min = 2, max = 15) Long account_no,
			@NotNull(message = "Please Enter the ifsc_code") @Size(min = 2, max = 10) String ifsc_code,
			@NotNull(message = "Please Enter the branch") @Size(min = 2, max = 25) String branch) {
		super();
		this.mycompanyid = mycompanyid;
		this.companyname = companyname;
		this.phoneno1 = phoneno1;
		this.phoneno2 = phoneno2;
		this.email = email;
		this.district = district;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.code = code;
		this.gstno = gstno;
		this.address = address;
		this.location = location;
		
		this.bankname = bankname;
		this.account_no = account_no;
		this.ifsc_code = ifsc_code;
		this.branch = branch;
	}

	public MyCompany() {
		super();
	}

	


	


}

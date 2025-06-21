package com.java.business.HeadOffice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="distributor")
public class Distributor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long distributorid;
	
	@NotNull(message="Please Enter the Name")
	@Size(min=1, max=40)
	@Column(name="name", nullable = false)
	private String name;

	@NotNull(message="please Enter the Phonenumber")
	@Size(min=10,max=10)
	@Column(name="phoneno")
	private long phoneno;

	@NotNull(message="Please Enter Email")
	@Column(name="email")
	private String email ;

	@NotNull(message="Please Enter District")
	@Size(min=10,max=40)
	@Column(name="district")
	private String district ;

	@NotNull(message="Please Enter State")
	@Size(min=7,max=30)
	@Column(name="state")
	private String state;

	@NotNull(message="Please Enter the code")
	@Size(min=2,max=12)
	@Column(name="code", nullable=false)
	private int code ;
	
	@NotNull(message="Please Enter the pincode")
	@Size(min=2,max=12)
	@Column(name="pincode")
	private int pincode ;

	@NotNull(message ="Please Enter GST/UIN")
	@Size(min=2,max=20)
	@Column(name="gstno")
	private String gst_no;

	@NotNull(message ="Please Enter Address")
	@Size(min=20, max=400)
    @Column(name="address",nullable=false)
	private String address;

	@NotNull(message = "Please Enter Status")
	@Column(name="status")
	private Boolean status;

//	private long creditAmt;
	
	@NotNull(message="Enter the date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date")
	private Date date;
	

//	private boolean creditstatus;
	




	public Distributor(@NotNull(message = "Enter the date") Date date) {
		super();
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getDistributorid() {
		return distributorid;
	}

	public void setDistributorid(long distributorid) {
		this.distributorid = distributorid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
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

	

	
	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	

	public Distributor() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	@Override
	public String toString() {
		return "Distributor [distributorid=" + distributorid + ", name=" + name + ", phoneno=" + phoneno + ", email="
				+ email + ", district=" + district + ", state=" + state + ", code=" + code + ", pincode=" + pincode
				+ ", gst_no=" + gst_no + ", address=" + address + ", status=" + status + "]";
	}

	public Distributor(long distributorid,
			@NotNull(message = "Please Enter the Name") @Size(min = 1, max = 40) String name,
			@NotNull(message = "please Enter the Phonenumber") @Size(min = 10, max = 10) long phoneno,
			@NotNull(message = "Please Enter Email") String email,
			@NotNull(message = "Please Enter District") @Size(min = 10, max = 40) String district,
			@NotNull(message = "Please Enter State") @Size(min = 7, max = 30) String state,
			@NotNull(message = "Please Enter the code") @Size(min = 2, max = 12) int code,
			@NotNull(message = "Please Enter the pincode") @Size(min = 2, max = 12) int pincode,
			@NotNull(message = "Please Enter GST/UIN") @Size(min = 2, max = 20) String gst_no,
			@NotNull(message = "Please Enter Address") @Size(min = 20, max = 400) String address,
			@NotNull(message = "Please Enter Status") Boolean status) {
		super();
		this.distributorid = distributorid;
		this.name = name;
		this.phoneno = phoneno;
		this.email = email;
		this.district = district;
		this.state = state;
		this.code = code;
		this.pincode = pincode;
		this.gst_no = gst_no;
		this.address = address;
		this.status = status;
	}

	

	
	
	
}

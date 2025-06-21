package com.java.business.HeadOffice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java.business.HeadOffice.ZeroToNullSerializer;

@Entity
@Table(name = "voucher")
public class Voucher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "voucher_id")
	private long voucher_id;

	@NotNull(message = "voucherno Already Exist")
	@Column(name = "voucherno", nullable = false)
	private long voucherNo;

	@NotNull(message = "Enter the date")
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;

	@NotNull(message = "paidTo Already Exist")
	@Column(name = "paidTo", nullable = false)
	private String paidTo;

	@NotNull(message = "particulars Already Exist")
	@Column(name = "particulars", nullable = true)
	private String particulars;

	@NotNull(message = "amount Already Exist")
	@Column(name = "amount", nullable = false)
	private double amount;

	
	@NotNull(message = "returnType Already Exist")
	@Column(name = "returnType", nullable = false)
	private boolean returnType;

	@JsonSerialize(using = ZeroToNullSerializer.class)
	private long distributorid;

	@JsonSerialize(using = ZeroToNullSerializer.class)
	private long companyid;

	private String voucherType;
	
	public String getTowards() {
		return towards;
	}

	public void setTowards(String towards) {
		this.towards = towards;
	}


	private String towards;

	public long getVoucher_id() {
		return voucher_id;
	}

	public void setVoucher_id(long voucher_id) {
		this.voucher_id = voucher_id;
	}

	public long getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(long voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaidTo() {
		return paidTo;
	}

	public void setPaidTo(String paidTo) {
		this.paidTo = paidTo;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isReturnType() {
		return returnType;
	}
	
	public long getDistributorid() {
		return distributorid;
	}

	public void setDistributorid(long distributorid) {
		this.distributorid = distributorid;
	}

	public long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(long companyid) {
		this.companyid = companyid;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}


	public void setReturnType(boolean returnType) {
		this.returnType = returnType;
	}

}

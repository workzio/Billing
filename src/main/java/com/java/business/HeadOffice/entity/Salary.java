package com.java.business.HeadOffice.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Salary")
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long salaryId;
	private long memberid;
	private long roleId;
	private Date salaryDate;
	private String paymentType;
	private double amount;
	private double fullPayment;
	private double advanceAmount;
	private Date advanceAmountDate;
	
	public Date getAdvanceAmountDate() {
		return advanceAmountDate;
	}

	public void setAdvanceAmountDate(Date advanceAmountDate) {
		this.advanceAmountDate = advanceAmountDate;
	}

	public long getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(long salaryId) {
		this.salaryId = salaryId;
	}

	public long getMemberid() {
		return memberid;
	}

	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public Date getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFullPayment() {
		return fullPayment;
	}

	public void setFullPayment(double fullPayment) {
		this.fullPayment = fullPayment;
	}

	public double getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	public Salary() {
		super();
	}

}

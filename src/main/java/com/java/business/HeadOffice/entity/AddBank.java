package com.java.business.HeadOffice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addbank")
public class AddBank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bankId;

	private String accountType;

	private String bankName;
	
//	private String customerBankName;

	private String branchName;
	
//	private String customerBranchName;

	private String accountNumber;
	
//	private String customerAccountNumber;

	private String ifscCode;
	
	@Column(length = 10)
	private long phoneNumber;
	
	private String accountHolderName;
	
//	private String customerAccountHolderName;
	
	private boolean bankStatus;

	public boolean isBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(boolean bankStatus) {
		this.bankStatus = bankStatus;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public long getBankId() {
		return bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public AddBank() {
		super();
	}

}

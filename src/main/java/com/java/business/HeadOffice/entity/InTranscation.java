package com.java.business.HeadOffice.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="intranscation")
public class InTranscation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inTranscationId;
	
	private long bankId;
	
	private Date inTranscationDate;
	
	private String paymentType;
	
	private double inTranscationAmount;
	
	
	private boolean inTranscationStatus;

	public boolean isInTranscationStatus() {
		return inTranscationStatus;
	}

	public void setInTranscationStatus(boolean inTranscationStatus) {
		this.inTranscationStatus = inTranscationStatus;
	}

	public long getInTranscationId() {
		return inTranscationId;
	}

	public void setInTranscationId(long inTranscationId) {
		this.inTranscationId = inTranscationId;
	}

	public long getBankId() {
		return bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	public Date getInTranscationDate() {
		return inTranscationDate;
	}

	public void setInTranscationDate(Date inTranscationDate) {
		this.inTranscationDate = inTranscationDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getInTranscationAmount() {
		return inTranscationAmount;
	}

	public void setInTranscationAmount(double inTranscationAmount) {
		this.inTranscationAmount = inTranscationAmount;
	}

	public InTranscation() {
		super();
	}

}

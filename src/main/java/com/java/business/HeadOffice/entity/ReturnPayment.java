package com.java.business.HeadOffice.entity;

import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name="returnpayment")
public class ReturnPayment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentId;
	
	private long sales_id;
	
	private double currentReceived;
	
	private Date paymentDate;
	
	private double received;

	private double balance;
	
	private String returnPaymentType;
	
	private Date invoice_date;
	
	private int invoice_no;
	
	private long distributorid;
	private boolean status;

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(int invoice_no) {
		this.invoice_no = invoice_no;
	}


	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public long getSales_id() {
		return sales_id;
	}

	public void setSales_id(long sales_id) {
		this.sales_id = sales_id;
	}

	public double getCurrentReceived() {
		return currentReceived;
	}

	public void setCurrentReceived(double currentReceived) {
		this.currentReceived = currentReceived;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getReceived() {
		return received;
	}

	public void setReceived(double received) {
		this.received = received;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getReturnPaymentType() {
		return returnPaymentType;
	}

	public void setReturnPaymentType(String returnPaymentType) {
		this.returnPaymentType = returnPaymentType;
	}

	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	public long getDistributorid() {
		return distributorid;
	}

	public void setDistributorid(long distributorid) {
		this.distributorid = distributorid;
	}

	
	
	
	
	


}

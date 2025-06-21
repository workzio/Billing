package com.java.business.HeadOffice.entity;

import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
//@IdClass(TimePK.class)
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_id")
	private long sales_id;

	private long distributorid;

	private Date invoice_date;

	private String payment_type;

	private long phone_number;

	@Column(length = 200)
	private String description;

	private double total_qty;

	private int invoice_no;

	private double total_price;

	private double total_tax;

	private double total_amount;

	@Column(length = 400)
	private String shipping_address;

	private double roundoff;

	private double roundoff_amount;

	private double received;

	private double balance;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_sales_id", referencedColumnName = "sales_id")
	private List<SalesList> saleslist;

	public long getSales_id() {
		return sales_id;
	}

	public void setSales_id(long sales_id) {
		this.sales_id = sales_id;
	}

	public long getDistributorid() {
		return distributorid;
	}

	public void setDistributorid(long distributorid) {
		this.distributorid = distributorid;
	}

	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public long getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTotal_qty() {
		return total_qty;
	}

	public void setTotal_qty(double total_qty) {
		this.total_qty = total_qty;
	}

	public int getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(int invoice_no) {
		this.invoice_no = invoice_no;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public double getTotal_tax() {
		return total_tax;
	}

	public void setTotal_tax(double total_tax) {
		this.total_tax = total_tax;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public String getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}

	public double getRoundoff() {
		return roundoff;
	}

	public void setRoundoff(double roundoff) {
		this.roundoff = roundoff;
	}

	public double getRoundoff_amount() {
		return roundoff_amount;
	}

	public void setRoundoff_amount(double roundoff_amount) {
		this.roundoff_amount = roundoff_amount;
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

	public List<SalesList> getSaleslist() {
		return saleslist;
	}

	public void setSaleslist(List<SalesList> saleslist) {
		this.saleslist = saleslist;
	}

	public Sales() {
		super();
	}

}
package com.java.business.HeadOffice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="purchaselist")
public class PurchaseList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_list_id")
	private long purchase_list_id;

	private String categoryid;

	private long productid;

	private long qty;
	
	private long shipped_qty;

	private double price;

	private double tax_amt;

	private double amount;
	
	private double inc_tax_price;
	
	private double tax_qty_amount;
	
	private double price_tot_amt;
	  
	
	private long unit_id;


	public long getPurchase_list_id() {
		return purchase_list_id;
	}


	public void setPurchase_list_id(long purchase_list_id) {
		this.purchase_list_id = purchase_list_id;
	}


	public String getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}


	public long getProductid() {
		return productid;
	}


	public void setProductid(long productid) {
		this.productid = productid;
	}


	public long getQty() {
		return qty;
	}


	public void setQty(long qty) {
		this.qty = qty;
	}


	public long getShipped_qty() {
		return shipped_qty;
	}


	public void setShipped_qty(long shipped_qty) {
		this.shipped_qty = shipped_qty;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	


	public double getTax_amt() {
		return tax_amt;
	}


	public void setTax_amt(double tax_amt) {
		this.tax_amt = tax_amt;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public double getInc_tax_price() {
		return inc_tax_price;
	}


	public void setInc_tax_price(double inc_tax_price) {
		this.inc_tax_price = inc_tax_price;
	}


	public double getTax_qty_amount() {
		return tax_qty_amount;
	}


	public void setTax_qty_amount(double tax_qty_amount) {
		this.tax_qty_amount = tax_qty_amount;
	}


	public double getPrice_tot_amt() {
		return price_tot_amt;
	}


	public void setPrice_tot_amt(double price_tot_amt) {
		this.price_tot_amt = price_tot_amt;
	}


	public long getUnit_id() {
		return unit_id;
	}


	public void setUnit_id(long unit_id) {
		this.unit_id = unit_id;
	}


	public PurchaseList(long purchase_list_id, String categoryid, long productid, long qty, long shipped_qty,
			double price, double tax_amt, double amount,
			double inc_tax_price, double tax_qty_amount, double price_tot_amt, long unit_id) {
		super();
		this.purchase_list_id = purchase_list_id;
		this.categoryid = categoryid;
		this.productid = productid;
		this.qty = qty;
		this.shipped_qty = shipped_qty;
		this.price = price;
		
		this.tax_amt = tax_amt;
		this.amount = amount;
		this.inc_tax_price = inc_tax_price;
		this.tax_qty_amount = tax_qty_amount;
		this.price_tot_amt = price_tot_amt;
		this.unit_id = unit_id;
	}


	public PurchaseList() {
		super();
	}


	
	

	
	

}

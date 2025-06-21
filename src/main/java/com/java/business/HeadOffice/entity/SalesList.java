package com.java.business.HeadOffice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "saleslist")
public class SalesList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saleslist_id")
	private long saleslist_id;

	private String categoryid;

	private long productid;

	private int qty;
	
	private int shipped_qty;

	private double price;

	private double dis_percentage;

   private double tax_include_price;
   
   private double price_tot_amt;
   
   private double tax_qty_amt;

	private double tax_amt;

	private double amount;
	
	private long unit_id;

	public long getSaleslist_id() {
		return saleslist_id;
	}

	public void setSaleslist_id(long saleslist_id) {
		this.saleslist_id = saleslist_id;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getShipped_qty() {
		return shipped_qty;
	}

	public void setShipped_qty(int shipped_qty) {
		this.shipped_qty = shipped_qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDis_percentage() {
		return dis_percentage;
	}

	public void setDis_percentage(double dis_percentage) {
		this.dis_percentage = dis_percentage;
	}

	public double getTax_include_price() {
		return tax_include_price;
	}

	public void setTax_include_price(double tax_include_price) {
		this.tax_include_price = tax_include_price;
	}

	public double getPrice_tot_amt() {
		return price_tot_amt;
	}

	public void setPrice_tot_amt(double price_tot_amt) {
		this.price_tot_amt = price_tot_amt;
	}

	public double getTax_qty_amt() {
		return tax_qty_amt;
	}

	public void setTax_qty_amt(double tax_qty_amt) {
		this.tax_qty_amt = tax_qty_amt;
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

	public long getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(long unit_id) {
		this.unit_id = unit_id;
	}

	public SalesList(long saleslist_id, String categoryid, long productid, int qty, int shipped_qty, double price,
			double dis_percentage, double tax_include_price, double price_tot_amt, double tax_qty_amt, double tax_amt,
			double amount, long unit_id) {
		super();
		this.saleslist_id = saleslist_id;
		this.categoryid = categoryid;
		this.productid = productid;
		this.qty = qty;
		this.shipped_qty = shipped_qty;
		this.price = price;
		this.dis_percentage = dis_percentage;
		this.tax_include_price = tax_include_price;
		this.price_tot_amt = price_tot_amt;
		this.tax_qty_amt = tax_qty_amt;
		this.tax_amt = tax_amt;
		this.amount = amount;
		this.unit_id = unit_id;
	}

	public SalesList() {
		super();
	}


	
	
	
	

}

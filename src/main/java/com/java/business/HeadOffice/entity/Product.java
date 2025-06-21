package com.java.business.HeadOffice.entity;


import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productid;

	@NotNull(message = "Send me the category id")
	@Column(name = "categoryid", nullable = false)
	private long categoryid;

	@Size(min = 1, max = 400)
	@Column(name = "productdescription", nullable = true)
	private String productdescription;

	@NotNull(message = "Code Already Exist")
	@Column(name = "hsn_code", nullable = false)
	private String hsn_code;

	@NotNull(message = "Please enter the product name")
	@Size(min = 1, max = 80)
	@Column(name = "productname", nullable = false)
	private String productname;

	private String fileName;
	private String fileType;

	@Lob
	private byte[] data;


	private long unit_id;

	@Column(name = "stock")
	private long stock;

	@Column(name = "stock_in")
	private long stockIn;
	
	@Column(name = "stock_out")
	private long stockOut;

	public Product(long stockIn, long stockOut) {
		super();
		this.stockIn = stockIn;
		this.stockOut = stockOut;
	}

	public long getStockIn() {
		return stockIn;
	}

	public void setStockIn(long stockIn) {
		this.stockIn = stockIn;
	}

	public long getStockOut() {
		return stockOut;
	}

	public void setStockOut(long stockOut) {
		this.stockOut = stockOut;
		}

	private double tax_percentage;

	public Product(double tax_percentage) {
		super();
		this.tax_percentage = tax_percentage;
	}

	public double getTax_percentage() {
		return tax_percentage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setTax_percentage(double tax_percentage) {
		this.tax_percentage = tax_percentage;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public String getProductdescription() {
		return productdescription;
	}

	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}

	public String getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	
	public long getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(long unit_id) {
		this.unit_id = unit_id;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public Product() {
		super();
	}

	public Product(long productid, @NotNull(message = "Send me the category id") long categoryid,
			@Size(min = 1, max = 400) String productdescription,
			@NotNull(message = "Code Already Exist") String hsn_code,
			@NotNull(message = "Please enter the product name") @Size(min = 1, max = 80) String productname,
			String fileName, String fileType, byte[] data, long unit_id, long stock, long stockIn, long stockOut,
			double tax_percentage) {
		super();
		this.productid = productid;
		this.categoryid = categoryid;
		this.productdescription = productdescription;
		this.hsn_code = hsn_code;
		this.productname = productname;
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.unit_id = unit_id;
		this.stock = stock;
		this.stockIn = stockIn;
		this.stockOut = stockOut;
		this.tax_percentage = tax_percentage;
	}

	@Override
	public String toString() {
		return "Product [productid=" + productid + ", categoryid=" + categoryid + ", productdescription="
				+ productdescription + ", hsn_code=" + hsn_code + ", productname=" + productname + ", fileName="
				+ fileName + ", fileType=" + fileType + ", data=" + Arrays.toString(data) + ", unit_id=" + unit_id
				+ ", stock=" + stock + ", stockIn=" + stockIn + ", stockOut=" + stockOut + ", tax_percentage="
				+ tax_percentage + "]";
	}

	


}

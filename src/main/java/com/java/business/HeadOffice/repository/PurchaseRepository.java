package com.java.business.HeadOffice.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.business.HeadOffice.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

	@Query(value="select * from purchase as p join outsource as o on p.companyid=o.companyid", nativeQuery = true)
	List<Map<String, Object>> getAllPurchase();

	
	@Query(value="select p.purchase_id,p.balance,p.description,p.payment_type,p.phone_number,p.purchase_date,p.purchase_number,"
			+ "p.received,p.roundoff,p.roundoff_amount,p.total_amount,p.total_price,p.total_qty,p.total_tax,"
			+ "u.unitname,pro.productname,pro.stock,pro.hsn_code,pro.productdescription,pro.categoryid,pro.tax_percentage,pl.purchase_list_id,pl.inc_tax_price,pl.tax_qty_amount,pl.price_tot_amt,"
			+ "pl.price,pl.productid,pl.qty,pl.shipped_qty,pl.tax_amt,"
			+ "pl.fk_purchase_id,pl.unit_id,pl.amount,c.category_id,c.category,o.* from purchase as p join "
			+ "outsource as o on p.companyid=o.companyid"
			+ " join purchaselist as pl on p.purchase_id=pl.fk_purchase_id"
			+ " join product as pro on pro.productid=pl.productid"
			+ " join category as c on pl.categoryid=c.category_id"
			+ " join unit as u on pl.unit_id=u.unit_id where p.companyid=:companyid",nativeQuery = true)
	List<Map<String, Object>> getAllOutsource(long companyid);

	@Query(value="select p.purchase_id,p.balance,p.description,p.payment_type,p.phone_number,p.purchase_date,p.purchase_number,"
			+ "p.received,p.invoice_no,p.roundoff,p.roundoff_amount,p.total_amount,p.total_price,p.total_qty,p.total_tax,"
			+ "u.unitname,pro.productname,pro.stock,pro.hsn_code,pro.productdescription,pro.categoryid,pro.tax_percentage,pl.purchase_list_id,pl.inc_tax_price,pl.tax_qty_amount,pl.price_tot_amt,"
			+ "pl.price,pl.productid,pl.qty,pl.shipped_qty,pl.amount,"
			+ "pl.tax_amt,pl.fk_purchase_id,"
			+ "pl.unit_id,c.category_id,c.category,o.* from purchase as p join outsource as o on p.companyid=o.companyid"
			+ " join purchaselist as pl on p.purchase_id=pl.fk_purchase_id"
			+ " join product as pro on pro.productid=pl.productid"
			+ " join category as c on pl.categoryid=c.category_id"
			+ " join unit as u on pl.unit_id=u.unit_id"
			+ "  ORDER BY p.purchase_id DESC  ",nativeQuery = true)
	List<Map<String, Object>> getAllOutsource();
	

	@Query(value="select p.purchase_id, p.balance, p.description, p.payment_type, p.phone_number, p.purchase_date, p.purchase_number,"
			+ "       p.received, p.invoice_no, p.roundoff, p.roundoff_amount, p.total_amount, p.total_price, p.total_qty, p.total_tax,"
			+ "       u.unitname, pro.productname, pro.stock, pro.hsn_code, pro.productdescription, pro.categoryid, pro.tax_percentage,"
			+ "       pl.purchase_list_id, pl.inc_tax_price, pl.tax_qty_amount, pl.price_tot_amt, pl.price, pl.productid, pl.qty,"
			+ "       pl.shipped_qty, pl.amount, pl.tax_amt, pl.fk_purchase_id,"
			+ "       c.category_id, c.category, o.*"
			+ "from purchase as p"
			+ " join outsource as o on p.companyid = o.companyid"
			+ " join purchaselist as pl on p.purchase_id = pl.fk_purchase_id"
			+ " join product as pro on pro.productid = pl.productid"
			+ " join category as c on pl.categoryid = c.category_id"
			+ " join unit as u on pl.unit_id = u.unit_id"
			+ " where p.purchase_date between :startDate and :endDate"
			+ "", nativeQuery = true)
	
	List<Map<String, Object>> findAllPurchaseBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate); 
	
		@Query(value = "select purchase_number  from purchase order by purchase_number desc limit 1", nativeQuery = true)
		Integer getLastPurchaseNumber();
		
}

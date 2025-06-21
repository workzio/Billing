package com.java.business.HeadOffice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.business.HeadOffice.entity.SalesList;

public interface SalesListRepository extends JpaRepository<SalesList, Long> {

	@Query(value = "select s.sales_id,d.code, tax_percentage, sum(tax_qty_amt) as tax from sales as s"
			+ " join saleslist as sl on s.sales_id = sl.fk_sales_id"
			+ " JOIN distributor AS d ON s.distributorid = d.distributorid"
			+ " join product as p on sl.productid=p.productid where sales_id=:sales_id group by s.sales_id,d.code, tax_percentage", nativeQuery = true)
	List<Map<String, Object>> getGstByTax(long sales_id);

	@Query(value = "select s.sales_id,d.code, tax_percentage, sum(tax_qty_amt) as tax from sales as s"
			+ " join saleslist as sl on s.sales_id = sl.fk_sales_id"
			+ " JOIN distributor AS d ON s.distributorid = d.distributorid"
			+ " join product as p on sl.productid=p.productid group by s.sales_id,d.code, tax_percentage", nativeQuery = true)
	List<Map<String, Object>> getGstByTax();
	
	
	@Query(value = "select s.sales_id,d.code, tax_percentage, sum(tax_qty_amt) as tax from sales as s"
			+ " join saleslist as sl on s.sales_id = sl.fk_sales_id"
			+ " JOIN distributor AS d ON s.distributorid = d.distributorid"
			+ " join product as p on sl.productid=p.productid where s.distributorid = :distributorid group by s.sales_id,d.code, tax_percentage", nativeQuery = true)
	List<Map<String, Object>> getGstByTaxDistributor(long distributorid);

}

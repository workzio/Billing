package com.java.business.HeadOffice.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.business.HeadOffice.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {
	@Query(value = " select* from sales as s join distributor as d on s.distributorid=d.distributorid", nativeQuery = true)
	List<Map<String, Object>> getAllSales();

	@Query(value = "select s.sales_id, s.balance, s.description, s.invoice_date, s.invoice_no, s.payment_type, s.received,"
			+ "s.roundoff, s.roundoff_amount, s.shipping_address, s.total_amount, s.total_price	,  s.total_qty, s.total_tax, s.phone_number,"
			+ "p.productdescription,p.hsn_code,p.stock,p.tax_percentage,p.productname, c.category, d.*, sl.*, u.unitname"
			+ " from sales as s" + " join distributor as d on s.distributorid = d.distributorid"
			+ " join saleslist as sl on s.sales_id = sl.fk_sales_id"
			+ " join product as p on p.productid = sl.productid"
			+ " join category as c on sl.categoryid = c.category_id" + " join unit as u on sl.unit_id = u.unit_id"
			+ " where s.distributorid = :distributorid", nativeQuery = true)
	List<Map<String, Object>> getAllDistributorDetails(long distributorid);
	

	@Query(value = "select s.sales_id, s.balance, s.description, s.invoice_date, s.invoice_no, s.payment_type, s.received,"
			+ "s.roundoff, s.roundoff_amount, s.shipping_address, s.total_amount,  s.total_qty,s.total_price, s.total_tax, s.phone_number,"
			+ "p.productdescription,p.hsn_code,p.stock,p.tax_percentage,p.productname, c.category, d.*, sl.*, u.unitname"
			+ " from sales as s" + " join distributor as d on s.distributorid = d.distributorid"
			+ " join saleslist as sl on s.sales_id = sl.fk_sales_id"
			+ " join product as p on p.productid = sl.productid"
			+ " join category as c on sl.categoryid = c.category_id" + " join unit as u on sl.unit_id = u.unit_id"
			, nativeQuery = true)
	List<Map<String, Object>> getAllDistributorDetails();
	
	@Query(value = " select"
			+ "    s.sales_id, s.balance, s.description, s.invoice_date, s.invoice_no, s.payment_type, s.received,"
			+ "    s.roundoff, s.roundoff_amount, s.shipping_address, s.total_amount, s.total_qty, s.total_price, s.total_tax, s.phone_number,"
			+ "    p.productdescription, p.hsn_code, p.stock, p.tax_percentage, p.productname,"
			+ "    c.category,"
			+ "    d.*,"
			+ "    sl.*,"
			+ "    u.unitname,"
			+ "    (case when s.balance = 0 then true else false end) as paidbill"
			+ " from"
			+ "    sales as s"
			+ " join"
			+ "    distributor as d on s.distributorid = d.distributorid"
			+ " join"
			+ "    saleslist as sl on s.sales_id = sl.fk_sales_id"
			+ " join"
			+ "    product as p on p.productid = sl.productid"
			+ " join"
			+ "    category as c on sl.categoryid = c.category_id"
			+ " join"
			+ "    unit as u on sl.unit_id = u.unit_id"
			+ " WHERE s.distributorid = :distributorid"
			, nativeQuery = true)
	List<Map<String, Object>> getAllPaidBillDetailsByDistributor(long distributorid);
	
	
	@Query(value = " select"
			+ "    s.sales_id, s.balance, s.description, s.invoice_date, s.invoice_no, s.payment_type, s.received,"
			+ "    s.roundoff, s.roundoff_amount, s.shipping_address, s.total_amount, s.total_qty, s.total_price, s.total_tax, s.phone_number,"
			+ "    p.productdescription, p.hsn_code, p.stock, p.tax_percentage, p.productname,"
			+ "    c.category,"
			+ "    d.*,"
			+ "    sl.*,"
			+ "    u.unitname,"
			+ "    (case when s.balance = 0 then true else false end) as paidbill"
			+ " from"
			+ "    sales as s"
			+ " join"
			+ "    distributor as d on s.distributorid = d.distributorid"
			+ " join"
			+ "    saleslist as sl on s.sales_id = sl.fk_sales_id"
			+ " join"
			+ "    product as p on p.productid = sl.productid"
			+ " join"
			+ "    category as c on sl.categoryid = c.category_id"
			+ " join"
			+ "    unit as u on sl.unit_id = u.unit_id"
			
			, nativeQuery = true)
	List<Map<String, Object>> getAllPaidBillDetails();
	

	@Query(value = "SELECT invoice_no  FROM sales ORDER BY invoice_no DESC LIMIT 1", nativeQuery = true)
	Integer getLastInvoiceNumber();

	@Query(value = "  select s.*, sl.*, d.address,d.code,d.date,d.district,d.email,d.gstno,d.name,d.phoneno,d.pincode,d.state from distributor as d"
			+ " left join sales as s on d.distributorid = s.distributorid"
			+ " left join saleslist as sl on s.sales_id = sl.fk_sales_id", nativeQuery = true)
	List<Map<String, Object>> findAllBySalesDetails();

	@Query(value = "select"
			+ "	row_number() over (order by sub.Date) as id,"
			+ "	 sub.Date,"
			+ "	 sub.billNumber,"
			+ "	 sub.particulars,"
			+ " sub.credit,"
			+ " sub.debit"
			+ " from ("
			+ "	select "
			+ "	rp.invoice_date as Date,"
			+ "	rp.invoice_no as billNumber,"
			+ "	'sales' as particulars,"
			+ "	rp.current_received as credit,"
			+ "	 0 AS debit"
			+ "	 from returnpayment rp"
			+ "	 where DATE(rp.invoice_date) = curdate()"
			+ "	 union all "
			+ "	 select"
			+ "	 p.purchase_date as Date,"
			+ "	 p.invoice_no as billNumber,"
			+ "	'purchase' as particulars,"
			+ "	0 AS credit,"
			+ "	 p.balance as debit"
			+ "	 from purchase p "
			+ "	 where DATE(p.purchase_date) = curdate()"
			+ "	 union all "
			+ "	 select"
			+ "	 r.date as Date,"
			+ "	 r.receiptno as billNumber,"
			+ "	'receipt' as particulars,"
			+ "	 r.amount as credit,"
			+ "	 0 AS debit"
			+ "	 from receipt r"
			+ "	 where DATE(r.date) = curdate()"
			+ "	 union all"
			+ "	 select"
			+ "	 v.date as Date,"
			+ "	 v.voucherno AS billNumber,"
			+ "	 'voucher' AS particulars,"
			+ "	 0 as credit,"
			+ "	 v.amount as debit"
			+ "	 from voucher v "
			+ "	 where DATE(v.date) = curdate()"
			+ "	 union all"
			+ "	 select "
			+ "	 sa.salary_date as Date,"
			+ "	 sa.salary_id as billNumber,"
			+ "	 'salary' as particulars,"
			+ "	 0 as credit,"
			+ "	 sa.amount as debit"
			+ "	from salary as sa"
			+ "	 where DATE(sa.salary_date) = curdate()"
			+ "	 union all"
			+ "	 select"
			+ "	 n.date as Date,"
			+ "	 n.new_bank_id as billNumber,"
			+ "	 'bank online transcation' as particulars,"
			+ "	 case when n.payment_type = 'credit' then n.amount else 0 end as credit, "
			+ "	 case when n.payment_type = 'debit' then n.amount else 0 end as debit"
			+ "	 from newbank as n"
			+ "	 where DATE(n.date) = curdate() and (n.payment_type = 'credit' or n.payment_type = 'debit')"
			+ "	 union all"
			+ "	select"
			+ "	 ni.in_transcation_date as Date,"
			+ "	 ni.in_transcation_id as billNumber,"
			+ "	 'bank internal transcation' as particulars,"
			+ "	 case when ni.payment_type = 'credit' then ni.in_transcation_amount else 0 end as credit,"
			+ "	 case when ni.payment_type = 'debit' then ni.in_transcation_amount else 0 end as debit"
			+ "	 from intranscation as ni"
			+ "	 where DATE(ni.in_transcation_date) = curdate() and (ni.payment_type = 'credit' or ni.payment_type = 'debit')"
			+ "	) as sub"
			+ "	order by sub.Date",
	nativeQuery = true)
	List<Map<String, Object>> getPurchaseAndSales();
	
	


	@Query(value = "select "
	+ " row_number() over (order by sub.Date) as id,"
	+ " sub.Date,"
	+ " sub.billNumber,"
	+ " sub.particulars,"
	+ " sub.credit,"
	+ " sub.debit "
	+ "from ("
	+ " select "
	+ " rp.invoice_date as Date,"
	+ " rp.invoice_no as billNumber,"
	+ " 'sales' as particulars,"
	+ " rp.current_received as credit, "
	+ " 0 AS debit "
	+ " from returnpayment rp"
	+ " where DATE(rp.invoice_date) = :startDate"
	+ " union all "
	+ " select "
	+ " p.purchase_date as Date,"
	+ " p.invoice_no as billNumber, "
	+ " 'purchase' as particulars, "
	+ " 0 AS credit,"
	+ " p.balance as debit "
	+ " from purchase p "
	+ " where DATE(p.purchase_date) = :startDate"
	+ " union all "
	+ " select "
	+ " r.date as Date, "
	+ " r.receiptno as billNumber,"
	+ " 'receipt' as particulars, "
	+ " r.amount as credit, "
	+ " 0 AS debit "
	+ " from receipt r "
	+ " where DATE(r.date) = :startDate"
	+ " union all "
	+ " select "
	+ " v.date as Date, "
	+ " v.voucherno AS billNumber, "
	+ " 'voucher' AS particulars, "
	+ " 0 as credit, "
	+ " v.amount as debit "
	+ " from voucher v "
	+ " where DATE(v.date) = :startDate"
	+ " union all "
	+ " select "
	+ " sa.salary_date as Date, "
	+ " sa.salary_id as billNumber, "
	+ " 'salary' as particulars, "
	+ " 0 as credit, "
	+ " sa.amount as debit "
	+ " from salary as sa "
	+ " where DATE(sa.salary_date) = :startDate"
	+ " union all "
	+ " select"
	+ " n.date as Date, "
	+ " n.new_bank_id as billNumber, "
	+ " 'bank online transcation' as particulars, "
	+ " case when n.payment_type = 'credit' then n.amount else 0 end as credit, "
	+ " case when n.payment_type = 'debit' then n.amount else 0 end as debit"
	+ " from newbank as n "
	+ " where DATE(n.date) = :startDate and (n.payment_type = 'credit' or n.payment_type = 'debit')"
	+ " union all "
	+ " select"
	+ " ni.in_transcation_date as Date, "
	+ " ni.in_transcation_id as billNumber, "
	+ " 'bank internal transcation' as particulars, "
	+ " case when ni.payment_type = 'credit' then ni.in_transcation_amount else 0 end as credit, "
	+ " case when ni.payment_type = 'debit' then ni.in_transcation_amount else 0 end as debit"
	+ " from intranscation ni "
	+ " where DATE(ni.in_transcation_date) = :startDate and (ni.payment_type = 'credit' or ni.payment_type = 'debit')"
	+ ") as sub "
	+ "order by sub.Date",
	nativeQuery = true)
	List<Map<String, Object>> getDayBookByStartDate(LocalDate startDate);


	@Query(value = "select s.sales_id, s.balance, s.description, s.invoice_date, s.invoice_no, s.payment_type, s.received,"
			+ "s.roundoff, s.roundoff_amount, s.shipping_address, s.total_amount,  s.total_qty,s.total_price, s.total_tax, s.phone_number,"
			+ "p.productdescription,p.hsn_code,p.stock,p.tax_percentage,p.productname, c.category, d.*, sl.*, u.unitname"
			+ " from sales as s"
			+ " join distributor as d on s.distributorid = d.distributorid"
			+ " join saleslist as sl on s.sales_id = sl.fk_sales_id"
			+ " join product as p on p.productid = sl.productid"
			+ " join category as c on sl.categoryid = c.category_id" + " join unit as u on sl.unit_id = u.unit_id"
			+ "  where s.invoice_date between :startDate and :endDate", nativeQuery = true)
	List<Map<String, Object>> getAllSalesBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}

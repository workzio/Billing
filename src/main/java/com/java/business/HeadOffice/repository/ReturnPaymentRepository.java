package com.java.business.HeadOffice.repository;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.business.HeadOffice.entity.ReturnPayment;

@Repository
public interface ReturnPaymentRepository extends JpaRepository<ReturnPayment, Long> {



	@Query(value = "select s.sales_id, s.balance, s.invoice_date,r.received,"
			+ " d.distributorid,r.current_received,r.status, r.return_payment_type,r.payment_date,r.payment_id,s.received as old_received"
			+ " from sales as s join distributor as d on s.distributorid = d.distributorid"
			+ " left join returnpayment as r on r.sales_id=s.sales_id"
			+ " where s.sales_id = :sales_id", nativeQuery = true)
	List<Map<String, Object>> getAllPaymentDetails(Long sales_id);


}

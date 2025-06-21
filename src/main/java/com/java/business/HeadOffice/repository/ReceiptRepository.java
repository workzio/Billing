package com.java.business.HeadOffice.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.business.HeadOffice.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

	@Query(value = "select receiptno " + "from receipt " + "order by receiptno desc " + "limit 1", nativeQuery = true)
	Integer getLastReceiptNumber();

	List<Receipt> findAllByDate(java.util.Date date);

	@Query(value = "select * from receipt r order by r.receipt_id desc", nativeQuery = true)
	Iterable<Receipt> findAllByOrderByDesc();

	@Query(value = "select r.date as date, r.receipt_id as receiptId, r.amount as amount, r.bank as bank,"
			+ " r.cheque_no as chequeNo, r.payment_type as paymentType, r.receiptno as receiptNo, r.received as received ,"
			+ " r.towards as towards from receipt as r where r.date between :startDate and :endDate", nativeQuery = true)
	List<Map<String, Object>> getAllReceiptBetweenDates(LocalDate startDate, LocalDate endDate);

//	@Query(value = "select r.*,o.dealername,d.name from receipt as r"
//			+ " join outsource as o on o.companyid = r.companyid"
//			+ " join distributor as d on d.distributorid = r.distributorid", nativeQuery = true)
//	List<Map<String, Object>> getAllReceiptDetails();

	@Query(value = "select r.receipt_id,r.towards,r.date,r.cheque_no as chequeNo,r.receiptno as receiptNo,"
			+ " r.distributorid,r.status,o.dealername," + " r.payment_type as paymentType,r.received,r.bank,"
			+ " r.companyid,r.receipt_type as receiptType,r.amount from receipt as r"
			+ " join outsource as o on o.companyid = r.companyid", nativeQuery = true)
	List<Map<String, Object>> getAllReceiptDetailsCompany();

	@Query(value = "select r.receipt_id,r.towards,r.date,r.cheque_no as chequeNo,r.receiptno as receiptNo,"
			+ " r.distributorid,r.status,o.dealername," + " r.payment_type as paymentType,r.received,r.bank,"
			+ " r.companyid,r.receipt_type as receiptType,r.amount from receipt as r"
			+ " join outsource as o on o.companyid = r.companyid"
			+ " where r.date between :startDate and :endDate", nativeQuery = true)
	List<Map<String, Object>> getAllReceiptDetailsCompanyByDate(LocalDate startDate, LocalDate endDate);

	@Query(value = "select r.receipt_id,r.towards,r.date,r.cheque_no as chequeNo,r.receiptno as receiptNo,"
			+ " r.distributorid,r.status,d.name," + " r.payment_type as paymentType,r.received,r.bank,"
			+ " r.companyid,r.receipt_type as receiptType,r.amount from receipt as r"
			+ " join distributor as d on d.distributorid = r.distributorid", nativeQuery = true)
	List<Map<String, Object>> getAllReceiptDetailsDistributor();

	@Query(value = "select r.receipt_id,r.towards,r.date,r.cheque_no as chequeNo,r.receiptno as receiptNo,"
			+ " r.distributorid,r.status,d.name," + " r.payment_type as paymentType,r.received,r.bank,"
			+ " r.companyid,r.receipt_type as receiptType,r.amount from receipt as r"
			+ " join distributor as d on d.distributorid = r.distributorid"
			+ " where r.date between :startDate and :endDate", nativeQuery = true)
	List<Map<String, Object>> getAllReceiptDetailsDistributorByDate(LocalDate startDate, LocalDate endDate);

	@Query(value = "select r.receipt_id,r.towards,r.date,r.cheque_no as chequeNo,r.receiptno as receiptNo,"
			+ " r.distributorid,r.status,d.name," + " r.payment_type as paymentType,r.received,r.bank,"
			+ " r.companyid,r.receipt_type as receiptType,r.amount from receipt as r"
			+ " join distributor as d on d.distributorid = r.distributorid where r.distributorid = :distributorid", nativeQuery = true)
	List<Map<String, Object>> getAllReceiptDetailsByDistributor(Long distributorid);

	@Query(value = "select r.receipt_id,r.towards,r.date,r.cheque_no as chequeNo,r.receiptno as receiptNo,"
			+ " r.distributorid,r.status,o.dealername," + " r.payment_type as paymentType,r.received,r.bank,"
			+ " r.companyid,r.receipt_type as receiptType,r.amount from receipt as r"
			+ " join outsource as o on o.companyid = r.companyid where r.companyid=:companyid", nativeQuery = true)
	List<Map<String, Object>> getAllReceiptDetailsByCompany(Long companyid);
}

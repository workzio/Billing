package com.java.business.HeadOffice.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.java.business.HeadOffice.entity.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

	@Query(value = "select voucherno " + "from voucher " + "order by voucherno desc " + "limit 1", nativeQuery = true)
	Integer getLastVoucherNumber();

	List<Voucher> findAllByDate(java.util.Date date);

	@Query(value = "select * from voucher v order by v.voucher_id desc", nativeQuery = true)
	List<Voucher> getVouchersInDescendingOrder();

	@Query(value = "select v.date as date, v.voucher_id as voucherId, v.voucherno as voucherNo, v.paid_to as paidTo, v.return_type as returnType, v.particulars, v.amount from voucher as v where v.date between :startDate and :endDate", nativeQuery = true)
	List<Map<String, Object>> getAllVoucherBetweenDates(LocalDate startDate, LocalDate endDate);

	@Query(value = "select v.voucherno as voucherNo,v.voucher_id,v.date,v.paid_to as paidTo,"
			+ "	 v.particulars,v.amount,v.return_type as returnType,v.distributorid,"
			+ "	 v.companyid,v.voucher_type as voucherType,v.towards,o.dealername from voucher as v"
			+ "	join outsource as o on o.companyid = v.companyid", nativeQuery = true)
	List<Map<String, Object>> getAllVoucherCompanyDetails();

	@Query(value = "select v.voucherno as voucherNo,v.voucher_id,v.date,v.paid_to as paidTo,"
			+ "	 v.particulars,v.amount,v.return_type as returnType,v.distributorid,"
			+ "	 v.companyid,v.voucher_type as voucherType,v.towards,o.dealername from voucher as v"
			+ "	join outsource as o on o.companyid = v.companyid"
			+ " where v.date between :startDate and :endDate", nativeQuery = true)
	List<Map<String, Object>> getAllVoucherCompanyDetailsByDate(LocalDate startDate, LocalDate endDate);

	@Query(value = "select v.voucherno as voucherNo,v.voucher_id,v.date,v.paid_to as paidTo,"
			+ " v.particulars,v.amount,v.return_type as returnType,v.distributorid,"
			+ " v.companyid,v.voucher_type as voucherType,v.towards,d.name from voucher as v"
			+ " join distributor as d on d.distributorid = v.distributorid", nativeQuery = true)
	List<Map<String, Object>> getAllVoucherDistributorDetails();

	@Query(value = "select v.voucherno as voucherNo,v.voucher_id,v.date,v.paid_to as paidTo,"
			+ "	v.particulars,v.amount,v.return_type as returnType,v.distributorid,"
			+ "	v.companyid,v.voucher_type as voucherType,v.towards,d.name from voucher as v"
			+ "	join distributor as d on d.distributorid = v.distributorid"
			+ " where v.date between :startDate and :endDate", nativeQuery = true)
	List<Map<String, Object>> getAllVoucherDistributorDetailsByDate(LocalDate startDate, LocalDate endDate);

	@Query(value = "select v.voucherno as voucherNo,v.voucher_id,v.date,v.paid_to as paidTo,"
			+ "	 v.particulars,v.amount,v.return_type as returnType,v.distributorid,"
			+ "	 v.companyid,v.voucher_type as voucherType,v.towards,o.dealername from voucher as v"
			+ "	join outsource as o on o.companyid = v.companyid"
			+ " where v.companyid=:companyid", nativeQuery = true)
	List<Map<String, Object>> getAllVoucherDetailsByCompany(Long companyid);

	@Query(value = "select v.voucherno as voucherNo,v.voucher_id,v.date,v.paid_to as paidTo,"
			+ "	 v.particulars,v.amount,v.return_type as returnType,v.distributorid,"
			+ "	 v.companyid,v.voucher_type as voucherType,v.towards,d.name from voucher as v"
			+ "	join distributor as d on d.distributorid = v.distributorid"
			+ " where v.distributorid=:distributorid", nativeQuery = true)
	List<Map<String, Object>> getAllVoucherDetailsByDistributor(Long distributorid);
}

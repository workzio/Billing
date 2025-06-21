package com.java.business.HeadOffice.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.business.HeadOffice.entity.NewBank;

public interface NewBankRepository extends JpaRepository<NewBank, Long>{

	
	@Query(value="	select  nb.new_bank_id, nb.amount, nb.client_bank_id, nb.own_bank_id, nb.date, nb.payment_type,"
			+ "    b1.bank_name as own_bank_name, b1.branch_name as own_branch_name, b1.ifsc_code as own_ifsc_code, b1.account_holder_name as own_account_holder_name, b1.account_number as own_account_number, b1.phone_number as own_phone_number,"
			+ "    b.account_holder_name as customer_account_name, b.bank_name as customer_bank_name,b.ifsc_code as customer_ifsc_code, b.branch_name as customer_branch_name, b.account_number as customer_account_number, b.phone_number as customer_phone_number"
			+ " from newbank as nb"
			+ " join addbank as b on b.bank_id = nb.client_bank_id"
			+ " join addbank as b1 on b1.bank_id = nb.own_bank_id",nativeQuery = true)
	List<Map<String, Object>> getAllDetailsOfOwnAndCustomerAccount();
	
	@Query(value="	select  nb.new_bank_id, nb.amount, nb.client_bank_id, nb.own_bank_id, nb.date, nb.payment_type,"
			+ "    b1.bank_name as own_bank_name, b1.branch_name as own_branch_name, b1.ifsc_code as own_ifsc_code, b1.account_holder_name as own_account_holder_name, b1.account_number as own_account_number, b1.phone_number as own_phone_number,"
			+ "    b.account_holder_name as customer_account_name, b.bank_name as customer_bank_name, b.branch_name as customer_branch_name,b.ifsc_code as customer_ifsc_code, b.account_number as customer_account_number, b.phone_number as customer_phone_number"
			+ " from newbank as nb"
			+ " join addbank as b on b.bank_id = nb.client_bank_id"
			+ " join addbank as b1 on b1.bank_id = nb.own_bank_id where nb.date between :startdate and :enddate ",nativeQuery = true)
	List<Map<String, Object>> getAllDetailsOfOwnAndCustomerAccountByDate(LocalDate startdate, LocalDate enddate);
	
	@Query(value="	select  nb.new_bank_id, nb.amount, nb.client_bank_id, nb.own_bank_id, nb.date, nb.payment_type,"
			+ "    b1.bank_name as own_bank_name, b1.branch_name as own_branch_name, b1.ifsc_code as own_ifsc_code, b1.account_holder_name as own_account_holder_name, b1.account_number as own_account_number, b1.phone_number as own_phone_number,"
			+ "    b.account_holder_name as customer_account_name, b.bank_name as customer_bank_name, b.branch_name as customer_branch_name,b.ifsc_code as customer_ifsc_code, b.account_number as customer_account_number, b.phone_number as customer_phone_number"
			+ " from newbank as nb"
			+ " join addbank as b on b.bank_id = nb.client_bank_id"
			+ " join addbank as b1 on b1.bank_id = nb.own_bank_id where nb.new_bank_id = :new_bank_id",nativeQuery = true)
	List<Map<String, Object>> getAllNewBankById(@Param("new_bank_id") Long new_bank_id);
}

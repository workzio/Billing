package com.java.business.HeadOffice.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.business.HeadOffice.entity.AddBank;

public interface AddBankRepository extends JpaRepository<AddBank, Long> {

	@Query(value = "select b.*,it.in_transcation_amount,it.payment_type,it.in_transcation_date,it.in_transcation_id from intranscation as it"
			+ " join addbank as b on b.bank_id = it.bank_id", nativeQuery = true)
	List<Map<String, Object>> getAllInTranscationDetails();

	@Query(value = "select b.*,it.in_transcation_amount,it.payment_type,it.in_transcation_date,it.in_transcation_id from intranscation as it"
			+ " join addbank as b on b.bank_id = it.bank_id where it.in_transcation_date between :startdate and :enddate", nativeQuery = true)
	List<Map<String, Object>> getAllInTranscationDetailsByTime(LocalDate startdate, LocalDate enddate);

	@Query(value = "select b.*,it.in_transcation_amount,it.payment_type,it.in_transcation_date,it.in_transcation_id from intranscation as it"
			+ " join addbank as b on b.bank_id = it.bank_id where it.in_transcation_id = :in_transcation_id", nativeQuery = true)
	List<Map<String, Object>> getAllInTranscationDetailsById(@Param("in_transcation_id") Long in_transcation_id);

	List<AddBank> findByBankStatusTrue();

	List<AddBank> findByBankStatusFalse();

}

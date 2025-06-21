package com.java.business.HeadOffice.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.business.HeadOffice.entity.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

	@Query(value = "select s.*,m.aadharno,m.address,m.description,m.dob,m.email,m.gender,m.name,m.pan_no,"
			+ " m.password,m.phone,r.role_name from salary as s" + " join member as m on m.memberid = s.memberid"
			+ " join role as r on r.role_id =s.role_id", nativeQuery = true)
	List<Map<String, Object>> getAllSalaryDetails();
	
	@Query(value="select s.*,m.aadharno,m.address,m.description,m.dob,m.email,m.gender,m.name,m.pan_no,"
			+ "	 m.password,m.phone,r.role_name from salary as s" + " join member as m on m.memberid = s.memberid"
			+ "	join role as r on r.role_id =s.role_id where m.memberid = :memberid",nativeQuery = true)
	List<Map<String, Object>> getAllSalaryDetailsByMember(long memberid);
	
	@Query(value="select coalesce(sum(advance_amount), 0) as advance_amount"
			+ "  from salary"
			+ "  where memberid =:memberid"
			+ "  and salary_date between date_add(cast(:salary_date as date), interval -1 month)"
			+ "  and cast(:salary_date as date)"
			+ "  and payment_type = 'advanceAmount'",nativeQuery = true)
	List<Map<String, Object>> getAllAdvanceAmountDetails(long memberid, LocalDate salary_date);
	
}

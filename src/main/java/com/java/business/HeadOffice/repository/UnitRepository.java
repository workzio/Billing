package com.java.business.HeadOffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.java.business.HeadOffice.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {


	 @Query(value = "select * from unit u order by u.unit_id desc", nativeQuery = true)
	 Iterable<Unit> findAllByOrderByDesc();
}

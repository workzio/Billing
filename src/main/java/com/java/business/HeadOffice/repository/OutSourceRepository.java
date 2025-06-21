package com.java.business.HeadOffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.java.business.HeadOffice.entity.OutSource;

public interface OutSourceRepository extends JpaRepository<OutSource, Long> {

	@Query(value = "select * from outsource o order by o.companyid desc", nativeQuery = true)
	Iterable<OutSource> findAllByOrderByDesc();
}

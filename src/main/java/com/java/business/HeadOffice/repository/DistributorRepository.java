package com.java.business.HeadOffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.java.business.HeadOffice.entity.Distributor;

public interface DistributorRepository extends JpaRepository<Distributor, Long>{

    Distributor findByPhoneno(long phoneno);
    
    
    Distributor findById(long distributorid);
 
    
    @Query(value = "select * from distributor d order by d.distributorid desc", nativeQuery = true)
	 Iterable<Distributor> findAllByOrderByDesc();
 


}

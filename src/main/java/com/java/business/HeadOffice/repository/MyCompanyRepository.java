package com.java.business.HeadOffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.java.business.HeadOffice.entity.MyCompany;

public interface MyCompanyRepository extends JpaRepository <MyCompany, Long>{

}

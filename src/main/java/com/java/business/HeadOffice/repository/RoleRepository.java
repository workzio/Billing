package com.java.business.HeadOffice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.business.HeadOffice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query(value="select m.*,r.role_name from member as m"
			+ " join role as r on r.role_id = m.role_id", nativeQuery = true)
	List<Map<String, Object>> getAllRole();
	

}

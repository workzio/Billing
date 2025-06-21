package com.java.business.HeadOffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.business.HeadOffice.entity.Role;
import com.java.business.HeadOffice.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Iterable<Role> listAll(){
		return this.roleRepository.findAll();	}

	public void Save(Role role) {
		roleRepository.save(role);
	}

	public Role getUserById(long roleId) {
		return roleRepository.findById(roleId).get();
	}

	public void update(Role role, long roleId) {
		roleRepository.save(role);
	}

	////////delete
	public void deleteMemberById(Long roleId) {
		roleRepository.deleteById(roleId);

	}

	/////// edit
	public void save(Role existingRole) {
		roleRepository.save(existingRole);
	}

	public Role findById(Long roleId) {
		return roleRepository.findById(roleId).get();
	}
	
	public List<Map<String, Object>> getAllRole(){
		return roleRepository.getAllRole();
	}
	
	
}

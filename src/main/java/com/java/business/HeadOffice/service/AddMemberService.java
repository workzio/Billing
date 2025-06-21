package com.java.business.HeadOffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.java.business.HeadOffice.entity.AddMember;
import com.java.business.HeadOffice.repository.AddMemberRepository;

@Service
public class AddMemberService {

	@Autowired
	private AddMemberRepository repo;

	public Iterable<AddMember> listAll(){
		return this.repo.findAll();	}
	
	public void Save(AddMember member) {
		repo.save(member);
	}

	public AddMember getUserById(long id) {
		return repo.findById(id).get();
	}

	public void update(AddMember member, long id) {
		repo.save(member);
	}


	////////delete
	public void deleteMemberById(Long id) {
		repo.deleteById(id);
	}

	///////edit
	public void save(AddMember existingCustomer) {
		repo.save(existingCustomer);
	}

	public AddMember findById(Long id) {
		return repo.findById(id).get();
	}

	public List<Map<String, Object>> getAllRoleId(Long memberid){
		return repo.getAllRoleId(memberid);
	}
	
	public List<Map<String, Object>> findAllMemberRole(){
		return repo.getAllMeberRole();
	}
}

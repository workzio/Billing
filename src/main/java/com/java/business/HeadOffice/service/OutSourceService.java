package com.java.business.HeadOffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.business.HeadOffice.entity.OutSource;

import com.java.business.HeadOffice.repository.OutSourceRepository;


@Service
public class OutSourceService {

	
	@Autowired
	private OutSourceRepository repo;

	public Iterable<OutSource> listAll(){
		return this.repo.findAllByOrderByDesc();	}
	
	public void Save(OutSource out) {
		repo.save(out);
	}

	public OutSource getUserById(long id) {
		return repo.findById(id).get();
	}

	public void update(OutSource out, long productid) {
		repo.save(out);
	}


	////////delete
	public void deleteMemberById(Long id) {
		repo.deleteById(id);
		
	}

	
	///////edit
	public void save(OutSource existingOut) {
		repo.save(existingOut);
	}

	public OutSource findById(Long id) {
		return repo.findById(id).get();
	}
}

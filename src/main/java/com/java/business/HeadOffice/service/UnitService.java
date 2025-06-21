package com.java.business.HeadOffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.business.HeadOffice.entity.Unit;
import com.java.business.HeadOffice.repository.UnitRepository;

@Service
public class UnitService {

	@Autowired
	private UnitRepository repo;

	public Iterable<Unit> listAll() {
		return this.repo.findAllByOrderByDesc();
	}

	public void Save(Unit category) {
		repo.save(category);
	}

	public Unit getUserById(long id) {
		return repo.findById(id).get();
	}

	public void update(Unit category, long productid) {
		repo.save(category);
	}

	////////delete
	public void deleteMemberById(Long id) {
		repo.deleteById(id);

	}

	/////// edit
	public void save(Unit existingUnit) {
		repo.save(existingUnit);
	}

	public Unit findById(Long id) {
		return repo.findById(id).get();
	}

}

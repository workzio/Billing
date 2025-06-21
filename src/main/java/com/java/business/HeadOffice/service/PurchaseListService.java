package com.java.business.HeadOffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.business.HeadOffice.entity.PurchaseList;
import com.java.business.HeadOffice.repository.PurchaseListRepository;

@Service
public class PurchaseListService {

	@Autowired
	private PurchaseListRepository repo;

	public Iterable<PurchaseList> listAll() {

		return this.repo.findAll();

	}

	public void Saveorupdate(PurchaseList purchaseList) {

		repo.save(purchaseList);

	}

	public PurchaseList getDetailById(long id) {

		return repo.findById(id).get();

	}

	public void save(PurchaseList existingPurchaseList) {

		repo.save(existingPurchaseList);

	}

	public PurchaseList findById(Long id) {

		return repo.findById(id).get();

	}

	public void deleteMyCompanyById(Long id) {

		repo.deleteById(id);

	}
}

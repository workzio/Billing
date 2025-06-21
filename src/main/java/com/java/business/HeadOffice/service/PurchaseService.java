package com.java.business.HeadOffice.service;

import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.business.HeadOffice.entity.Purchase;
import com.java.business.HeadOffice.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository repo;

	public Iterable<Purchase> listAll() {
		return this.repo.findAll();
	}

	public void Saveorupdate(Purchase purchase) {
		repo.save(purchase);
	}

	public Purchase getDetailByPurchaseId(long id) {
		return repo.findById(id).get();
	}

	public Purchase save(Purchase existingPurchase) {
		return repo.save(existingPurchase);
	}

	public Purchase findById(Long id) {
		return repo.findById(id).get();
	}

	public void deletePurchaseById(Long id) {
		repo.deleteById(id);
	}

	public List<Map<String, Object>> findAllPurchase() {
		return repo.getAllPurchase();
	}

	public long getPurchaseCount() {
		return repo.count();
	}

	public List<Map<String, Object>> getAllOutsource(long companyid) {
		return repo.getAllOutsource(companyid);

	}

	public List<Map<String, Object>> getAllSalesInvoices() {
		return repo.getAllOutsource();
	}

	public void delete(Purchase purchase) {
		repo.delete(purchase);
	}

	public Map<String, Object> getNextPurchaseNumber() {
		Integer result = repo.getLastPurchaseNumber();
		if (result == null) {
			result = 1;
		} else {
			result = result + 1;
		}

		Map<String, Object> responseData = new HashMap<>();
		responseData.put("purchaseNumber", result);
		return responseData;
	}

	public List<Map<String, Object>> findAllPurchaseBetweenDates(LocalDate startDate, LocalDate endDate) {
		return repo.findAllPurchaseBetweenDates(startDate, endDate);
	}

}

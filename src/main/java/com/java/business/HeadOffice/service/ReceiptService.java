package com.java.business.HeadOffice.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.business.HeadOffice.entity.Receipt;
import com.java.business.HeadOffice.repository.ReceiptRepository;

@Service
public class ReceiptService {

	@Autowired
	private ReceiptRepository repo;

	public Iterable<Receipt> getReceiptsInDescendingOrder() {
		return repo.findAllByOrderByDesc();
	}

	public void saveOrUpdate(Receipt receipt) {
		repo.save(receipt);
	}

	public Receipt getById(long id) {
		return repo.findById(id).orElse(null);
	}

	public void deleteById(long id) {
		repo.deleteById(id);
	}

	public long getReceiptCount() {
		return repo.count();
	}

	public List<Map<String, Object>> getAllReceiptBetweenDates(LocalDate startDate, LocalDate endDate) {
		return repo.getAllReceiptBetweenDates(startDate, endDate);
	}

	public List<Map<String, Object>> getAllReceiptDetailsCompanyByDate(LocalDate startDate, LocalDate endDate) {
		return repo.getAllReceiptDetailsCompanyByDate(startDate, endDate);
	}
	
	public List<Map<String, Object>> getAllReceiptDetailsDistributorByDate(LocalDate startDate, LocalDate endDate) {
		return repo.getAllReceiptDetailsDistributorByDate(startDate, endDate);
	}
	
	public Map<String, Object> getNextReceiptNumber() {
		Integer result = repo.getLastReceiptNumber();
		if (result == null) {
			int nextReceiptNumber = 1;
			Map<String, Object> responseData = new HashMap<>();
			responseData.put("receiptno", nextReceiptNumber);
			return responseData;
		} else {
			int nextReceiptNumber = result + 1;
			Map<String, Object> responseData = new HashMap<>();
			responseData.put("receiptno", nextReceiptNumber);
			return responseData;
		}
	}

	public List<Receipt> findAllReceiptsByDate(java.util.Date date) {
		Date sqlDate = new Date(date.getTime());
		return repo.findAllByDate(sqlDate);
	}


	public List<Map<String, Object>> getAllReceiptDetailsCompany() {
		return repo.getAllReceiptDetailsCompany();
	}

	public List<Map<String, Object>> getAllReceiptDetailsDistributor() {
		return repo.getAllReceiptDetailsDistributor();
	}

	public List<Map<String, Object>> getAllReceiptDetailsByDistributor(Long distributorid) {
		return repo.getAllReceiptDetailsByDistributor(distributorid);
	}

	public List<Map<String, Object>> getAllReceiptDetailsByCompany(Long companyid) {
		return repo.getAllReceiptDetailsByCompany(companyid);
	}
}

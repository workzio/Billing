package com.java.business.HeadOffice.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.business.HeadOffice.entity.Voucher;
import com.java.business.HeadOffice.repository.VoucherRepository;

@Service
public class VoucherService {

	@Autowired
	private VoucherRepository repo;

	public List<Voucher> listAll() {
		return repo.findAll();
	}

	public void saveOrUpdate(Voucher voucher) {
		repo.save(voucher);
	}

	public Voucher getById(long id) {
		return repo.findById(id).get();
	}

	public void deleteById(long id) {
		repo.deleteById(id);
	}


	public long getVoucherCount() {
		return repo.count();
	}

	public List<Map<String, Object>> getAllVoucherBetweenDates(LocalDate startDate, LocalDate endDate) {
		return repo.getAllVoucherBetweenDates(startDate, endDate);
	}

	public List<Map<String, Object>> getAllVoucherCompanyDetailsByDate(LocalDate startDate, LocalDate endDate) {
		return repo.getAllVoucherCompanyDetailsByDate(startDate, endDate);
	}

	public List<Map<String, Object>> getAllVoucherDistributorDetailsByDate(LocalDate startDate, LocalDate endDate) {
		return repo.getAllVoucherDistributorDetailsByDate(startDate, endDate);
	}

	public Map<String, Object> getNextVoucherNumber() {
		Integer result = repo.getLastVoucherNumber();
		if (result == null) {
			int nextVoucherNumber = 1;
			Map<String, Object> responseData = new HashMap<>();
			responseData.put("voucherno", nextVoucherNumber);
			return responseData;
		} else {
			int nextVoucherNumber = result + 1;
			Map<String, Object> responseData = new HashMap<>();
			responseData.put("voucherno", nextVoucherNumber);
			return responseData;
		}
	}

	public List<Voucher> findAllVoucherByDate(java.util.Date date) {
		Date sqlDate = new Date(date.getTime());
		return repo.findAllByDate(sqlDate);
	}

	public List<Voucher> getVouchersInDescendingOrder() {
		return repo.getVouchersInDescendingOrder();
	}

	public List<Map<String, Object>> getAllVoucherCompanyDetails() {
		return repo.getAllVoucherCompanyDetails();
	}

	public List<Map<String, Object>> getAllVoucherDistributorDetails() {
		return repo.getAllVoucherDistributorDetails();
	}

	public List<Map<String, Object>> getAllVoucherDetailsByCompany(Long companyid) {
		return repo.getAllVoucherDetailsByCompany(companyid);
	}

	public List<Map<String, Object>> getAllVoucherDetailsByDistributor(Long distributorid) {
		return repo.getAllVoucherDetailsByDistributor(distributorid);
	}
}

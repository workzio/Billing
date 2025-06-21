package com.java.business.HeadOffice.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.java.business.HeadOffice.entity.Voucher;
import com.java.business.HeadOffice.service.VoucherService;

@RestController
@CrossOrigin
public class VoucherController {

	@Autowired
	private VoucherService service;

	@GetMapping("/vouchers")
	public ResponseEntity<?> getVouchers(Voucher voucher) {
		try {

			List<Voucher> vouchers = service.getVouchersInDescendingOrder();
			return ResponseEntity.ok(vouchers);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving vouchers: " + e.getMessage());
		}
	}

	@PostMapping("/vouchers/report")
	public List<Map<String, Object>> getAllVoucherBetweenDates(@RequestBody Map<String, Object> requestBody) {
		LocalDate startDate = LocalDate.parse(requestBody.get("startDate").toString(), DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(requestBody.get("endDate").toString(), DateTimeFormatter.ISO_DATE);
		return service.getAllVoucherBetweenDates(startDate, endDate);
	}

	@PostMapping("/vouchers/company/report")
	public List<Map<String, Object>> getAllVoucherCompanyDetailsByDate(@RequestBody Map<String, Object> requestBody) {
		LocalDate startDate = LocalDate.parse(requestBody.get("startDate").toString(), DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(requestBody.get("endDate").toString(), DateTimeFormatter.ISO_DATE);
		return service.getAllVoucherCompanyDetailsByDate(startDate, endDate);
	}

	@PostMapping("/vouchers/distributor/report")
	public List<Map<String, Object>> getAllVoucherDistributorDetailsByDate(
			@RequestBody Map<String, Object> requestBody) {
		LocalDate startDate = LocalDate.parse(requestBody.get("startDate").toString(), DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(requestBody.get("endDate").toString(), DateTimeFormatter.ISO_DATE);
		return service.getAllVoucherDistributorDetailsByDate(startDate, endDate);
	}

	@GetMapping("/vouchers/last")
	public Map<String, Object> getLastVoucherNumber() {
		return service.getNextVoucherNumber();
	}

	@PostMapping("/voucher/filter")
	public List<Voucher> getReceiptsByDate(@RequestBody Voucher voucher) {
		Date date = voucher.getDate();
		return service.findAllVoucherByDate(date);
	}

	@PostMapping("/vouchers/save")
	public ResponseEntity<String> saveVoucher(@RequestBody Voucher voucher) {
		try {
			service.saveOrUpdate(voucher);
			return ResponseEntity.ok("Voucher saved with id: " + voucher.getVoucher_id());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving voucher: " + e.getMessage());
		}
	}

	@RequestMapping("/vouchers/{id}")
	public ResponseEntity<?> getVoucherById(@PathVariable(name = "id") long id) {
		try {
			Voucher voucher = service.getById(id);
			if (voucher != null) {
				return ResponseEntity.ok(voucher);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving voucher: " + e.getMessage());
		}
	}

	@PutMapping("/vouchers/edit/{id}")
	public ResponseEntity<Voucher> updateVoucher(@PathVariable("id") long id, @RequestBody Voucher voucher) {
		try {
			Voucher existingVoucher = service.getById(id);
			if (existingVoucher == null) {
				return ResponseEntity.notFound().build();
			}
			existingVoucher.setVoucherNo(voucher.getVoucherNo());
			existingVoucher.setAmount(voucher.getAmount());
			existingVoucher.setDate(voucher.getDate());
			existingVoucher.setParticulars(voucher.getParticulars());
			existingVoucher.setPaidTo(voucher.getPaidTo());
			existingVoucher.setReturnType(voucher.isReturnType());
			existingVoucher.setCompanyid(voucher.getCompanyid());
			existingVoucher.setDistributorid(voucher.getDistributorid());
			existingVoucher.setVoucherType(voucher.getVoucherType());
			existingVoucher.setTowards(voucher.getTowards());
			service.saveOrUpdate(existingVoucher);
			return ResponseEntity.ok(existingVoucher);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/vouchers/delete/{id}")
	public ResponseEntity<String> deleteVoucher(@PathVariable("id") long id) {
		try {
			service.deleteById(id);
			return ResponseEntity.ok("Voucher deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting voucher: " + e.getMessage());
		}
	}

	/////////// adding voucher with company details//////////
	@GetMapping("/voucher/company")
	public List<Map<String, Object>> getAllVoucherCompanyDetails() {
		return service.getAllVoucherCompanyDetails();
	}

	//////// adding voucher with distributor details////////////
	@GetMapping("/voucher/distributor")
	public List<Map<String, Object>> getAllVoucherDistributorDetails() {
		return service.getAllVoucherDistributorDetails();
	}

	/////////// adding voucher with company details by id//////////
	@GetMapping("/voucher/company/{companyid}")
	public List<Map<String, Object>> getAllVoucherDetailsByCompany(@PathVariable Long companyid) {
		return service.getAllVoucherDetailsByCompany(companyid);
	}

	//////// adding voucher with distributor details by id////////////
	@GetMapping("/voucher/distributor/{distributorid}")
	public List<Map<String, Object>> getAllVoucherDetailsByDistributor(@PathVariable Long distributorid) {
		return service.getAllVoucherDetailsByDistributor(distributorid);
	}
}

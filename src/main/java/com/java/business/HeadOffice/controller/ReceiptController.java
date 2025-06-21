package com.java.business.HeadOffice.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.java.business.HeadOffice.entity.Receipt;
import com.java.business.HeadOffice.service.ReceiptService;

@RestController
@CrossOrigin
public class ReceiptController {

	@Autowired
	private ReceiptService service;



	@GetMapping("/receipts")
	public ResponseEntity<?> getReceipts(Receipt receipt) {
		try {
			Iterable<Receipt> receipts = service.getReceiptsInDescendingOrder();
			return ResponseEntity.ok(receipts);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving receipts: " + e.getMessage());
		}
	}

	@PostMapping("/receipts/save")
	public ResponseEntity<String> saveReceipt(@RequestBody Receipt receipt) {
		try {
			service.saveOrUpdate(receipt);
			return ResponseEntity.ok("Receipt saved with id: " + receipt.getReceipt_id());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving receipt: " + e.getMessage());
		}
	}

	@GetMapping("/receipts/last")
	public Map<String, Object> getLastReceiptNumber() {
		return service.getNextReceiptNumber();
	}

	////////// adding receipt with company details/////////
	@GetMapping("/receipt/company")
	public List<Map<String, Object>> getAllReceiptDetailsCompany() {
		return service.getAllReceiptDetailsCompany();
	}

	//////////// adding receipt with distributor details by id//////////
	@GetMapping("/receipt/distributor/{distributorid}")
	public List<Map<String, Object>> getAllReceiptDetailsByDistributor(@PathVariable Long distributorid) {
		return service.getAllReceiptDetailsByDistributor(distributorid);
	}

	////////// adding receipt with company details by id/////////
	@GetMapping("/receipt/company/{companyid}")
	public List<Map<String, Object>> getAllReceiptDetailsByCompany(@PathVariable Long companyid) {
		return service.getAllReceiptDetailsByCompany(companyid);
	}

	//////////// adding receipt with distributor details//////////
	@GetMapping("/receipt/distributor")
	public List<Map<String, Object>> getAllReceiptDetailsDistributor() {
		return service.getAllReceiptDetailsDistributor();
	}

	@PostMapping("/receipts/filter")
	public List<Receipt> getReceiptsByDate(@RequestBody Receipt receipt) {
		Date date = receipt.getDate();
		return service.findAllReceiptsByDate(date);
	}

	@PostMapping("/receipts/report")
	public List<Map<String, Object>> getAllReceiptBetweenDates(@RequestBody Map<String, Object> requestBody) {
		LocalDate startDate = LocalDate.parse(requestBody.get("startDate").toString(), DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(requestBody.get("endDate").toString(), DateTimeFormatter.ISO_DATE);
		return service.getAllReceiptBetweenDates(startDate, endDate);
	}

	@PostMapping("/receipts/company/report")
	public List<Map<String, Object>> getAllReceiptDetailsCompanyByDate(@RequestBody Map<String, Object> requestBody) {
		LocalDate startDate = LocalDate.parse(requestBody.get("startDate").toString(), DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(requestBody.get("endDate").toString(), DateTimeFormatter.ISO_DATE);
		return service.getAllReceiptDetailsCompanyByDate(startDate, endDate);
	}

	@PostMapping("/receipts/distributor/report")
	public List<Map<String, Object>> getAllReceiptDetailsDistributorByDate(
			@RequestBody Map<String, Object> requestBody) {
		LocalDate startDate = LocalDate.parse(requestBody.get("startDate").toString(), DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(requestBody.get("endDate").toString(), DateTimeFormatter.ISO_DATE);
		return service.getAllReceiptDetailsDistributorByDate(startDate, endDate);
	}

	@RequestMapping("/receipts/{id}")
	public ResponseEntity<?> getReceiptById(@PathVariable(name = "id") long id) {
		try {
			Receipt receipt = service.getById(id);
			if (receipt != null) {
				return ResponseEntity.ok(receipt);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving receipt: " + e.getMessage());
		}
	}

	@PutMapping("/receipts/edit/{id}")
	public ResponseEntity<Receipt> updateReceipt(@PathVariable("id") long receipt_id, @RequestBody Receipt receipt) {
		try {
			Receipt existingReceipt = service.getById(receipt_id);
			if (existingReceipt == null) {
				return ResponseEntity.notFound().build();
			}
			existingReceipt.setReceiptNo(receipt.getReceiptNo());
			existingReceipt.setDate(receipt.getDate());
			existingReceipt.setReceived(receipt.getReceived());
			existingReceipt.setTowards(receipt.getTowards());
			existingReceipt.setPaymentType(receipt.getPaymentType());
			existingReceipt.setChequeNo(receipt.getChequeNo());
			existingReceipt.setBank(receipt.getBank());
			existingReceipt.setAmount(receipt.getAmount());
			existingReceipt.setDistributorid(receipt.getDistributorid());
			existingReceipt.setCompanyid(receipt.getCompanyid());
			existingReceipt.setReceiptType(receipt.getReceiptType());

			// Set other fields to be updated accordingly

			service.saveOrUpdate(existingReceipt);
			return ResponseEntity.ok(existingReceipt);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/receipts/delete/{id}")
	public ResponseEntity<String> deleteReceipt(@PathVariable("id") long receipt_id) {
		try {
			service.deleteById(receipt_id);
			return ResponseEntity.ok("Receipt deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting receipt: " + e.getMessage());
		}
	}

}

package com.java.business.HeadOffice.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.java.business.HeadOffice.entity.AddBank;
import com.java.business.HeadOffice.repository.AddBankRepository;
import com.java.business.HeadOffice.service.AddBankService;

@RestController
@CrossOrigin
public class AddBankController {

	@Autowired
	private AddBankService addBankService;

	@GetMapping("/addbank")
	public ResponseEntity<?> getUser() {
		try {
			Iterable<AddBank> bank = addBankService.listAll();
//			Map<String, Object> ob = new HashMap<>();
//			ob.put("key", bank.ge)
			
			return ResponseEntity.ok(bank);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while retrieving bank");
		}
	}


	@PostMapping(value = "/addbank/save")
	public ResponseEntity<String> saveMember(@RequestBody AddBank bank) {
		try {
			if (bank.getAccountType().equals("ownAccount")) {
				bank.setBankStatus(true);
			} else if (bank.getAccountType().equals("customerAccount")) {
				bank.setBankStatus(false);
			}
			addBankService.save(bank);
			long bankId = bank.getBankId();
			return ResponseEntity.ok("Bank saved successfully. Bank ID: " + bankId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving bank: " + e.getMessage());
		}
	}

	@RequestMapping("/addbank/{id}")
	private AddBank getBooks(@PathVariable(name = "id") long id) {
		return addBankService.getBankById(id);
	}

	@PutMapping("/addbank/edit/{id}")
	public ResponseEntity<AddBank> updateOrder(@PathVariable("id") Long id, @RequestBody AddBank bank) {
		try {
			AddBank existingBank = addBankService.findById(id);
			if (existingBank == null) {
				return ResponseEntity.notFound().build();
			}
			existingBank.setAccountNumber(bank.getAccountNumber());
			existingBank.setAccountType(bank.getAccountType());
			existingBank.setBankName(bank.getBankName());
			existingBank.setBranchName(bank.getBankName());
			existingBank.setIfscCode(bank.getIfscCode());
			existingBank.setPhoneNumber(bank.getPhoneNumber());
			existingBank.setAccountHolderName(bank.getAccountHolderName());
			existingBank.setBankStatus(bank.isBankStatus());
			addBankService.save(existingBank);
			return ResponseEntity.ok(existingBank);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/addbank/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		addBankService.deleteBankById(id);
		return ResponseEntity.ok("bank deleted successfully");
	}

	
	@GetMapping("/all/internal/transcation")
	public List<Map<String, Object>> getAllInTranscationDetails() {
		List<Map<String, Object>> transcationDetails = addBankService.getAllInTranscationDetails();
		List<Map<String, Object>> paymentList = new ArrayList<>();

		for (Map<String, Object> transcation : transcationDetails) {
			Map<String, Object> paymentMap = new HashMap<>(transcation);

			String paymentType = (String) transcation.get("payment_type");
			if ("credit".equals(paymentType)) {
				paymentMap.put("debit", 0);
			} else if ("debit".equals(paymentType)) {
				paymentMap.put("credit", 0);
			}

			paymentList.add(paymentMap);
		}

		return paymentList;
	}

	@GetMapping("/all/internal/transcation/{inTranscationId}")
	public List<Map<String, Object>> getAllInTranscationDetailsById(@PathVariable Long inTranscationId) {
		List<Map<String, Object>> transcationDetails = addBankService.getAllInTranscationDetailsById(inTranscationId);

		List<Map<String, Object>> paymentList = new ArrayList<>();

		for (Map<String, Object> transcation : transcationDetails) {
			Map<String, Object> paymentMap = new HashMap<>(transcation);

			String paymentType = (String) transcation.get("payment_type");
			if ("credit".equals(paymentType)) {
				paymentMap.put("debit", 0);
			} else if ("debit".equals(paymentType)) {
				paymentMap.put("credit", 0);
			}

			paymentList.add(paymentMap);
		}

		return paymentList;
	}

	@PostMapping("/in/transcation/report")
	public List<Map<String, Object>> getAllInTranscationDetailsByTime(@RequestBody Map<String, Object> requestBody) {
		LocalDate startdate = LocalDate.parse(requestBody.get("startdate").toString(), DateTimeFormatter.ISO_DATE);
		LocalDate enddate = LocalDate.parse(requestBody.get("enddate").toString(), DateTimeFormatter.ISO_DATE);
		List<Map<String, Object>> transcationDetails = addBankService.getAllInTranscationDetailsByTime(startdate,
				enddate);
		List<Map<String, Object>> paymentList = new ArrayList<>();

		for (Map<String, Object> transcation : transcationDetails) {
			Map<String, Object> paymentMap = new HashMap<>(transcation);

			String paymentType = (String) transcation.get("payment_type");
			if ("credit".equals(paymentType)) {
				paymentMap.put("debit", 0);
			} else if ("debit".equals(paymentType)) {
				paymentMap.put("credit", 0);
			}

			paymentList.add(paymentMap);
		}

		return paymentList;
	}

	////////////////// status true and false //////////////

	@Autowired
	private AddBankRepository repo;

	@GetMapping("/bank/true")
	public List<AddBank> findByBankStatusTrue() {
		return repo.findByBankStatusTrue();
	}

	@GetMapping("/bank/false")
	public List<AddBank> findByBankStatusFalse() {
		return repo.findByBankStatusFalse();
	}

}

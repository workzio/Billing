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
import org.springframework.web.bind.annotation.RestController;
import com.java.business.HeadOffice.entity.NewBank;
import com.java.business.HeadOffice.service.NewBankService;

@RestController
@CrossOrigin
public class NewBankController {

	@Autowired
	private NewBankService newBankService;

	@GetMapping("/newbank")
	public ResponseEntity<?> getUser() {
		try {
			Iterable<NewBank> bank = newBankService.listAll();
			return ResponseEntity.ok(bank);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while retrieving bank");
		}
	}

	@PostMapping(value = "/newbank/save")
	public ResponseEntity<String> saveMember(@RequestBody NewBank bank) {
		try {
			newBankService.save(bank);
			long newBankId = bank.getNewBankId();
			return ResponseEntity.ok("NewBank saved successfully. newBank ID: " + newBankId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving NewBank: " + e.getMessage());
		}
	}


	
	@GetMapping("/online/alldetails")
	public List<Map<String, Object>> getAllDetailsOfOwnAndCustomerAccount() {
	    List<Map<String, Object>> transcationDetails = newBankService.getAllDetailsOfOwnAndCustomerAccount();
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

	@PostMapping("/online/report")
	public List<Map<String, Object>> getAllDetailsOfOwnAndCustomerAccountByDate(
			@RequestBody Map<String, Object> requestBody) {
		LocalDate startdate = LocalDate.parse(requestBody.get("startdate").toString(), DateTimeFormatter.ISO_DATE);
		LocalDate enddate = LocalDate.parse(requestBody.get("enddate").toString(), DateTimeFormatter.ISO_DATE);
		 List<Map<String, Object>> transcationDetails = newBankService.getAllDetailsOfOwnAndCustomerAccountByDate(startdate, enddate);
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

	@GetMapping("/online/{newBankId}")
	public List<Map<String, Object>> getAllNewBankById(@PathVariable Long newBankId) {
		 List<Map<String, Object>> transcationDetails = newBankService.getAllNewBankById(newBankId);
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

	@PutMapping("/newbank/edit/{id}")
	public ResponseEntity<NewBank> updateOrder(@PathVariable("id") Long id, @RequestBody NewBank bank) {
		try {
			NewBank existingNewBank = newBankService.findById(id);
			if (existingNewBank == null) {
				return ResponseEntity.notFound().build();
			}
			existingNewBank.setAmount(bank.getAmount());
			existingNewBank.setClientBankId(bank.getClientBankId());
			existingNewBank.setDate(bank.getDate());
			existingNewBank.setOwnBankId(bank.getOwnBankId());
			existingNewBank.setPaymentType(bank.getPaymentType());
			newBankService.save(existingNewBank);
			return ResponseEntity.ok(existingNewBank);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/newbank/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		newBankService.deleteNewBankById(id);
		return ResponseEntity.ok("bank deleted successfully");
	}
}

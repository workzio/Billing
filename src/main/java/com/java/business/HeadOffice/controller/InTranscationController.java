package com.java.business.HeadOffice.controller;

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

import com.java.business.HeadOffice.entity.InTranscation;
import com.java.business.HeadOffice.service.InTranscationService;

@RestController
@CrossOrigin
public class InTranscationController {

	@Autowired
	private InTranscationService inTranscationService;
	
	@GetMapping("/inTranscation")
	public ResponseEntity<?> getUser() {
		try {
			Iterable<InTranscation> bank = inTranscationService.listAll();
			return ResponseEntity.ok(bank);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while retrieving members");
		}
	}

	@PostMapping(value = "/inTranscation/save")
	public ResponseEntity<String> saveMember(@RequestBody InTranscation bank) {
		try {
			inTranscationService.save(bank);
			long inTranscationId = bank.getInTranscationId();
			return ResponseEntity.ok("InTranscation saved successfully. Member ID: " + inTranscationId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving InTranscation: " + e.getMessage());
		}
	}

	@RequestMapping("/inTranscation/{id}")
	private InTranscation getBooks(@PathVariable(name = "id") long id) {
		return inTranscationService.getBankById(id);
	}

	@PutMapping("/inTranscation/edit/{id}")
	public ResponseEntity<InTranscation> updateOrder(@PathVariable("id") Long id, @RequestBody InTranscation inTranscation) {
		try {
			InTranscation existingInTranscation = inTranscationService.findById(id);
			if (existingInTranscation == null) {
				return ResponseEntity.notFound().build();
			}
			existingInTranscation.setBankId(inTranscation.getBankId());
			existingInTranscation.setInTranscationDate(inTranscation.getInTranscationDate());
			existingInTranscation.setInTranscationAmount(inTranscation.getInTranscationAmount());
			existingInTranscation.setInTranscationStatus(existingInTranscation.isInTranscationStatus());
			existingInTranscation.setPaymentType(inTranscation.getPaymentType());
			inTranscationService.save(existingInTranscation);
			return ResponseEntity.ok(existingInTranscation);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/inTranscation/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		inTranscationService.deleteInTranscationById(id);
		return ResponseEntity.ok("InTranscation Details deleted successfully");
	}
}

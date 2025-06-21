package com.java.business.HeadOffice.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import com.java.business.HeadOffice.entity.Distributor;
import com.java.business.HeadOffice.service.DistributorService;

@RestController
@CrossOrigin
public class DistributorController {

	@Autowired
	private DistributorService services;

	@GetMapping("/Distribution")
	public Iterable<Distributor> getUser() {
		return services.listAll();
	}

//	@GetMapping("/Distribution")
//	public ResponseEntity<?> getDistributors() {
//		try {
//			Iterable<Distributor> distributors = services.listAll();
//			return ResponseEntity.ok(distributors);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Error retrieving distributors: " + e.getMessage());
//		}
//
//	}

	@PostMapping(value = "/save_Distribution")
	private long saveBook(@RequestBody Distributor distribution) {
		distribution.setDate(new Date());
		services.save(distribution);
		return distribution.getDistributorid();
	}

//	@PostMapping(value = "/save_Distribution")
//	public ResponseEntity<String> saveDistribution(@RequestBody Distributor distribution) {
//
//		try {
//
//			Distributor existingDistributor = services.findByPhoneno(distribution.getPhoneno());
//			if (existingDistributor != null) {
//				return ResponseEntity.status(HttpStatus.CONFLICT)
//						.body("A distributor with the same phone number or email already exists");
//			}
//			services.Saveorupdate(distribution);
//			return ResponseEntity.ok("Distribution saved with id: " + distribution.getDistributorid());
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Error saving distribution: " + e.getMessage());
//
//		}
//
//	}

	@RequestMapping("/Shop_Distribution/{id}")
	private Distributor getBooks(@PathVariable(name = "id") long distributorid) {
		return services.getDetailById(distributorid);

	}

	@PutMapping("/edit/Distribution/{id}")
	public ResponseEntity<Distributor> updateOrder(@PathVariable("id") Long distributorid,
			@RequestBody Distributor existingDistributor) {

		try {
			Distributor distribution = services.findById(distributorid);
			if (distribution == null) {
				return ResponseEntity.notFound().build();
			}
			distribution.setName(existingDistributor.getName());
			distribution.setPhoneno(existingDistributor.getPhoneno());
			distribution.setEmail(existingDistributor.getEmail());
			distribution.setDistrict(existingDistributor.getDistrict());
			distribution.setState(existingDistributor.getState());
			distribution.setCode(existingDistributor.getCode());
			distribution.setPincode(existingDistributor.getPincode());
			distribution.setGst_no(existingDistributor.getGst_no());
			distribution.setAddress(existingDistributor.getAddress());
			distribution.setDate(existingDistributor.getDate());
			services.save(distribution);
			return ResponseEntity.ok(distribution);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

	@DeleteMapping("/delete/Distribution/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		services.deleteDistributionById(id);
		return ResponseEntity.ok("Customer deleted successfully");

	}
}

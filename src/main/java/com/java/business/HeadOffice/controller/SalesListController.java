package com.java.business.HeadOffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.java.business.HeadOffice.entity.SalesList;
import com.java.business.HeadOffice.service.SalesListService;

@RestController
@CrossOrigin
public class SalesListController {

	@Autowired
	private SalesListService service;

	@GetMapping("/saleslist")
	public ResponseEntity<?> getPurcha() {
		try {
			Iterable<SalesList> SalesLists = service.listAll();
			return ResponseEntity.ok(SalesLists);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving PurchaseLists: " + e.getMessage());
		}
	}

	@PostMapping(value = "/saleslist/save")
	public ResponseEntity<String> savePurchaseList(@RequestBody SalesList salesList) {
		try {
			service.save(salesList);
			return ResponseEntity.ok("Saleslist saved with id: " + salesList.getSaleslist_id());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving saleslist: " + e.getMessage());
		}

	}

}

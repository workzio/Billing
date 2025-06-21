package com.java.business.HeadOffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.business.HeadOffice.entity.PurchaseList;
import com.java.business.HeadOffice.service.PurchaseListService;

@RestController
@CrossOrigin
public class PurchaseListController {

	@Autowired
	private PurchaseListService service;


	@GetMapping("/purchaselist")
	public ResponseEntity<?> getPurchaseLists() {
		try {
			Iterable<PurchaseList> purchaseLists = service.listAll();
			return ResponseEntity.ok(purchaseLists);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving PurchaseLists: " + e.getMessage());
		}

	}

	@PostMapping(value = "/purchaselist/save")
	public ResponseEntity<String> savePurchaseList(@RequestBody PurchaseList purchaseList) {
		try {
			service.save(purchaseList);
			return ResponseEntity.ok("PurchaseList saved with id: " + purchaseList.getPurchase_list_id());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving PurchaseList: " + e.getMessage());
		}

	}

}

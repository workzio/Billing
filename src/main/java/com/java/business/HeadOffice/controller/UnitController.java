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
import org.springframework.web.bind.annotation.RestController;
import com.java.business.HeadOffice.entity.Unit;
import com.java.business.HeadOffice.service.UnitService;

@RestController
@CrossOrigin
public class UnitController {

	@Autowired
	private UnitService service;
	

	
	@GetMapping("/unit")
	public ResponseEntity<?> getProductUnits() {
		try {
			Iterable<Unit> productUnits = service.listAll();
			return ResponseEntity.ok(productUnits);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving Product Units: " + e.getMessage());
		}

	}

	@PostMapping(value = "/unit/save")
	public ResponseEntity<String> saveProductUnit(@RequestBody Unit product_Unit) {
		try {
			service.save(product_Unit);
			return ResponseEntity.ok("Product Unit saved with id: " + product_Unit.getUnit_id());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving Product Unit: " + e.getMessage());

		}

	}
	
	
	@PutMapping("/unit/edit/{id}")
	public ResponseEntity<Unit> updateOrder(@PathVariable("id") Long unit_id, @RequestBody Unit unit) {
		try {
			Unit existingUnit = service.findById(unit_id);
			if (existingUnit == null) {
				return ResponseEntity.notFound().build();
			}
			
			existingUnit.setUnitname(unit.getUnitname());
			service.save(existingUnit);
			return ResponseEntity.ok(existingUnit);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/unit/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long unit_id) {
		service.deleteMemberById(unit_id);
		return ResponseEntity.ok("Customer deleted successfully");
	}

}

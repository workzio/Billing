package com.java.business.HeadOffice.controller;

import java.util.Date;
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
import com.java.business.HeadOffice.entity.OutSource;
import com.java.business.HeadOffice.service.OutSourceService;

@RestController
@CrossOrigin
public class OutSourceController {

	@Autowired
	private OutSourceService service;

	
	@GetMapping("/outsource")
	public Iterable<OutSource> getUser() {
		return service.listAll();
	}

	@PostMapping(value = "/outsource/save")
	private long saveBook(@RequestBody OutSource outSource) {
		outSource.setDate(new Date());
		service.Save(outSource);
		return outSource.getCompanyid();
	}

	@RequestMapping("/outsource/{id}")
	private OutSource getBooks(@PathVariable(name = "id") long companyid) {
		return service.getUserById(companyid);
	}

	@PutMapping("/outsource/edit/{id}")
	public ResponseEntity<OutSource> updateOrder(@PathVariable("id") Long companyid, @RequestBody OutSource outSource) {
		try {
			OutSource existingOut = service.findById(companyid);
			if (existingOut == null) {
				return ResponseEntity.notFound().build();
			}
			existingOut.setAccountnumber(outSource.getAccountnumber());
			existingOut.setAddress(outSource.getAddress());
			existingOut.setCity(outSource.getCity());
			existingOut.setEmail(outSource.getEmail());
			existingOut.setCompanyname(outSource.getCompanyname());
			existingOut.setContact(outSource.getContact());
			existingOut.setCountry(outSource.getCountry());
			existingOut.setDate(outSource.getDate());
			existingOut.setDealername(outSource.getDealername());
			existingOut.setAddress(outSource.getAddress());
			existingOut.setDate(outSource.getDate());
			existingOut.setDistrict(outSource.getDistrict());
//			existingOut.setGstin(outSource.getGstin());
			existingOut.setState(outSource.getState());
			existingOut.setTaxid(outSource.getTaxid());
			existingOut.setZipcode(outSource.getZipcode());
			existingOut.setCode(outSource.getCode());
			service.save(existingOut);
			return ResponseEntity.ok(existingOut);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/outsource/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		service.deleteMemberById(id);
		return ResponseEntity.ok("Customer deleted successfully");
	}
}

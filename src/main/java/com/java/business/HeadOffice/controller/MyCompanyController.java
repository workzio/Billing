package com.java.business.HeadOffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.java.business.HeadOffice.entity.MyCompany;
import com.java.business.HeadOffice.repository.DistributorRepository;
import com.java.business.HeadOffice.repository.PurchaseRepository;
import com.java.business.HeadOffice.repository.SalesRepository;
import com.java.business.HeadOffice.service.MyCompanyService;
import com.java.business.HeadOffice.service.ProductService;

@RestController
@CrossOrigin
public class MyCompanyController {

	@Autowired
	private MyCompanyService services;
	
	@Autowired
	private SalesRepository salesRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private DistributorRepository distributorRepository;

	@Autowired
	private ProductService productService;

	
	
	@GetMapping("/mycompany")
	public Iterable<MyCompany> getUser() {
		return services.listAll();
	}

	@GetMapping("/dashboard/report")
	public Map<String, Object> getDashboard() {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("sale_count", salesRepository.count());
		returnMap.put("purchase_count", purchaseRepository.count());
		returnMap.put("customer", distributorRepository.count());
		List<Map<String, Object>> stockList = productService.getTotalStock();
		returnMap.put("stock", stockList.isEmpty() ? 0 : stockList.get(0).getOrDefault("stock", 0));
		return returnMap;

	}

	@PostMapping(value = "/save_mycompany")
	public ResponseEntity<String> saveMyCompany(@RequestBody MyCompany mycompany) {
		try {
			services.Saveorupdate(mycompany);
			return ResponseEntity.ok("MyCompany saved with id: " + mycompany.getMycompanyid());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving MyCompany: " + e.getMessage());
		}
	}

	
	@RequestMapping("/Shop_mycompany/{id}")
	private MyCompany getBooks(@PathVariable(name = "id") long mycompanyId) {
	    Optional<MyCompany> myCompanyOptional = services.getDetailById(mycompanyId);

	    if (myCompanyOptional.isPresent()) {
	        MyCompany myCompany = myCompanyOptional.get();
	        return myCompany;
	    } else {
	        return null;
	    }
	}


	@PutMapping("/edit/mycompany/{id}")
	public ResponseEntity<MyCompany> updateOrder(@PathVariable("id") Long mycompanyid,
			@RequestBody MyCompany existingmycompany) {

		try {
			MyCompany mycompany = services.findById(mycompanyid);
			if (mycompany == null) {
				return ResponseEntity.notFound().build();
			}
			mycompany.setCompanyname(existingmycompany.getCompanyname());
			mycompany.setPhoneno1(existingmycompany.getPhoneno1());
			mycompany.setPhoneno2(existingmycompany.getPhoneno2());
			mycompany.setEmail(existingmycompany.getEmail());
			mycompany.setDistrict(existingmycompany.getDistrict());
			mycompany.setState(existingmycompany.getState());
			mycompany.setCountry(existingmycompany.getCountry());
			mycompany.setCode(existingmycompany.getCode());
			mycompany.setLocation(existingmycompany.getLocation());
			mycompany.setPincode(existingmycompany.getPincode());
			mycompany.setGstno(existingmycompany.getGstno());
			mycompany.setAddress(existingmycompany.getAddress());
			mycompany.setBankname(existingmycompany.getBankname());
			mycompany.setAccount_no(existingmycompany.getAccount_no());
			mycompany.setIfsc_code(existingmycompany.getIfsc_code());
			mycompany.setBranch(existingmycompany.getBranch());
			services.save(mycompany);
			return ResponseEntity.ok(mycompany);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	
	@DeleteMapping("/delete/mycompany/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		services.deleteMyCompanyById(id);
		return ResponseEntity.ok("Customer deleted successfully");
	}
}

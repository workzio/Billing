package com.java.business.HeadOffice.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
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
import com.java.business.HeadOffice.entity.Product;
import com.java.business.HeadOffice.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/product")
	public Iterable<Product> getUser() {
		return service.listAll();
	}

	@PostMapping(value = "/product/save")
	private long saveBook(@RequestBody Product product) {
		service.Save(product);
		return product.getProductid();
	}

	@GetMapping("/high-moving")
	public List<Map<String, Object>> getHighMovingProducts() {
		return service.getHighMovingProducts();
	}

	@PostMapping("/date/range")
	public List<Map<String, Object>> getProductsByDateRange(@RequestBody Map<String, String> requestBody) {
		LocalDate startDate = LocalDate.parse(requestBody.get("startDate"), DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(requestBody.get("endDate"), DateTimeFormatter.ISO_DATE);
		return service.getAllHighMovingProducts(startDate, endDate);
	}

//	@GetMapping("/product")
//	public ResponseEntity<?> getProducts() {
//		try {
//			Iterable<Product> products = service.listAll();
//			return ResponseEntity.ok(products);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Error retrieving Products: " + e.getMessage());
//		}
//	}

//	@PostMapping(value = "/product/save")
//	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
//		try {
//			service.save(product);
//			return ResponseEntity.ok("Product saved with id: " + product.getProductid());
//		} catch (DataIntegrityViolationException e) {
//			if (e.getCause() instanceof ConstraintViolationException) {
//				ConstraintViolationException cause = (ConstraintViolationException) e.getCause();
//				if (cause.getConstraintViolations().equals("uk_product_hsn_code")) {
//					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//							.body("Error saving Product: Product with this HSN code already exists.");
//				}
//
//			}
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Error saving Product: " + e.getMessage());
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Error saving Product: " + e.getMessage());
//		}
//	}

////////stock//////

	@GetMapping("/stockview")
	private List<Map<String, Object>> findStock() {
		return service.findStock();
	}

	/////// stock/////

	////// productview

//	@GetMapping("/productview")
//	private List<Map<String, Object>> getProductView() {
//		return service.getProductView();
//	}

	@GetMapping("/productview")
	private List<Map<String, Object>> getProductView() {
		List<Map<String, Object>> productView = service.getProductView();
		for (int i = 0; i < productView.size(); i++) {
			Map<String, Object> row = new HashMap<>(productView.get(i));
			if (row.get("stock_in") == null) {
				row.put("stock_in", 0);
			}
			if (row.get("stock_out") == null) {
				row.put("stock_out", 0);
			}
			productView.set(i, row);
		}

		return productView;
	}

	////// productview/////

	@RequestMapping("/product/{id}")
	private Product getBooks(@PathVariable(name = "id") long productid) {
		return service.getUserById(productid);
	}

	@RequestMapping("/product/category/{id}")
	private List<Product> getCategory(@PathVariable(name = "id") long categoryid) {
		return service.getUserByCategoryId(categoryid);
	}

	@PutMapping("/product/edit/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId, @RequestBody Product newProduct) {
		try {
			Product existingProduct = service.findById(productId);

			if (existingProduct == null) {
				return ResponseEntity.notFound().build();
			}
			existingProduct.setCategoryid(newProduct.getCategoryid());
			existingProduct.setUnit_id(newProduct.getUnit_id());
			existingProduct.setTax_percentage(newProduct.getTax_percentage());
			existingProduct.setProductdescription(newProduct.getProductdescription());
			existingProduct.setProductname(newProduct.getProductname());
			existingProduct.setHsn_code(newProduct.getHsn_code());
			service.save(existingProduct);
			return ResponseEntity.ok(existingProduct);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		service.deleteMemberById(id);
		return ResponseEntity.ok("Customer deleted successfully");
	}
}

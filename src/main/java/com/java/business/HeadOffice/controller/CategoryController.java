package com.java.business.HeadOffice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.java.business.HeadOffice.entity.Category;
import com.java.business.HeadOffice.entity.Product;
import com.java.business.HeadOffice.repository.CategoryRepository;
import com.java.business.HeadOffice.repository.ProductRepository;
import com.java.business.HeadOffice.repository.UnitRepository;
import com.java.business.HeadOffice.service.CategoryService;

@RestController
@CrossOrigin
public class CategoryController {

	@Autowired
	private CategoryService service;

	@Autowired
	private CategoryRepository repo;

	@GetMapping("/search")
	public List<Category> findAllCategory(@RequestParam("category") String category) {
		return repo.findByCategory(category);
	}

	@GetMapping("/search1")
	public List<Category> findAllCategory1(@RequestBody Category category) {
		return repo.findByCategory(category.getCategory());
	}

	@GetMapping("/category")
	public Iterable<Category> getUser() {
		return service.listAll();
	}

	@GetMapping("/category/product/unit")
	private List<Map<String, Object>> findAllBYCategoryAndProduct() {
		List<Map<String, Object>> orderList = new ArrayList<>();
		Iterable<Map<String, Object>> iterable = service.findAllProductsByCategory();
		Map<String, List<Map<String, Object>>> categoryGroupMap = StreamSupport.stream(iterable.spliterator(), false)
				.collect(Collectors.groupingBy(action -> action.get("category_id").toString()));
		System.out.println(categoryGroupMap);
		for (Entry<String, List<Map<String, Object>>> list : categoryGroupMap.entrySet()) {
			Map<String, Object> categoryMap = new HashMap<>();
			categoryMap.put("category_id", list.getKey());
			categoryMap.put("category_name", list.getValue().get(0).get("category"));
			categoryMap.put("products", categoryGroupMap.get(list.getKey()));
			orderList.add(categoryMap);
		}
		return orderList;
	}

//	@GetMapping("/category/product/unit")
//	private List<Map<String, Object>> findAllBYCategoryAndProduct() {
//		List<Map<String, Object>> orderList = new ArrayList<>();
//		Iterable<Map<String, Object>> iterable = service.findAllProductsByCategory();
//		Iterable<Map<String, Object>> iterable1 = service.findAllProductsByCategory1();
//		Map<String, List<Map<String, Object>>> categoryGroupMap = StreamSupport.stream(iterable.spliterator(), false)
//				.collect(Collectors.groupingBy(action -> action.get("category_id").toString()));
//		Map<String, List<Map<String, Object>>> categoryGroupMap1 = StreamSupport.stream(iterable1.spliterator(), false)
//				.collect(Collectors.groupingBy(action -> action.get("category_id").toString()));
//		System.out.println(categoryGroupMap);
//		for (Entry<String, List<Map<String, Object>>> list : categoryGroupMap.entrySet()) {
//			Map<String, Object> categoryMap = new HashMap<>();
//			categoryMap.put("category_id", list.getKey());
//			categoryMap.put("category_name", list.getValue().get(0).get("category"));
//			categoryMap.put("products", categoryGroupMap.get(list.getKey()));
//			orderList.add(categoryMap);
//
//			for (Entry<String, List<Map<String, Object>>> list1 : categoryGroupMap1.entrySet()) {
//				Map<String, Object> categoryMap1 = new HashMap<>();
//				categoryMap1.put("category_id_new", list1.getKey());
//				categoryMap1.put("category_name_new", list1.getValue().get(0).get("category"));
//				categoryMap1.put("products_new", list1.getValue());
//				orderList.add(categoryMap1);
//			}
//		}
//		return orderList;
//	}

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UnitRepository unitRepository;

	@Autowired
	private ProductRepository productRepository;

//	@GetMapping("demo/group")
//	private Map<String, Map<String, Map<String, List<Map<String, Object>>>>> findByProductBasedCategory1() {
//		List<Map<String, Object>> iterable = service.findAllProductsByCategory();
//		Map<String, Map<String, Map<String, List<Map<String, Object>>>>> categoryGroupMap = new HashMap<>();
//		for (Map<String, Object> map : iterable) {
//			if (categoryGroupMap.containsKey(map.get("unit_id").toString())) {
//				if (categoryGroupMap.get(map.get("unit_id").toString())
//						.containsKey(map.get("category_id").toString())) {
//					if (categoryGroupMap.get(map.get("unit_id").toString()).get(map.get("category_id").toString())
//							.containsKey(map.get("productid").toString())) {
//						categoryGroupMap.get(map.get("unit_id").toString()).get(map.get("category_id").toString())
//								.get(map.get("productid").toString()).add(map);
//					} else {
//						List<Map<String, Object>> list = new ArrayList<>();
//						list.add(map);
//						categoryGroupMap.get(map.get("unit_id").toString()).get(map.get("category_id").toString())
//								.put(map.get("productid").toString(), list);
//					}
//				} else {
//					List<Map<String, Object>> list = new ArrayList<>();
//					list.add(map);
//					Map<String, List<Map<String, Object>>> childMap = new HashMap<>();
//					childMap.put(map.get("productid").toString(), list);
//					categoryGroupMap.get(map.get("unit_id").toString()).put(map.get("category_id").toString(),
//							childMap);
//				}
//			} else {
//				List<Map<String, Object>> list = new ArrayList<>();
//				list.add(map);
//				Map<String, List<Map<String, Object>>> childMap = new HashMap<>();
//				childMap.put(map.get("productid").toString(), list);
//				Map<String, Map<String, List<Map<String, Object>>>> innerChildMap = new HashMap<>();
//				innerChildMap.put(map.get("category_id").toString(), childMap);
//				categoryGroupMap.put(map.get("unit_id").toString(), innerChildMap);
//			}
//		}
//		return categoryGroupMap;
//	}

//	@GetMapping("/demo")
//	private List<Map<String, Object>> findByProductBasedCategory() {
//		List<Category> category = categoryRepository.findAll();
//		List<Product> product = productRepository.findAll();
//		List<Unit> unit = unitRepository.findAll();
//
//		List<Map<String, Object>> productList = new ArrayList<>();
//		for (Product product1 : product) {
//		
//			Map<String, Object> productMap = new HashMap<>();
//			productMap.put("productId", product1.getProductid());
//			productMap.put("productname", product1.getProductname());
//
//			List<Map<String, Object>> categoryList = new ArrayList<>();
//			for (Category category1 : category) {
//				Map<String, Object> categoryMap = new HashMap<>();
//				categoryMap.put("categoryId", category1.getCategory_id());
//				categoryMap.put("categoryName", category1.getCategory());
//				categoryList.add(categoryMap);
//			}
//			productMap.put("categories", categoryList);
//			productList.add(productMap);
//		}
//		return productList;
//	}

	@GetMapping("/demo")
	private Map<String, Object> findByProductBasedCategory() {
		List<Category> categories = categoryRepository.findAll();
		List<Product> products = productRepository.findAll();

		Map<String, Object> productList = new HashMap<>();

		for (Product product : products) {
			Map<String, Object> productMap = new HashMap<>();
			productMap.put("productId", product.getProductid());
			productMap.put("productName", product.getProductname());

			List<Map<String, Object>> categoryList = new ArrayList<>();
			for (Category category : categories) {
				if (category.getCategory_id() == product.getCategoryid()) {
					if (category.getCategory() == product.getProductname()) {
						Map<String, Object> categoryMap = new HashMap<>();
						categoryMap.put("categoryId", category.getCategory_id());
						categoryMap.put("categoryName", category.getCategory());
						categoryList.add(categoryMap);
					}

				}
			}

			productMap.put("categories", categoryList);
			productList.put(String.valueOf(product.getProductid()), productMap);
			productList.putAll(productMap);
		}

		return productList;
	}

//	@GetMapping("/category/product/unit/new")
//	private List<Map<String, Object>> findAllByCategoryAndProduct() {
//		List<Map<String, Object>> orderList = new ArrayList<>();
//		Iterable<Map<String, Object>> iterable = service.findAllProductsByCategory();
//		Map<String, Map<String, Map<String, List<Map<String, Object>>>>> categoryGroupMap = StreamSupport
//				.stream(iterable.spliterator(), false)
//				.collect(Collectors.groupingBy(action -> action.get("unit_id").toString(),
//						Collectors.groupingBy(action -> action.get("category_id").toString(),
//								Collectors.groupingBy(action -> action.get("productid").toString()))));
//
//		for (Entry<String, Map<String, Map<String, List<Map<String, Object>>>>> categoryEntry : categoryGroupMap
//				.entrySet()) {
//			Map<String, Object> categoryMap = new HashMap<>();
//			categoryMap.put("unit_id", categoryEntry.getKey());
//			List<Map<String, Object>> unitList = new ArrayList<>();
//			categoryEntry.getValue().get("1").get("1").get(0).get("category");
//
//			for (Entry<String, Map<String, List<Map<String, Object>>>> categoryMapEntry : categoryEntry.getValue()
//					.entrySet()) {
//				Map<String, Object> unitMap = new HashMap<>();
//				unitMap.put("category_id", categoryMapEntry.getKey());
//				List<Map<String, Object>> productList = new ArrayList<>();
//
//				for (Entry<String, List<Map<String, Object>>> productMapEntry : categoryMapEntry.getValue()
//						.entrySet()) {
//					Map<String, Object> productMap = new HashMap<>();
//					productMap.put("productid", productMapEntry.getKey());
//					productMap.put("products", productMapEntry.getValue());
//					unitMap.put("category_name", productMapEntry.getValue().get(0).get("category"));
//					categoryMap.put("unit_name", productMapEntry.getValue().get(0).get("unitname"));
//					productList.add(productMap);
//				}
//
//				unitMap.put("products", productList);
//				unitList.add(unitMap);
//			}
//
//			categoryMap.put("unit", unitList);
//			orderList.add(categoryMap);
//		}
//		return orderList;
//	}

	@PostMapping(value = "/category/save")
	private long saveBook(@RequestBody Category category) {
		service.Save(category);
		return category.getCategory_id();
	}

	@RequestMapping("/category/{id}")
	private Category getBooks(@PathVariable(name = "id") long category_id) {
		return service.getUserById(category_id);
	}

	@PutMapping("/category/edit/{id}")
	public ResponseEntity<Category> updateOrder(@PathVariable("id") Long category_id, @RequestBody Category category) {
		try {
			Category existingCategory = service.findById(category_id);
			if (existingCategory == null) {
				return ResponseEntity.notFound().build();
			}
			existingCategory.setCategory(category.getCategory());
			service.save(existingCategory);
			return ResponseEntity.ok(existingCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/category/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		service.deleteMemberById(id);
		return ResponseEntity.ok("Customer deleted successfully");
	}

}

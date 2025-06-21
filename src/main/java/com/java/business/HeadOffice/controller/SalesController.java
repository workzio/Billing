package com.java.business.HeadOffice.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.business.HeadOffice.entity.MyCompany;
import com.java.business.HeadOffice.entity.Product;
import com.java.business.HeadOffice.entity.ReturnPayment;
import com.java.business.HeadOffice.entity.Sales;
import com.java.business.HeadOffice.entity.SalesList;
import com.java.business.HeadOffice.repository.DistributorRepository;
import com.java.business.HeadOffice.repository.MyCompanyRepository;
import com.java.business.HeadOffice.repository.ProductRepository;
import com.java.business.HeadOffice.repository.ReturnPaymentRepository;
import com.java.business.HeadOffice.repository.SalesRepository;
import com.java.business.HeadOffice.service.SalesListService;
import com.java.business.HeadOffice.service.SalesService;

@RestController
@CrossOrigin
public class SalesController {

	@Autowired
	private SalesService service;

	@Autowired
	private SalesRepository repos;

	@Autowired
	private ProductRepository repo;

	@Autowired
	private MyCompanyRepository companyRepo;

	@Autowired
	private SalesListService saleService;

	@GetMapping("/sales")
	public ResponseEntity<?> getSales() {
		try {
			Iterable<Sales> sales = service.listAll();
			return ResponseEntity.ok(sales);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving Sales: " + e.getMessage());
		}

	}

	@PostMapping("/sales/report1")
	public List<Map<String, Object>> getAllSalesBetweenDates(@RequestBody Map<String, Object> requestBody) {
		LocalDate startDate = LocalDate.parse(requestBody.get("startDate").toString(), DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(requestBody.get("endDate").toString(), DateTimeFormatter.ISO_DATE);
		List<Map<String, Object>> report1 = saleService.getGstByTax();
		List<Map<String, Object>> report = service.getAllSalesBetweenDates(startDate, endDate);
		List<MyCompany> companyList = companyRepo.findAll();
		List<Map<String, Object>> salesDataList = new ObjectMapper().convertValue(report, List.class);
		List<Map<String, Object>> salesDataList1 = new ObjectMapper().convertValue(report1, List.class);
		Map<String, List<Map<String, Object>>> salesGroupMap = salesDataList.stream()
				.collect(Collectors.groupingBy(action -> action.get("sales_id").toString()));
		Map<String, List<Map<String, Object>>> salesGroupMap1 = salesDataList1.stream()
				.collect(Collectors.groupingBy(action -> action.get("sales_id").toString()));
		List<Map<String, Object>> salesList = new ArrayList<>();
		for (Entry<String, List<Map<String, Object>>> salesDataMap : salesGroupMap.entrySet()) {
			Map<String, Object> salesMap = new HashMap<>();
			salesMap.putAll(salesDataMap.getValue().get(0));
			salesMap.put("saleslist", salesDataMap.getValue());
			if (String.valueOf(companyList.get(0).getCode()).equals(salesMap.get("code").toString())) {
				double gst = Double.parseDouble(salesMap.get("total_tax").toString());
				salesMap.put("sgst", gst / 2);
				salesMap.put("cgst", gst / 2);
				for (Map<String, Object> salesData : (List<Map<String, Object>>) salesMap.get("saleslist")) {
					double productgst = Double.parseDouble(salesData.get("tax_qty_amt").toString());
					salesData.put("sgst", productgst / 2);
					salesData.put("cgst", productgst / 2);
				}
			} else {
				salesMap.put("igst", salesMap.get("total_tax"));
				for (Map<String, Object> salesData : (List<Map<String, Object>>) salesMap.get("saleslist")) {
					salesData.put("igst", salesData.get("tax_qty_amt"));
				}
			}
			for (Map<String, Object> salesMap1 : salesGroupMap1.get(salesDataMap.getKey())) {
				if (String.valueOf(companyList.get(0).getCode()).equals(salesMap.get("code").toString())) {
					double percentage = Double.parseDouble(salesMap1.get("tax_percentage").toString());
					salesMap1.put("gst_type", true);
					salesMap1.put("sgst_tax", percentage / 2);
					salesMap1.put("cgst_tax", percentage / 2);
					double gst = Double.parseDouble(salesMap1.get("tax").toString());
					salesMap1.put("sgst", gst / 2);
					salesMap1.put("cgst", gst / 2);
				} else {
					salesMap1.put("igst", salesMap1.get("tax"));
					salesMap1.put("gst_type", false);
					salesMap1.put("igst_tax", salesMap1.get("tax_percentage"));
				}
			}
			salesMap.put("taxlist", salesGroupMap1.get(salesDataMap.getKey()));
			salesList.add(salesMap);
			salesList.sort(Comparator.comparing(salesData -> salesData.get("sales_id").toString()));
			Collections.reverse(salesList);
		}
		return salesList;
	}

	@GetMapping("/last-invoice")
	public Map<String, Object> getLastInvoiceNumber() {
		return service.getNextInvoiceNumber();
	}

	@GetMapping("/sales/distributor/{id}")
	public Map<String, Object> getSalesInovice(@PathVariable(name = "id") long distributorid) {
		ObjectMapper object = new ObjectMapper();
		List<Map<String, Object>> report1 = saleService.getGstByTaxDistributor(distributorid);
		List<Map<String, Object>> report = service.getAllPaidBillDetailsByDistributor(distributorid);
		List<MyCompany> companyList = companyRepo.findAll();
		List<Map<String, Object>> salesDataList = object.convertValue(report, List.class);
		List<Map<String, Object>> salesDataList1 = object.convertValue(report1, List.class);
		Map<String, List<Map<String, Object>>> salesGroupMap = salesDataList.stream()
				.collect(Collectors.groupingBy(action -> action.get("sales_id").toString()));
		Map<String, List<Map<String, Object>>> salesGroupMap1 = salesDataList1.stream()
				.collect(Collectors.groupingBy(action -> action.get("sales_id").toString()));

		List<Map<String, Object>> salesList = new ArrayList<>();
		for (Entry<String, List<Map<String, Object>>> salesDataMap : salesGroupMap.entrySet()) {
			Map<String, Object> salesMap = new HashMap<>();
			salesMap.putAll(salesDataMap.getValue().get(0));
			salesMap.put("saleslist", salesDataMap.getValue());
			if (String.valueOf(companyList.get(0).getCode()).equals(salesMap.get("code").toString())) {
				double gst = Double.parseDouble(salesMap.get("total_tax").toString());
				salesMap.put("sgst", gst / 2);
				salesMap.put("cgst", gst / 2);
				for (Map<String, Object> salesData : (List<Map<String, Object>>) salesMap.get("saleslist")) {
					double productgst = Double.parseDouble(salesData.get("tax_qty_amt").toString());
					salesData.put("sgst", productgst / 2);
					salesData.put("cgst", productgst / 2);
				}
			} else {
				salesMap.put("igst", salesMap.get("total_tax"));
				for (Map<String, Object> salesData : (List<Map<String, Object>>) salesMap.get("saleslist")) {
					salesData.put("igst", salesData.get("tax_qty_amt"));
				}
			}
			for (Map<String, Object> salesMap1 : salesGroupMap1.get(salesDataMap.getKey())) {
				if (String.valueOf(companyList.get(0).getCode()).equals(salesMap.get("code").toString())) {
					double percentage = Double.parseDouble(salesMap1.get("tax_percentage").toString());
					salesMap1.put("gst_type", true);
					salesMap1.put("sgst_tax", percentage / 2);
					salesMap1.put("cgst_tax", percentage / 2);

					double gst = Double.parseDouble(salesMap1.get("tax").toString());
					salesMap1.put("sgst", gst / 2);
					salesMap1.put("cgst", gst / 2);

				} else {
					salesMap1.put("igst", salesMap1.get("tax"));
					salesMap1.put("gst_type", false);
					salesMap1.put("igst_tax", salesMap1.get("tax_percentage"));

				}
			}
			salesMap.put("taxlist", salesGroupMap1.get(salesDataMap.getKey()));
			salesList.add(salesMap);
			salesList.sort(Comparator.comparing(salesData -> salesData.get("sales_id").toString()));
			Collections.reverse(salesList);
		}

		Map<String, Object> result = new HashMap<>();
		if (!salesDataList.isEmpty()) {
			result.put("distributorid", salesDataList.get(0).get("distributorid"));
			result.put("distributorname", salesDataList.get(0).get("name"));
		} else {

		}

		result.put("sales", salesList);

		return result;
	}

	@GetMapping("/sales/invoice")
	public List<Map<String, Object>> getSalesInvoiceList() {
		ObjectMapper object = new ObjectMapper();
		List<Map<String, Object>> report1 = saleService.getGstByTax();
		List<Map<String, Object>> report = service.getAllSalesInvoices();
		List<MyCompany> companyList = companyRepo.findAll();

		List<Map<String, Object>> salesDataList = object.convertValue(report, List.class);
		List<Map<String, Object>> salesDataList1 = object.convertValue(report1, List.class);
		Map<String, List<Map<String, Object>>> salesGroupMap = salesDataList.stream()
				.collect(Collectors.groupingBy(action -> action.get("sales_id").toString()));
		Map<String, List<Map<String, Object>>> salesGroupMap1 = salesDataList1.stream()
				.collect(Collectors.groupingBy(action -> action.get("sales_id").toString()));
		System.out.println(salesGroupMap);

		List<Map<String, Object>> salesList = new ArrayList<>();
		for (Map.Entry<String, List<Map<String, Object>>> salesDataMap : salesGroupMap.entrySet()) {
			Map<String, Object> salesMap = new HashMap<>();
			salesMap.putAll(salesDataMap.getValue().get(0));
			salesMap.put("saleslist", salesDataMap.getValue());
			if (String.valueOf(companyList.get(0).getCode()).equals(salesMap.get("code").toString())) {
				double gst = Double.parseDouble(salesMap.get("total_tax").toString());
				salesMap.put("sgst", gst / 2);
				salesMap.put("cgst", gst / 2);
				for (Map<String, Object> salesData : (List<Map<String, Object>>) salesMap.get("saleslist")) {
					double productgst = Double.parseDouble(salesData.get("tax_qty_amt").toString());
					salesData.put("sgst", productgst / 2);
					salesData.put("cgst", productgst / 2);
				}
			} else {
				salesMap.put("igst", salesMap.get("total_tax"));
				for (Map<String, Object> salesData : (List<Map<String, Object>>) salesMap.get("saleslist")) {
					salesData.put("igst", salesData.get("tax_qty_amt"));
				}
			}
			for (Map<String, Object> salesMap1 : salesGroupMap1.get(salesDataMap.getKey())) {
				if (String.valueOf(companyList.get(0).getCode()).equals(salesMap.get("code").toString())) {
					double percentage = Double.parseDouble(salesMap1.get("tax_percentage").toString());
					salesMap1.put("gst_type", true);
					salesMap1.put("sgst_tax", percentage / 2);
					salesMap1.put("cgst_tax", percentage / 2);

					double gst = Double.parseDouble(salesMap1.get("tax").toString());
					salesMap1.put("sgst", gst / 2);
					salesMap1.put("cgst", gst / 2);
				} else {
					salesMap1.put("igst", salesMap1.get("tax"));
					salesMap1.put("gst_type", false);
					salesMap1.put("igst_tax", salesMap1.get("tax_percentage"));
				}
			}
			salesMap.put("taxlist", salesGroupMap1.get(salesDataMap.getKey()));
			salesList.add(salesMap);
		}

		// Reverse the order of salesList manually
		List<Map<String, Object>> reversedSalesList = new ArrayList<>();
		for (int i = salesList.size() - 1; i >= 0; i--) {
			reversedSalesList.add(salesList.get(i));
		}

		return reversedSalesList;
	}

	@GetMapping("/sales/paidbill")
	public List<Map<String, Object>> getPaidBillList() {
		ObjectMapper object = new ObjectMapper();
		List<Map<String, Object>> report1 = saleService.getGstByTax();
		List<Map<String, Object>> report = service.getAllPaidBill();
		List<MyCompany> companyList = companyRepo.findAll();

		List<Map<String, Object>> salesDataList = object.convertValue(report, List.class);
		List<Map<String, Object>> salesDataList1 = object.convertValue(report1, List.class);
		Map<String, List<Map<String, Object>>> salesGroupMap = salesDataList.stream()
				.collect(Collectors.groupingBy(action -> action.get("sales_id").toString()));
		Map<String, List<Map<String, Object>>> salesGroupMap1 = salesDataList1.stream()
				.collect(Collectors.groupingBy(action -> action.get("sales_id").toString()));

		List<Map<String, Object>> salesList = new ArrayList<>();
		for (Entry<String, List<Map<String, Object>>> salesDataMap : salesGroupMap.entrySet()) {
			Map<String, Object> salesMap = new HashMap<>();
			salesMap.putAll(salesDataMap.getValue().get(0));
			salesMap.put("saleslist", salesDataMap.getValue());
			if (String.valueOf(companyList.get(0).getCode()).equals(salesMap.get("code").toString())) {
				double gst = Double.parseDouble(salesMap.get("total_tax").toString());
				salesMap.put("sgst", gst / 2);
				salesMap.put("cgst", gst / 2);
				for (Map<String, Object> salesData : (List<Map<String, Object>>) salesMap.get("saleslist")) {
					double productgst = Double.parseDouble(salesData.get("tax_qty_amt").toString());
					salesData.put("sgst", productgst / 2);
					salesData.put("cgst", productgst / 2);
				}
			} else {
				salesMap.put("igst", salesMap.get("total_tax"));
				for (Map<String, Object> salesData : (List<Map<String, Object>>) salesMap.get("saleslist")) {
					salesData.put("igst", salesData.get("tax_qty_amt"));
				}
			}
			for (Map<String, Object> salesMap1 : salesGroupMap1.get(salesDataMap.getKey())) {
				if (String.valueOf(companyList.get(0).getCode()).equals(salesMap.get("code").toString())) {
					double percentage = Double.parseDouble(salesMap1.get("tax_percentage").toString());
					salesMap1.put("gst_type", true);
					salesMap1.put("sgst_tax", percentage / 2);
					salesMap1.put("cgst_tax", percentage / 2);

					double gst = Double.parseDouble(salesMap1.get("tax").toString());
					salesMap1.put("sgst", gst / 2);
					salesMap1.put("cgst", gst / 2);

				} else {
					salesMap1.put("igst", salesMap1.get("tax"));
					salesMap1.put("gst_type", false);
					salesMap1.put("igst_tax", salesMap1.get("tax_percentage"));

				}
			}
			salesMap.put("taxlist", salesGroupMap1.get(salesDataMap.getKey()));
			salesList.add(salesMap);
			salesList.sort(Comparator.comparing(salesData -> salesData.get("sales_id").toString()));
			Collections.reverse(salesList);
		}

		return salesList;
	}

	@GetMapping("/sales/unpaidbill")
	public List<Map<String, Object>> getUnPaidBillList() {
		ObjectMapper object = new ObjectMapper();
		List<Map<String, Object>> report1 = saleService.getGstByTax();
		List<Map<String, Object>> report = service.getAllPaidBill();
		List<MyCompany> companyList = companyRepo.findAll();
		List<Map<String, Object>> salesDataList = object.convertValue(report, List.class);
		List<Map<String, Object>> salesDataList1 = object.convertValue(report1, List.class);
		Map<String, List<Map<String, Object>>> salesGroupMap = salesDataList.stream()
				.collect(Collectors.groupingBy(action -> action.get("sales_id").toString()));
		Map<String, List<Map<String, Object>>> salesGroupMap1 = salesDataList1.stream()
				.collect(Collectors.groupingBy(action -> action.get("sales_id").toString()));

		List<Map<String, Object>> salesList = new ArrayList<>();
		for (Entry<String, List<Map<String, Object>>> salesDataMap : salesGroupMap.entrySet()) {
			Map<String, Object> salesMap = new HashMap<>();
			salesMap.putAll(salesDataMap.getValue().get(0));
			salesMap.put("saleslist", salesDataMap.getValue());

			if (String.valueOf(companyList.get(0).getCode()).equals(salesMap.get("code").toString())) {
				double gst = Double.parseDouble(salesMap.get("total_tax").toString());
				salesMap.put("sgst", gst / 2);
				salesMap.put("cgst", gst / 2);
				for (Map<String, Object> salesData : (List<Map<String, Object>>) salesMap.get("saleslist")) {
					double productgst = Double.parseDouble(salesData.get("tax_qty_amt").toString());
					salesData.put("sgst", productgst / 2);
					salesData.put("cgst", productgst / 2);
				}
			} else {
				salesMap.put("igst", salesMap.get("total_tax"));
				for (Map<String, Object> salesData : (List<Map<String, Object>>) salesMap.get("saleslist")) {
					salesData.put("igst", salesData.get("tax_qty_amt"));
				}
			}
			for (Map<String, Object> salesMap1 : salesGroupMap1.get(salesDataMap.getKey())) {
				if (String.valueOf(companyList.get(0).getCode()).equals(salesMap.get("code").toString())) {
					double percentage = Double.parseDouble(salesMap1.get("tax_percentage").toString());
					salesMap1.put("gst_type", true);
					salesMap1.put("sgst_tax", percentage / 2);
					salesMap1.put("cgst_tax", percentage / 2);

					double gst = Double.parseDouble(salesMap1.get("tax").toString());
					salesMap1.put("sgst", gst / 2);
					salesMap1.put("cgst", gst / 2);

				} else {
					salesMap1.put("igst", salesMap1.get("tax"));
					salesMap1.put("gst_type", false);
					salesMap1.put("igst_tax", salesMap1.get("tax_percentage"));

				}
			}
			salesMap.put("taxlist", salesGroupMap1.get(salesDataMap.getKey()));
			salesList.sort(Comparator.comparing(salesData -> salesData.get("sales_id").toString()));
			Collections.reverse(salesList);
		}

		return salesList;
	}

	@Autowired
	private ReturnPaymentRepository returnPaymentRepository;

	@PostMapping(value = "/sales/save")
	@Transactional
	private long saveBook(@RequestBody Sales sales) {
		List<Long> productIdList = sales.getSaleslist().stream().map(action -> action.getProductid())
				.collect(Collectors.toList());
		List<Product> productList = repo.findAllById(productIdList);

		for (Product product : productList) {
			for (SalesList salesList : sales.getSaleslist()) {
				if (product.getProductid() == salesList.getProductid()) {
					product.setStock(
							Objects.nonNull(product.getStock()) ? product.getStock() - salesList.getShipped_qty()
									: -salesList.getShipped_qty());
					repo.save(product);
				}
			}
		}

		repo.saveAll(productList);
		service.save(sales);
		double balance = sales.getBalance();
		double received = sales.getReceived();
		Date invoice_date = sales.getInvoice_date();
		long sales_id = sales.getSales_id();
		long distributorid = sales.getDistributorid();
		int invoice_no = sales.getInvoice_no();
		ReturnPayment payment = new ReturnPayment();

		payment.setBalance(balance);
		payment.setReceived(received);
		payment.setCurrentReceived(received);
		payment.setSales_id(sales_id);
		payment.setInvoice_date(invoice_date);
		payment.setDistributorid(distributorid);
		payment.setInvoice_no(invoice_no);
		returnPaymentRepository.save(payment);
		return sales.getSales_id();
	}

	@RequestMapping("/sales/{id}")
	private Sales getBooks(@PathVariable(name = "id") long sales_id) {
		return service.getDetailBySalesId(sales_id);
	}

	@RequestMapping("/sales/report")
	public List<Map<String, Object>> getAllSales() {
		return service.findAllSales();
	}

	@PutMapping("/sales/edit/{id}")
	public ResponseEntity<Sales> updateOrder(@PathVariable("id") Long sales_id, @RequestBody Sales sales) {
		try {
			Sales existingSales = service.findById(sales_id);

			if (existingSales == null) {
				return ResponseEntity.notFound().build();
			}
			existingSales.setBalance(sales.getBalance());
			existingSales.setDescription(sales.getDescription());
			existingSales.setInvoice_date(sales.getInvoice_date());
			existingSales.setPayment_type(sales.getPayment_type());
			existingSales.setSaleslist(sales.getSaleslist());
			existingSales.setDistributorid(sales.getDistributorid());
			existingSales.setRoundoff(sales.getRoundoff());
			existingSales.setReceived(sales.getReceived());
			existingSales.setPhone_number(sales.getPhone_number());
			existingSales.setRoundoff_amount(sales.getRoundoff_amount());
			existingSales.setShipping_address(sales.getShipping_address());
			existingSales.setTotal_amount(sales.getTotal_amount());
			existingSales.setTotal_tax(sales.getTotal_tax());
			existingSales.setTotal_qty(sales.getTotal_qty());
			existingSales.setTotal_price(sales.getTotal_price());
			service.save(existingSales);
			return ResponseEntity.ok(existingSales);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	@DeleteMapping("/sales/delete/{id}")
//	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long sales_id) {
//		service.deleteSalesById(sales_id);
//		return ResponseEntity.ok("Customer deleted successfully");
//	}

	@DeleteMapping("/sales/delete/{id}")
	public ResponseEntity<String> deleteSales(@PathVariable("id") Long sales_id) {
		Sales sales = service.getDetailBySalesId(sales_id);
		List<Long> productIdList = sales.getSaleslist().stream().map(SalesList::getProductid)
				.collect(Collectors.toList());
		List<Product> productList = repo.findAllById(productIdList);
		for (Product product : productList) {
			for (SalesList salesList : sales.getSaleslist()) {
				if (product.getProductid() == salesList.getProductid()) {
					product.setStock(product.getStock() + salesList.getShipped_qty());
				}
			}
		}
		repo.saveAll(productList);
		service.deleteSalesById(sales_id);
		return ResponseEntity.ok("Sales deleted successfully");
	}

	@GetMapping("/daybook/filter")
	public ResponseEntity<List<Map<String, Object>>> getPurchaseAndSales() {
		List<Map<String, Object>> purchaseAndSales = repos.getPurchaseAndSales();
		purchaseAndSales.removeIf(row -> Double.parseDouble(row.get("credit").toString()) == 0
				&& Double.parseDouble(row.get("debit").toString()) == 0);
		return ResponseEntity.ok(purchaseAndSales);
	}

	@PostMapping("/daybook/report1")
	public List<Map<String, Object>> getDayBookByStartDate(@RequestBody Map<String, Object> requestBody) {
		LocalDate startDate = LocalDate.parse(requestBody.get("startDate").toString(), DateTimeFormatter.ISO_DATE);
		List<Map<String, Object>> dayBook = service.getDayBookByStartDate(startDate);
		dayBook.removeIf(row -> Double.parseDouble(row.get("credit").toString()) == 0
				&& Double.parseDouble(row.get("debit").toString()) == 0);
		return dayBook != null ? dayBook : Collections.emptyList();
	}

}

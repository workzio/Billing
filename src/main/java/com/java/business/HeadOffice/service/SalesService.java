package com.java.business.HeadOffice.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.business.HeadOffice.entity.Sales;
import com.java.business.HeadOffice.repository.SalesRepository;

@Service
public class SalesService {

	@Autowired
	private SalesRepository repo;

	public Iterable<Sales> listAll() {
		return this.repo.findAll();
	}

	public void Saveorupdate(Sales sales) {
		repo.save(sales);
	}

	public Sales getDetailBySalesId(long id) {
		return repo.findById(id).get();
	}

	public void save(Sales existingSales) {
		repo.save(existingSales);

	}

	public Sales findById(Long id) {
		return repo.findById(id).get();
	}

	public void deleteSalesById(Long id) {
		repo.deleteById(id);

	}

	public List<Map<String, Object>> findAllSales() {
		return repo.getAllSales();
	}


	public long getSalesCount() {
		return repo.count();
	}

	public List<Map<String, Object>> getAllDistributorDetail(long distributorid) {

		return repo.getAllDistributorDetails(distributorid);

	}

	public List<Map<String, Object>> getAllSalesInvoices() {
		return repo.getAllDistributorDetails();
	}
	
	public List<Map<String, Object>> getAllPaidBill() {
		return repo.getAllPaidBillDetails();
	}


	public List<Map<String, Object>> getAllSalesInvoices(long distributorid) {
		return repo.getAllDistributorDetails(distributorid);
	}

	public void delete(Sales sales) {
		repo.delete(sales);
	}

	        int nextInvoiceNumber = 1;

	public Map<String, Object> getNextInvoiceNumber() {
	    Integer result = repo.getLastInvoiceNumber();
	    
	    if (result == null) {
	        int nextInvoiceNumber = 1;
	        Map<String, Object> responseData = new HashMap<>();
	        responseData.put("invoice_no", nextInvoiceNumber);
	        return responseData;
	    } else {
	        int nextInvoiceNumber = result.intValue() + 1;
	        Map<String, Object> responseData = new HashMap<>();
	        responseData.put("invoice_no", nextInvoiceNumber);
	        return responseData;
	    }
	}


	
	public List<Map<String, Object>> findAllBySalesDetails() {
		return repo.findAllBySalesDetails();
	}



	public List<Map<String, Object>> getAllSalesBetweenDates(LocalDate startDate, LocalDate endDate) {
		return repo.getAllSalesBetweenDates(startDate, endDate);
	}
	
	
	 
	 public List<Map<String, Object>> getDayBookByStartDate(LocalDate startDate) {
	        return repo.getDayBookByStartDate(startDate);
	    }
	 
	 public List<Map<String, Object>> getAllPaidBillDetailsByDistributor(long distributorid){
		 return repo.getAllPaidBillDetailsByDistributor(distributorid);
	 }

	 public Map<String, Object> getSalesDetails(Long salesId) {
	        Optional<Sales> salesOptional = repo.findById(salesId);
	        if (salesOptional.isPresent()) {
	            Sales sales = salesOptional.get();
	            Map<String, Object> salesDetails = new HashMap<>();
	            salesDetails.put("received", sales.getReceived());
	            salesDetails.put("invoiceDate", sales.getInvoice_date());
	            // Add more sales details as needed
	            return salesDetails;
	        }
	        return null;
	    }

	 
	 
	 
}
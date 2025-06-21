package com.java.business.HeadOffice.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.java.business.HeadOffice.entity.ReturnPayment;
import com.java.business.HeadOffice.entity.Sales;
import com.java.business.HeadOffice.repository.SalesRepository;
import com.java.business.HeadOffice.service.ReturnPaymentService;

@RestController
@CrossOrigin
public class ReturnPaymentController {

	@Autowired
	private ReturnPaymentService service;

	@Autowired
	private SalesRepository Service;

	@PostMapping(value = "/total/sales/save")
	public String saveBook(@RequestBody ReturnPayment payment) {
		double currentReceived = payment.getCurrentReceived();
		double balance = payment.getBalance();
		payment.setCurrentReceived(currentReceived);
		payment.setBalance(balance - currentReceived);
		service.saveReturnPayment(payment);

		Sales sales = service.getSalesById(payment.getSales_id());
		double newBalance = sales.getBalance() - currentReceived;
		double newreceived = sales.getReceived() + currentReceived;
		sales.setBalance(newBalance);
		sales.setReceived(newreceived);
		service.saveSales(sales);

		if (newBalance == 0) {
			return "Payment successfully saved. Balance is now 0.";
		} else {
			return "Payment successfully saved. Balance remaining: " + newBalance;
		}
	}

	@GetMapping("/total/sales/{sales_id}")
	public ResponseEntity<Map<String, Object>> getAllPaymentDetails(@PathVariable Long sales_id) {
		Optional<Sales> salesOptional = Service.findById(sales_id);
		if (salesOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		List<Map<String, Object>> paymentDetails = service.getAllPaymentDetails(sales_id);
		if (paymentDetails.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<Map<String, Object>> report = new ArrayList<>();
		Map<String, Object> reportEntry1 = new HashMap<>();
		reportEntry1.put("amount", paymentDetails.get(0).get("received"));
		reportEntry1.put("payment_date", paymentDetails.get(0).get("invoice_date"));
		reportEntry1.put("sales_id", paymentDetails.get(0).get("sales_id"));
		reportEntry1.put("status", paymentDetails.get(0).get("status"));
		reportEntry1.put("paymentId", paymentDetails.get(0).get("payment_id"));

		report.add(reportEntry1);
		for (Map<String, Object> payment : paymentDetails) {
			Map<String, Object> reportEntry = new HashMap<>();
			reportEntry.put("amount", payment.get("current_received"));
			reportEntry.put("sales_id", payment.get("sales_id"));
			reportEntry.put("payment_date", payment.get("payment_date"));
			reportEntry.put("status", payment.get("status"));
			reportEntry.put("paymentId", payment.get("payment_id"));
//			reportEntry.put("sales_id", payment.get(0).get("sales_id"));
			report.add(reportEntry);
		}
		Map<String, Object> response = new HashMap<>();
		response.put("report", report);
		response.put("paymentDetails", paymentDetails);
		return ResponseEntity.ok(response);
	}



	@PutMapping("/total/sales/edit/{paymentId}")
	public ResponseEntity<String> updateOrder(@PathVariable("paymentId") Long paymentId,
			@RequestBody ReturnPayment updatedPayment) {

		try {
			ReturnPayment existingPayment = service.findById(paymentId);
			if (existingPayment == null) {
				return ResponseEntity.notFound().build();
			}

			if (existingPayment.isStatus()) {
				return ResponseEntity.badRequest().body("Payment has already been edited and cannot be updated again.");
			}

			existingPayment.setReturnPaymentType(updatedPayment.getReturnPaymentType());
			existingPayment.setPaymentDate(updatedPayment.getPaymentDate());

			double existingReceived = existingPayment.getCurrentReceived();
			double updatedReceived = updatedPayment.getCurrentReceived();
			double balance = existingPayment.getBalance();

			if (updatedReceived < existingReceived) {
				balance += (existingReceived - updatedReceived);
			} else if (updatedReceived > existingReceived) {
				balance -= (updatedReceived - existingReceived);
			} else {
				return ResponseEntity.ok()
						.body("No changes made. The currentReceived is the same as the existing value.");
			}

			existingPayment.setCurrentReceived(updatedReceived);
			existingPayment.setReceived(updatedReceived);
			existingPayment.setBalance(balance);
			existingPayment.setStatus(true);
			service.save(existingPayment);

			Sales sales = service.getSalesById(existingPayment.getSales_id());
			double salesBalance = sales.getBalance();
			double salesReceived = sales.getReceived();

			if (updatedReceived < existingReceived) {
				salesBalance += (existingReceived - updatedReceived);
			} else if (updatedReceived > existingReceived) {
				salesBalance -= (updatedReceived - existingReceived);
			}

			salesReceived = salesReceived - existingReceived + updatedReceived;

			sales.setBalance(salesBalance);
			sales.setReceived(salesReceived);
			service.saveSales(sales);

			if (salesBalance == 0) {
				return ResponseEntity.ok().body("Payment successfully saved. Balance is now 0.");
			} else {
				return ResponseEntity.ok().body("Payment successfully saved. Balance remaining: " + salesBalance);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}

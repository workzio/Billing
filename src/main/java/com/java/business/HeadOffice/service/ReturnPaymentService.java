package com.java.business.HeadOffice.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.business.HeadOffice.entity.ReturnPayment;
import com.java.business.HeadOffice.entity.Sales;
import com.java.business.HeadOffice.repository.ReturnPaymentRepository;
import com.java.business.HeadOffice.repository.SalesRepository;

@Service
public class ReturnPaymentService {

	@Autowired
	private ReturnPaymentRepository repo;

	@Autowired
	private SalesRepository salesRepository;

	public Iterable<ReturnPayment> listAll() {
		return this.repo.findAll();

	}

	public List<Map<String, Object>> getAllPaymentDetails(Long sales_id) {
		return repo.getAllPaymentDetails(sales_id);
	}

	public void saveReturnPayment(ReturnPayment payment) {
		repo.save(payment);
	}

	public Sales getSalesById(long salesId) {
		return salesRepository.findById(salesId).orElse(null);
	}

	public void saveSales(Sales sales) {
		salesRepository.save(sales);
	}

	/////// edit
	public void save(ReturnPayment existingRole) {
		repo.save(existingRole);
	}

	public ReturnPayment findById(Long paymentId) {
		return repo.findById(paymentId).get();
	}

}

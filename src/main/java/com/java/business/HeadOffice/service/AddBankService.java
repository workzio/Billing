package com.java.business.HeadOffice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.business.HeadOffice.entity.AddBank;
import com.java.business.HeadOffice.repository.AddBankRepository;

@Service
public class AddBankService {

	@Autowired
	private AddBankRepository addBankRepository;

	public Iterable<AddBank> listAll() {
		return addBankRepository.findAll();
	}

	public void bankDetails(AddBank bank) {
		this.addBankRepository.save(bank);
	}

	public AddBank getBankById(long id) {
		return addBankRepository.findById(id).get();
	}

	public void update(AddBank bank, long id) {
		addBankRepository.save(bank);
	}

	//////// delete/////////
	public void deleteBankById(Long id) {
		addBankRepository.deleteById(id);

	}

	/////// edit///////
	public void save(AddBank existingBank) {
		addBankRepository.save(existingBank);
	}

	public AddBank findById(Long id) {
		return addBankRepository.findById(id).get();
	}

	public List<Map<String, Object>> getAllInTranscationDetails() {
		return addBankRepository.getAllInTranscationDetails();
	}


	public List<Map<String, Object>> getAllInTranscationDetailsById(Long inTranscationId) {
		return addBankRepository.getAllInTranscationDetailsById(inTranscationId);
	}

	//////////////////in transcation////////////
	public List<Map<String, Object>> getAllInTranscationDetailsByTime(LocalDate startdate, LocalDate enddate) {
		return addBankRepository.getAllInTranscationDetailsByTime(startdate, enddate);
	}
	

}

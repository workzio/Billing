package com.java.business.HeadOffice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.business.HeadOffice.entity.NewBank;
import com.java.business.HeadOffice.repository.NewBankRepository;

@Service
public class NewBankService {

	@Autowired
	private NewBankRepository newBankRepository;
	
	public Iterable<NewBank> listAll(){
		return this.newBankRepository.findAll();	}
	
	public void Save(NewBank bank) {
		newBankRepository.save(bank);
	}

	public NewBank getNewBankById(long id) {
		return newBankRepository.findById(id).get();
	}

	public void update(NewBank bank, long id) {
		newBankRepository.save(bank);
	}


	////////delete
	public void deleteNewBankById(Long id) {
		newBankRepository.deleteById(id);
		
	}

	
	///////edit
	public void save(NewBank existingNewBank) {
		newBankRepository.save(existingNewBank);
	}

	public NewBank findById(Long id) {
		return newBankRepository.findById(id).get();
	}
	
	public List<Map<String, Object>> getAllDetailsOfOwnAndCustomerAccount(){
		return newBankRepository.getAllDetailsOfOwnAndCustomerAccount();
	}
	
	public List<Map<String, Object>> getAllDetailsOfOwnAndCustomerAccountByDate(LocalDate startdate, LocalDate enddate) {
		return newBankRepository.getAllDetailsOfOwnAndCustomerAccountByDate(startdate, enddate);
	}
	
	public List<Map<String, Object>> getAllNewBankById(Long newBankId){
		return newBankRepository.getAllNewBankById(newBankId);
	}
	

}

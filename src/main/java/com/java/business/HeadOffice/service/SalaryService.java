package com.java.business.HeadOffice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.business.HeadOffice.entity.Salary;
import com.java.business.HeadOffice.repository.SalaryRepository;

@Service
public class SalaryService {

	@Autowired
	private SalaryRepository salaryRepository;
	
	public Iterable<Salary> listAll(){
		return this.salaryRepository.findAll();	}
	
	public void Save(Salary salary) {
		salaryRepository.save(salary);
	}

	public Salary getSalaryById(long id) {
		return salaryRepository.findById(id).get();
	}

	public void update(Salary salary, long id) {
		salaryRepository.save(salary);
	}


	////////delete
	public void deleteSalaryById(Long id) {
		salaryRepository.deleteById(id);
		
	}

	
	///////edit
	public void save(Salary existingSalary) {
		salaryRepository.save(existingSalary);
	}

	public Salary findById(Long id) {
		return salaryRepository.findById(id).get();
	}

	public List<Map<String, Object>> getAllSalaryDetails(){
		return salaryRepository.getAllSalaryDetails();
	}
	
	public List<Map<String, Object>> getAllSalaryDetailsByMember(long memberid){
		return salaryRepository.getAllSalaryDetailsByMember(memberid);
	}
	
	public List<Map<String, Object>> getAllAdvanceAmountDetails(long memberid,LocalDate salary_date){
		return salaryRepository.getAllAdvanceAmountDetails(memberid, salary_date);
	}
}

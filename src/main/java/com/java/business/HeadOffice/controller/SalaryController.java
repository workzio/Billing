package com.java.business.HeadOffice.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
import com.java.business.HeadOffice.entity.Salary;
import com.java.business.HeadOffice.service.SalaryService;

@RestController
@CrossOrigin
public class SalaryController {

	@Autowired
	private SalaryService salaryService;

	@GetMapping("/salary")
	public ResponseEntity<?> getUser() {
		try {
			Iterable<Salary> members = salaryService.listAll();
			return ResponseEntity.ok(members);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while retrieving salary");
		}
	}

	@PostMapping(value = "/salary/save")
	public ResponseEntity<String> saveMember(@RequestBody Salary salary) {
		try {

			double amount = salary.getAmount();
			Date salaryDate = salary.getSalaryDate();

			if (salary.getPaymentType().equals("advanceAmount")) {
				salary.setAdvanceAmount(amount);
				salary.setAdvanceAmountDate(salaryDate);
				salary.setFullPayment(0.0);
			} else if (salary.getPaymentType().equals("fullPayment")) {
				salary.setAdvanceAmount(0.0);
				salary.setFullPayment(amount);
			}
			salaryService.save(salary);
			long salaryId = salary.getSalaryId();
			return ResponseEntity.ok("Salary saved successfully. Salary ID: " + salaryId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving salary: " + e.getMessage());
		}
	}

	@RequestMapping("/salary/{id}")
	private Salary getBooks(@PathVariable(name = "id") long id) {
		return salaryService.getSalaryById(id);
	}

	@PutMapping("/salary/edit/{id}")
	public ResponseEntity<Salary> updateOrder(@PathVariable("id") Long id, @RequestBody Salary salary) {
		try {
			Salary existingSalary = salaryService.findById(id);
			if (existingSalary == null) {
				return ResponseEntity.notFound().build();
			}
			existingSalary.setAdvanceAmount(salary.getAdvanceAmount());
			existingSalary.setAmount(salary.getAmount());
			existingSalary.setFullPayment(salary.getFullPayment());
			existingSalary.setMemberid(salary.getMemberid());
			existingSalary.setPaymentType(salary.getPaymentType());
			existingSalary.setRoleId(salary.getRoleId());
			existingSalary.setSalaryDate(salary.getSalaryDate());
			salaryService.save(existingSalary);
			return ResponseEntity.ok(existingSalary);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/salary/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		salaryService.deleteSalaryById(id);
		return ResponseEntity.ok("memer deleted successfully");
	}

	//////////////////// member salary details/////////////////

	@GetMapping("/member/alldetails")
	public List<Map<String, Object>> getAllSalaryDetails() {
		return salaryService.getAllSalaryDetails();
	}

	@GetMapping("/member/role/{memberid}")
	public List<Map<String, Object>> getAllSalaryDetailsByMember(@PathVariable Long memberid) {
		return salaryService.getAllSalaryDetailsByMember(memberid);
	}

	@PostMapping("/member/report")
	public List<Map<String, Object>> getAllAdvanceAmountDetails(@RequestBody Map<String, Object> requestBody,
			Salary salary) {
//		LocalDate salary_date = LocalDate.parse(requestBody.get("salary_date").toString(), DateTimeFormatter.ISO_DATE);
		LocalDate salary_date = LocalDate.now();
		long memberid = Long.parseLong(requestBody.get("memberid").toString());
		return salaryService.getAllAdvanceAmountDetails(memberid, salary_date);
	}

}

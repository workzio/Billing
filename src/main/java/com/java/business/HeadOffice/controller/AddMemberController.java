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
import org.springframework.web.bind.annotation.*;
import com.java.business.HeadOffice.entity.AddMember;
import com.java.business.HeadOffice.service.AddMemberService;

@RestController
@CrossOrigin
public class AddMemberController {

	@Autowired
	private AddMemberService service;

	@GetMapping("/member")
	public ResponseEntity<?> getUser() {
		try {
			Iterable<AddMember> members = service.listAll();
			return ResponseEntity.ok(members); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while retrieving members"); 
		}
	}

	@PostMapping(value = "/member/save")
	public ResponseEntity<String> saveMember(@RequestBody AddMember member) {
		try {
			service.save(member);
			long memberId = member.getMemberid();
			return ResponseEntity.ok("Member saved successfully. Member ID: " + memberId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving member: " + e.getMessage());
		}
	}

	@GetMapping("/total/rolelist/{memberid}")
	public List<Map<String, Object>> getAllRoleId(@PathVariable("memberid") Long memberid) {
		return service.getAllRoleId(memberid);
	}

	@RequestMapping("/member/{id}")
	private AddMember getBooks(@PathVariable(name = "id") long memberid) {
		return service.getUserById(memberid);
	}

	@PutMapping("/member/edit/{id}")
	public ResponseEntity<AddMember> updateOrder(@PathVariable("id") Long id, @RequestBody AddMember customer) {
		try {
			AddMember existingCustomer = service.findById(id);
			if (existingCustomer == null) {
				return ResponseEntity.notFound().build();
			}
			existingCustomer.setAadharno(customer.getAadharno());
			existingCustomer.setName(customer.getName());
			existingCustomer.setPhone(customer.getPhone());
			existingCustomer.setEmail(customer.getEmail());
			existingCustomer.setDob(customer.getDob());
			existingCustomer.setPassword(customer.getPassword());
			existingCustomer.setGender(customer.getGender());
			existingCustomer.setPanNo(customer.getPanNo());
			existingCustomer.setAddress(customer.getAddress());
			existingCustomer.setDate(customer.getDate());
			existingCustomer.setRoleId(customer.getRoleId());
			existingCustomer.setDescription(customer.getDescription());
			service.save(existingCustomer);
			return ResponseEntity.ok(existingCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	@DeleteMapping("/member/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		service.deleteMemberById(id);
		return ResponseEntity.ok("memer deleted successfully");
	}

	
	@GetMapping("/member/role")
	private List<Map<String, Object>> findAllBYCategoryAndProduct() {
		List<Map<String, Object>> memberList = new ArrayList<>();
		Iterable<Map<String, Object>> role = service.findAllMemberRole();
		Map<String, List<Map<String, Object>>> roleGroupMap = StreamSupport.stream(role.spliterator(), false)
				.collect(Collectors.groupingBy(action -> action.get("role_id").toString()));
		for (Entry<String, List<Map<String, Object>>> roleList : roleGroupMap.entrySet()) {
			Map<String, Object> roleMap = new HashMap<>();
			roleMap.put("role_id", roleList.getKey());
			roleMap.put("role_name", roleList.getValue().get(0).get("role_name"));
			roleMap.put("member", roleList.getValue());
			memberList.add(roleMap);
		}
		return memberList;
	}

}

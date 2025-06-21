package com.java.business.HeadOffice.controller;

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

import com.java.business.HeadOffice.entity.Role;
import com.java.business.HeadOffice.service.RoleService;

@RestController
@CrossOrigin
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/role")
	public ResponseEntity<?> getUser() {
	    try {
	        Iterable<Role> role = roleService.listAll();
	        return ResponseEntity.ok(role); 
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error occurred while retrieving members"); // Return error response
	    }
	}

	@PostMapping(value = "/role/save")
	public ResponseEntity<String> saveMember(@RequestBody Role role) {
	    try {
	        roleService.save(role);
	        long roleId = role.getRoleId();
	        return ResponseEntity.ok("Role saved successfully. Member ID: " + roleId);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Role: " + e.getMessage());
	    }
	}

	@RequestMapping("/role/{roleId}")
	private Role getBooks(@PathVariable(name = "id") long roleId) {
		return roleService.getUserById(roleId);
	}

	@PutMapping("/role/edit/{roleId}")
	public ResponseEntity<Role> updateOrder(@PathVariable("id") Long roleId, @RequestBody Role role) {
		try {
			Role existingRole = roleService.findById(roleId);
			if (existingRole == null) {
				return ResponseEntity.notFound().build();
			}
			existingRole.setRoleName(role.getRoleName());
			roleService.save(existingRole);
			return ResponseEntity.ok(existingRole);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	///////////////role joining
	
	@GetMapping("/rolelist")
	public List<Map<String, Object>> getAllRole(){
		return roleService.getAllRole();
	}
	
	



	@DeleteMapping("/role/delete/{roleId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long roleId) {
		roleService.deleteMemberById(roleId);
		return ResponseEntity.ok("Role deleted successfully");
	}
}

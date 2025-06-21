package com.java.business.HeadOffice.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.java.business.HeadOffice.entity.Attendance;
import com.java.business.HeadOffice.entity.AttendanceList;
import com.java.business.HeadOffice.repository.AttendanceRepository;
import com.java.business.HeadOffice.service.AttendanceService;

@RestController
@CrossOrigin
public class AttendanceController {
	@Autowired
	private AttendanceService service;

	@GetMapping("/attendance")
	public ResponseEntity<?> getUser() {
		try {
			Iterable<Attendance> members = service.listAll();
			return ResponseEntity.ok(members);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while retrieving members");

		}
	}

	@PostMapping(value = "/attendance/save")
	public ResponseEntity<String> saveMember(@RequestBody Attendance attendance) {
		try {
			Date attendanceDate = attendance.getAttendanceDate();
			List<AttendanceList> attendanceList = attendance.getAttendance();

			for (AttendanceList attendanceLoop : attendanceList) {
				if (attendanceLoop.isAttstatus()) {
					attendanceLoop.setPresent(true);
					attendanceLoop.setAbsent(false);
				} else {
					attendanceLoop.setPresent(false);
					attendanceLoop.setAbsent(true);
				}

				if ("Full".equals(attendanceLoop.getSection())) {
					attendanceLoop.setFullDay(true);
					attendanceLoop.setHalfDay(false);
				} else if ("Half".equals(attendanceLoop.getSection())) {
					attendanceLoop.setFullDay(false);
					attendanceLoop.setHalfDay(true);
				}
			}

			Optional<Attendance> existingAttendance = attendanceRepository.findByAttendanceDate(attendanceDate);
			if (existingAttendance.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Attendance for date " + attendanceDate + " already exists.");
			} else {
				attendanceRepository.save(attendance);
				System.out.println("Saving attendance: " + attendance);
			}

			long attendanceId = attendance.getAttendanceId();
			return ResponseEntity.ok("Attendance saved successfully. Attendance ID: " + attendanceId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving attendance: " + e.getMessage());
		}
	}

	@PutMapping("/attendance/edit/{id}")
	public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody Attendance updatedAttendance) {
		try {
			Attendance existingAttendance = service.findById(id);
			if (existingAttendance == null) {
				return ResponseEntity.notFound().build();
			}

			existingAttendance.setAttendanceDate(updatedAttendance.getAttendanceDate());

			List<AttendanceList> existingAttendanceList = existingAttendance.getAttendance();
			List<AttendanceList> updatedAttendanceList = updatedAttendance.getAttendance();

			for (int i = 0; i < updatedAttendanceList.size(); i++) {
				AttendanceList updatedChild = updatedAttendanceList.get(i);
				AttendanceList existingChild;

				if (i < existingAttendanceList.size()) {
					existingChild = existingAttendanceList.get(i);
					existingChild.setFullDay("Full".equals(updatedChild.getSection()));
					existingChild.setHalfDay("Half".equals(updatedChild.getSection()));
					existingChild.setSection(updatedChild.getSection());

//					if (!existingChild.isAttstatus()) {
//						return ResponseEntity.ok("Attendance status is in false status you cannot able to edit");
//					} else {
//						existingChild.setPresent(true);
//						existingChild.setAbsent(false);
//					}

					if (existingChild.isAttstatus()) {
						existingChild.setPresent(true);
						existingChild.setAbsent(false);
					} else {
						existingChild.setPresent(false);
						existingChild.setAbsent(true);
					}

				} else {
					existingChild = new AttendanceList();
					existingChild.setFullDay("Full".equals(updatedChild.getSection()));
					existingChild.setHalfDay("Half".equals(updatedChild.getSection()));
					existingChild.setSection(updatedChild.getSection());
					existingAttendanceList.add(existingChild);
				}
			}

			service.save(existingAttendance);

			return ResponseEntity.ok(existingAttendance);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Autowired
	private AttendanceRepository attendanceRepository;

	@GetMapping("/attendance/member")
	public List<Map<String, Object>> getAllMemberDetails() {
		return attendanceRepository.getAllMemberDetails();
	}

	@GetMapping("/attendance/currentdate")
	public List<Map<String, Object>> getAllMemberDetailsByMemberByDate() {
		return attendanceRepository.getAllMemberDetailsByMemberByDate();
	}

	@GetMapping("/attendance/{memberid}")
	public List<Map<String, Object>> getAllMemberDetailsByMemberId(@PathVariable Long memberid) {
		return attendanceRepository.getAllMemberDetailsByMemberId(memberid);
	}

}

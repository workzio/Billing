package com.java.business.HeadOffice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attendancelist")
public class AttendanceList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long attendanceListId;
	private boolean fullDay;
	private boolean halfDay;
	private boolean present;
	private boolean absent;
	private long memberid;
	private boolean attstatus;
	private String section;
	
	
	public long getAttendanceListId() {
		return attendanceListId;
	}
	public void setAttendanceListId(long attendanceListId) {
		this.attendanceListId = attendanceListId;
	}
	public boolean isFullDay() {
		return fullDay;
	}
	public void setFullDay(boolean fullDay) {
		this.fullDay = fullDay;
	}
	public boolean isHalfDay() {
		return halfDay;
	}
	public void setHalfDay(boolean halfDay) {
		this.halfDay = halfDay;
	}
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
	public boolean isAbsent() {
		return absent;
	}
	public void setAbsent(boolean absent) {
		this.absent = absent;
	}
	public long getMemberid() {
		return memberid;
	}
	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}
	public boolean isAttstatus() {
		return attstatus;
	}
	public void setAttstatus(boolean attstatus) {
		this.attstatus = attstatus;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public AttendanceList() {
		super();
	}
	
	
}

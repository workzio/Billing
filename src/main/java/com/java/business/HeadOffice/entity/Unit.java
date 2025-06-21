package com.java.business.HeadOffice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="unit")
public class Unit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long unit_id;
	private String unitname;
	
	
	public long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(long unit_id) {
		this.unit_id = unit_id;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public Unit(long unit_id, String unitname) {
		super();
		this.unit_id = unit_id;
		this.unitname = unitname;
	}
	@Override
	public String toString() {
		return "Unit [unit_id=" + unit_id + ", unitname=" + unitname + "]";
	}
	public Unit() {
		super();
	}
	
	
	
	
}

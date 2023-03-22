package com.mycompany.rating.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Rating implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Size(min = 4)
	@Column(name = "name")
	private String name;

	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "salary_increase", nullable = false)
	private int salaryIncreas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalaryIncreas() {
		return salaryIncreas;
	}

	public void setSalaryIncreas(int salaryIncreas) {
		this.salaryIncreas = salaryIncreas;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

}
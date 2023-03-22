package com.mycompany.employee.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Employee implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Size(min=4)
	@Column(name = "name")
	private String name;
	
	@Column(name= "salary",nullable=false)
	private int salary;
	
	public Employee() {

	}

	public Long getId() {
		return id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public int getsalary() {
		return salary;
	}

	public void setsalary(int salary) {
		this.salary = salary;
	}


}
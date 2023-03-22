package com.mycompany.rating.entity;

import java.io.Serializable;

public class Employee implements Serializable {

	private Long id;
	private String name;
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
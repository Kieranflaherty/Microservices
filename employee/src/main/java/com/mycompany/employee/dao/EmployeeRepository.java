package com.mycompany.employee.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}


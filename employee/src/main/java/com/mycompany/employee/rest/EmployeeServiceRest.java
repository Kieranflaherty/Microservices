package com.mycompany.employee.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mycompany.employee.dao.EmployeeRepository;
import com.mycompany.employee.entity.Employee;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class EmployeeServiceRest {

	@Autowired
	EmployeeRepository prodRepo;

	@ApiOperation(value = "Retrieve all employee API", nickname = "Retrieve all employee API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully retreive all amployee."),
			@ApiResponse(code = 400, message = "Employee retrieve bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	@GetMapping(value = "/employees")
	List<Employee> getEmployeeForCategory() {
		return prodRepo.findAll();
	}

	@ApiOperation(value = "Retrieve all employee by id API", nickname = "Retrieve all employee by id API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully retreive all employee by id"),
			@ApiResponse(code = 400, message = "Employee retrieve bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	@GetMapping("/employees/{id}")
	Optional<Employee> getEmployee(@PathVariable("id") Long id) {
		return prodRepo.findById(id);
	}

	@ApiOperation(value = "Employee created API", nickname = "Employee created API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully employee created"),
			@ApiResponse(code = 400, message = "Employee created bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	@PostMapping(value = "/employees")
	ResponseEntity<Employee> insertEmployee(@Valid @RequestBody Employee employee) {
		Employee savedEmployee = prodRepo.save(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEmployee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Employee delete API", nickname = "Employee delete API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully employee deleted"),
			@ApiResponse(code = 400, message = "Employee delete bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	@DeleteMapping(value = "/employees/{id}")
	ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {

		// First fetch an existing product and then delete it.
		Optional<Employee> optionalEmployee = prodRepo.findById(id);
		Employee existingEmployee = optionalEmployee.get();
		// Return the deleted product
		prodRepo.delete(existingEmployee);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Employee update API", nickname = "Employee update API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully employee updated"),
			@ApiResponse(code = 400, message = "Employee updated bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	@PutMapping(value = "/employees/{id}")
	ResponseEntity updateEmployee(@PathVariable("id") Long id, @RequestBody Employee Employee) {

		// First fetch an existing product and then modify it.
		Optional<Employee> optionalEmployee = prodRepo.findById(id);
		Employee existingEmployee = optionalEmployee.get();
		// Now update it back
		existingEmployee.setsalary(Employee.getsalary());
		existingEmployee.setname(Employee.getname());
		Employee savedEmployee = prodRepo.save(existingEmployee);
		// Return the updated product
		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
	}

}

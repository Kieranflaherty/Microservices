package com.mycompany.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mycompany.rating.dao.RatingRepository;
import com.mycompany.rating.entity.Employee;
import com.mycompany.rating.entity.Rating;

@Service
public class RatingService {

	private RestTemplate restTemplate = new RestTemplate();
	String employeeServiceUrl = "http://localhost:8081/";

	@Autowired
	RatingRepository prodRepo;

	public Rating saveRating(Rating rating) {
		Rating savedRating = null;
		Employee employee = getEmployee(rating);
		if (null != employee) {
			rating.setEmployeeId(employee.getId());
			savedRating = prodRepo.save(rating);
		}
		return savedRating;

	}

	public Employee getEmployee(Rating rating) {

		Employee response = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", "application/json");
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<?> request = new HttpEntity<>(headers);
			ResponseEntity<Employee> responseEntity = restTemplate.exchange(
					employeeServiceUrl + "employee/employees/" + rating.getEmployeeId(), HttpMethod.GET, request,
					Employee.class);

			if (responseEntity.getBody() != null && (responseEntity.getStatusCodeValue() == 200)) {
				response = responseEntity.getBody();
			}
			return response;
		} catch (Exception ex) {
		}

		return response;
	}
}

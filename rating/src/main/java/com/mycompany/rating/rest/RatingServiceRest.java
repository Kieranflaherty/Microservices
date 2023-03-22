package com.mycompany.rating.rest;

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

import com.mycompany.rating.dao.RatingRepository;
import com.mycompany.rating.entity.Rating;
import com.mycompany.rating.service.RatingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class RatingServiceRest {

	@Autowired
	RatingRepository prodRepo;

	@Autowired
	RatingService ratingService;

	@ApiOperation(value = "Retrieve all rating API", nickname = "Retrieve all rating API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully retreive all rating."),
			@ApiResponse(code = 400, message = "Rating retrieve bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	@GetMapping(value = "/ratings")
	List<Rating> getAllRatings() {
		return prodRepo.findAll();
	}

	@ApiOperation(value = "Retrieve all rating by id API", nickname = "Retrieve all rating by id API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully retreive all rating by id"),
			@ApiResponse(code = 400, message = "Rating retrieve bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	@GetMapping("/rating/{id}")
	Optional<Rating> getRating(@PathVariable("id") Long id) {
		return prodRepo.findById(id);
	}

	@ApiOperation(value = "Rating created API", nickname = "Rating created API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully rating created"),
			@ApiResponse(code = 400, message = "Rating created bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	@PostMapping(value = "/rating")
	ResponseEntity<Rating> insertRating(@Valid @RequestBody Rating rating) {
		Rating savedRating = ratingService.saveRating(rating);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedRating.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Rating delete API", nickname = "Rating delete API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Rating deleted"),
			@ApiResponse(code = 400, message = "Rating delete bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	@DeleteMapping(value = "/rating/{id}")
	ResponseEntity<Rating> deleteRating(@PathVariable("id") Long id) {

		// First fetch an existing rating and then delete it.
		Optional<Rating> optionalRating = prodRepo.findById(id);
		Rating existingRating = optionalRating.get();
		// Return the deleted product
		prodRepo.delete(existingRating);
		return new ResponseEntity<Rating>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Rating update API", nickname = "Rating update API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully rating updated"),
			@ApiResponse(code = 400, message = "Rating updated bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	@PutMapping(value = "/rating/{id}")
	ResponseEntity updateRating(@PathVariable("id") Long id, @RequestBody Rating rating) {

		// First fetch an existing product and then modify it.
		Optional<Rating> optionalRatinge = prodRepo.findById(id);
		Rating existingRating = optionalRatinge.get();
		// Now update it back
		existingRating.setSalaryIncreas(rating.getSalaryIncreas());
		existingRating.setName(rating.getName());
		Rating savedRating = prodRepo.save(existingRating);
		// Return the updated rating
		return new ResponseEntity<Rating>(savedRating, HttpStatus.OK);
	}

}

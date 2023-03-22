package com.mycompany.employee.jwt;

import org.springframework.util.Assert;


public class RequestValidator {


	public static void validateGenerateTokenRequest(GenerateTokenRequest request) {
		
		Assert.notNull(request, "Generate JWS token Request cannot be null");
		Assert.hasText(request.getApiKey(), "API key is required");
		Assert.notNull(request.getAppId(), "Application id required");
		Assert.notNull(request.getCustomerId(), "Customer id required");
		
	}

}

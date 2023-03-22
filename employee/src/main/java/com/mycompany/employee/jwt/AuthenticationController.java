package com.mycompany.employee.jwt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestMappings.AUTH)
public class AuthenticationController extends HTTPResponseHandler {

	final static Logger logger = Logger.getLogger(AuthenticationController.class);

	@Autowired
	private AuthService authService;

	/**
	 * Generate new JWS token.
	 *
	 */
	@RequestMapping(value = RequestMappings.GENERATE_JWS_TOKEN, method = RequestMethod.POST)
	public @ResponseBody GenerateTokenResponse generateToken(@RequestBody GenerateTokenRequest request) {

		RequestValidator.validateGenerateTokenRequest(request);

		logger.info("Inside > Generate JWS token endpoint ");

		GenerateTokenResponse response = null;

		try {
			response = authService.generateJWSToken(request);

		} catch (Exception e) {
			logger.error("Erorr occured while generating JWS token");
		}
		return response;

	}

}

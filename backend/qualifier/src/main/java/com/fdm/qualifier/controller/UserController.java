package com.fdm.qualifier.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.httpResponse.LoginResponse;
import com.fdm.qualifier.model.AuthRequest;
import com.fdm.qualifier.model.User;
import com.fdm.qualifier.security.AccountDetails;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.model.User;
import com.fdm.qualifier.service.AccountDetailsService;
import com.fdm.qualifier.service.UserService;
import com.fdm.qualifier.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3005")
public class UserController {
	private Log log = LogFactory.getLog(AccountDetailsService.class);
	
	private AuthenticationManager authenticationManager;
	private AccountDetailsService accountDetailsService;
	private UserService userService;
	private JwtUtil jwtUtil;
	
	@Autowired
	public UserController(AuthenticationManager authenticationManager, AccountDetailsService accountDetailsService,
			UserService userService, JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.accountDetailsService = accountDetailsService;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	@GetMapping("/getUser")
	public User getUser() {
		//Retrieve the currently logged in User [WIP]
		User user = new Trainee("username","password");
		user.setEmail("mail@mail.com");
		return user;
	}

	@CrossOrigin
	@PostMapping("/auth/login")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception e) {
			log.info("Bad login request received");
            log.info(HttpStatus.UNAUTHORIZED);
            return ResponseEntity.ok(new LoginResponse(HttpStatus.UNAUTHORIZED, "Invalid Credential"));
		}
		log.info("new login request received");
		User user = userService.getUserByUsername(authRequest.getUsername());
		AccountDetails accountDetails = new AccountDetails(user);
		String jwt = jwtUtil.generateToken(accountDetails);
		return ResponseEntity.ok(new LoginResponse( HttpStatus.ACCEPTED, 
                accountDetails.getUsername(), 
                ((AccountDetails) accountDetails).getAccountType(),
                jwt));
	}

}

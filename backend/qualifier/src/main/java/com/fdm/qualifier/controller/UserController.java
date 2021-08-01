package com.fdm.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.model.User;
import com.fdm.qualifier.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	

	@GetMapping("/getUser")
	public User getUser() {
		//Retrieve the currently logged in User [WIP]
		User user = new Trainee("username","password");
		user.setEmail("mail@mail.com");
		return user;
	}
		
}

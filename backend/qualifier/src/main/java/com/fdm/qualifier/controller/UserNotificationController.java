package com.fdm.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fdm.qualifier.service.UserNotificationService;

@Controller
public class UserNotificationController {

	private UserNotificationService userNotificationService;

	@Autowired
	public UserNotificationController(UserNotificationService userNotificationService) {
		super();
		this.userNotificationService = userNotificationService;
	}
	
	
	
}

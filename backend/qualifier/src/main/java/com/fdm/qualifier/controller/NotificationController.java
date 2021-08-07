package com.fdm.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fdm.qualifier.service.NotificationService;

/**
 * Notification Controller
 * @author William
 *
 */
@Controller
public class NotificationController {
	private NotificationService notificationService;
	
	@Autowired
	public NotificationController(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}
	
}

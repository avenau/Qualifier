package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.repository.UserNotificationRepository;

@Service
public class UserNotificationService {

	private UserNotificationRepository userNotificationRepo;

	@Autowired
	public UserNotificationService(UserNotificationRepository userNotificationRepo) {
		super();
		this.userNotificationRepo = userNotificationRepo;
	}
	
	
	
}

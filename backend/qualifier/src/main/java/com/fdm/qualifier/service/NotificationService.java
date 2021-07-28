package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.repository.NotificationRepo;

@Service
public class NotificationService {
	private NotificationRepo notificationRepo;

	@Autowired
	public NotificationService(NotificationRepo notificationRepo) {
		super();
		this.notificationRepo = notificationRepo;
	}
	
}

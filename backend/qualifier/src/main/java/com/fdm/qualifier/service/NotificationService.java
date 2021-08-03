package com.fdm.qualifier.service;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Notification;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.repository.NotificationRepo;

@Service
public class NotificationService {
	private Log log = LogFactory.getLog(NotificationService.class);
	
	private NotificationRepo notificationRepo;

	@Autowired
	public NotificationService(NotificationRepo notificationRepo) {
		super();
		this.notificationRepo = notificationRepo;
	}

	public Notification savePlacementNotification(Placement placement) {
		log.trace("savePlacementNotification() called");
		LocalDate notificationTime = LocalDate.now();
		String notificationText = String.format("New placement %s with %s requires skills you have.", placement.getName(), placement.getClient().getName());
		Notification notification = new Notification(notificationText, notificationTime);
		notification = notificationRepo.save(notification);
		log.debug("Notification for placement created: " + notification);
		return notification;
	}
	
}

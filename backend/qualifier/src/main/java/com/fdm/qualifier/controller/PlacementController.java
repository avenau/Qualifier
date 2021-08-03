package com.fdm.qualifier.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Notification;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.service.ClientService;
import com.fdm.qualifier.service.NotificationService;
import com.fdm.qualifier.service.PlacementService;
import com.fdm.qualifier.service.UserNotificationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlacementController {
	
	private Log log = LogFactory.getLog(PlacementController.class);
	private PlacementService placementService;
	private ClientService clientService;
	
	private NotificationService notificationService;
	private UserNotificationService userNotificationService;
	
	@Autowired
	public PlacementController(PlacementService placementService, ClientService clientService, NotificationService notificationService, UserNotificationService userNotificationService) {
		super();
		this.placementService = placementService;
		this.clientService = clientService;
		this.notificationService = notificationService;
		this.userNotificationService = userNotificationService;
	}
	
	@PostMapping("/savePlacement")
	public void save(@RequestBody Placement placement) {
		log.trace("save() called");
		Client client = new Client("ANZ");
		clientService.save(client);
		placement.setClient(client);
		log.info("Saving placement: " + placement);
		placementService.save(placement);
		
		//Create notificaitons for users
		Notification notification = notificationService.savePlacementNotification(placement);
		userNotificationService.createNotificationsForTraineesWithSkills(placement, notification);
	}
	

}

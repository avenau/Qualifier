package com.fdm.qualifier.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Notification;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.repository.TraineeRepository;
import com.fdm.qualifier.repository.UserNotificationRepository;

/**
 * UserNotification Service
 * @author William
 *
 */
@Service
public class UserNotificationService {
	private Log log = LogFactory.getLog(UserNotificationService.class);

	private UserNotificationRepository userNotificationRepo;
	private TraineeRepository traineeRepo;

	@Autowired
	public UserNotificationService(UserNotificationRepository userNotificationRepo, TraineeRepository traineeRepo) {
		super();
		this.userNotificationRepo = userNotificationRepo;
		this.traineeRepo = traineeRepo;
	}

	public void createNotificationsForTraineesWithSkills(Placement placement, Notification notification) {
	}

}

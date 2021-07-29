package com.fdm.qualifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.qualifier.model.UserNotification;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Integer>{

}

package com.fdm.qualifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Integer>{

}

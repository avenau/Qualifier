package com.fdm.qualifier.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Notification {
	@Id
	@GeneratedValue
	private int notificarionId;
	private String notification;
	private LocalDate date;
	
	@OneToMany(mappedBy="notification")
	private List<UserNotification> userNotifications;

	public Notification() {
		super();
	}

	public Notification(String notification, LocalDate date) {
		super();
		this.notification = notification;
		this.date = date;
	}

	public int getNotificarionId() {
		return notificarionId;
	}

	public void setNotificarionId(int notificarionId) {
		this.notificarionId = notificarionId;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<UserNotification> getUserNotifications() {
		return userNotifications;
	}

	public void setUserNotifications(List<UserNotification> userNotifications) {
		this.userNotifications = userNotifications;
	}
	
	
}

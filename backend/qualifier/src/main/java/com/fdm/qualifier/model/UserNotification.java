package com.fdm.qualifier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserNotification {

	@Id
	@GeneratedValue
	private int userNotificationId;

	@ManyToOne
	@JoinColumn(name = "FK_NOTIFICATION_ID")
	private Notification notification;

	@ManyToOne
	@JoinColumn(name = "FK_USER_ID")
	private User user;

	private boolean seen;

	public UserNotification() {
		super();
	}

	public UserNotification(Notification notification, User user) {
		super();
		this.notification = notification;
		this.user = user;
		this.seen = false;
	}

	public UserNotification(Notification notification, User user, boolean seen) {
		super();
		this.notification = notification;
		this.user = user;
		this.seen = seen;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public int getUserNotificationId() {
		return userNotificationId;
	}

	@Override
	public String toString() {
		return "UserNotification [userNotificationId=" + userNotificationId + ", user=" + user + ", seen=" + seen + "]";
	}

}

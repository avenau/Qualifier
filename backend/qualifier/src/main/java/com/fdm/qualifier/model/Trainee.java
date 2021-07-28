package com.fdm.qualifier.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "trainee")
public class Trainee extends User{
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "FK_STREAM")
    private Stream stream;

    public Trainee() {
        super();
    }

    public Trainee(String username, String password, LocalDate date, Stream stream) {
        super(username, password);
        this.date = date;
        this.stream = stream;
    }

    public Trainee(String username, String password, boolean isActive, LocalDate date, Stream stream) {
        super(username, password, isActive);
        this.date = date;
        this.stream = stream;
    }

    public Trainee(String username, String password, boolean isActive, String userType, LocalDate date, Stream stream) {
        super(username, password, isActive, userType);
        this.date = date;
        this.stream = stream;
    }

    public Trainee(String username, String password, String email, String address, int phoneNumber, String city,
            String firstName, String lastName, LocalDate dob, List<UserNotification> userNotification, boolean isActive,
            String userType, LocalDate date, Stream stream) {
        super(username, password, email, address, phoneNumber, city, firstName, lastName, dob, userNotification,
                isActive, userType);
        this.date = date;
        this.stream = stream;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    @Override
    public String toString() {
        return "Trainee [" + (date != null ? "date=" + date + ", " : "")
                + (stream != null ? "stream=" + stream + ", " : "") + "userId=" + userId + "]";
    }
}

package com.fdm.qualifier.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Client {
    @Id
    @GeneratedValue
    private int clientId;
    private String name;

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private List<Placement> placements;

    public Client() {
        super();
    }

    public Client(String name) {
        this.name = name;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Placement> getPlacements() {
        return placements;
    }

    public void setPlacements(List<Placement> placements) {
        this.placements = placements;
    }
    
    @Override
    public String toString() {
        return "Client [" + "clientId=" + clientId + ""
                + (name != null ? "name=" + name + ", " : "") 
                + "]";
    }
}

package com.fdm.qualifier.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private String clientId;
    private String name;

    @OneToMany(mappedBy = "client")
    private List<Placement> placements;

    public Client() {
        super();
    }

    public Client(String name, List<Placement> placements) {
        this.name = name;
        this.placements = placements;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
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
        return "Client [" + (clientId != null ? "clientId=" + clientId + ", " : "")
                + (name != null ? "name=" + name + ", " : "") + (placements != null ? "placements=" + placements : "")
                + "]";
    }


}

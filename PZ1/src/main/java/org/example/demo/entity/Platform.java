package org.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "platform")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int platformId;
    private String name;

    public Platform() {}

    public Platform(int platformId, String name) {
        this.platformId = platformId;
        this.name = name;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("{ id: '%s', name: '%s' }", platformId, name);
    }
}

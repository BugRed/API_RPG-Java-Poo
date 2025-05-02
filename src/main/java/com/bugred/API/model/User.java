package com.bugred.API.model;

import java.util.UUID;

public abstract class User {

    // Mudando id para
    private UUID id;
    private String username;
    private String email;

    public User(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User() {

    }

    public User(UUID id) {
        this.id = id;
    }

    // Getters e setters
    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }

    public void setId(UUID id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
}

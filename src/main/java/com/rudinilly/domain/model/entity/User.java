package com.rudinilly.domain.model.entity;

import java.util.UUID;

public class User {
    private UUID id;
    private UUID sellerId;
    private String email;
    private String password;

    public User(UUID sellerId, String email, String password) {
        this.id = UUID.randomUUID();
        this.sellerId = sellerId;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

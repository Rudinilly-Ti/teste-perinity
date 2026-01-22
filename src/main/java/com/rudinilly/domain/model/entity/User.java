package com.rudinilly.domain.model.entity;

import java.util.UUID;

public class User {
    private UUID id;
    private UUID sellerId;
    private String email;
    private String password;

    public User(UUID id, UUID sellerId, String email, String password) {
        validateId(id);
        validateSellerId(sellerId);
        validateEmail(email);
        validatePassword(password);

        this.id = id;
        this.sellerId = sellerId;
        this.email = email;
        this.password = password;
    }

    private void validateId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    private void validateSellerId(UUID sellerId) {
        if (sellerId == null) {
            throw new IllegalArgumentException("Seller id cannot be null");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
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

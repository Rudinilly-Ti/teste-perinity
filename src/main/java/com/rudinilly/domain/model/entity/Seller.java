package com.rudinilly.domain.model.entity;

import java.util.UUID;

public class Seller {
    private UUID id;
    private String name;

    public Seller(String name) {
        validateName(name);

        this.id = UUID.randomUUID();
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

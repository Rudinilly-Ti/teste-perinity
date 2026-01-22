package com.rudinilly.domain.model.entity;

import java.util.UUID;

public class Seller {
    private UUID id;
    private String name;

    public Seller(UUID id, String name) {
        validateId(id);
        validateName(name);

        this.id = id;
        this.name = name;
    }

    private void validateId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

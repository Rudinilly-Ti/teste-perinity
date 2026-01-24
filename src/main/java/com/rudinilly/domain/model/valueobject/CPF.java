package com.rudinilly.domain.model.valueobject;

public record CPF(String value) {
    public CPF {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("CPF cannot be empty");
        }

        if (!value.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF must have 11 digits");
        }
    }
}

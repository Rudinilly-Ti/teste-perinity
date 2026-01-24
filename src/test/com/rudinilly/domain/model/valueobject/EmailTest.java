package com.rudinilly.domain.model.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    @Test
    void shouldCreateValidEmail() {
        Email email = new Email("User@Email.COM ");
        assertEquals("user@email.com", email.value());
    }

    @Test
    void shouldThrowWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Email(null));
    }

    @Test
    void shouldThrowWhenEmailIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Email("invalid-email"));
    }
}
package com.rudinilly.domain.model.valueobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DimensionTest {

    @Test
    void shouldCreateValidDimension() {
        Dimension dimension = new Dimension(10.5, 2.0, 3.5);

        assertNotNull(dimension);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(doubles = { 0.0, -1.0 })
    void shouldThrowExceptionWhenWidthIsInvalid(Double value) {
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(value, 5.0, 3.0)
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(doubles = { 0.0, -1.0 })
    void shouldThrowExceptionWhenHeightIsInvalid(Double value) {
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(4.0, value, 3.0)
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(doubles = { 0.0, -1.0 })
    void shouldThrowExceptionWhenDepthIsInvalid(Double value) {
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(3.0, 5.0, value)
        );
    }
}
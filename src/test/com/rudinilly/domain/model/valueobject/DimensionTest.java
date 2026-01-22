package com.rudinilly.domain.model.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DimensionTest {

    @Test
    void shouldCreateValidDimension() {
        Dimension dimension = new Dimension(10.5, 2.0, 3.5);

        assertEquals(10.5, dimension.width());
        assertEquals(2.0, dimension.height());
        assertEquals(3.5, dimension.depth());
    }

    @Test
    void shouldThrowExceptionWhenWidthIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(0.0, 5.0, 3.0)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(-1.0, 5.0, 3.0)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(null, 5.0, 3.0)
        );
    }

    @Test
    void shouldThrowExceptionWhenHeightIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(4.0, 0.0, 3.0)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(5.0, null, 3.0)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(1.0, -5.0, 3.0)
        );
    }

    @Test
    void shouldThrowExceptionWhenDepthIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(3.0, 5.0, 0.0)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(1.0, 5.0, -3.0)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Dimension(5.0, 3.0, null)
        );
    }
}
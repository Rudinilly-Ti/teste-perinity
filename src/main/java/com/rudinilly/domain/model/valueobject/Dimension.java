package com.rudinilly.domain.model.valueobject;

public record Dimension(Double width, Double height, Double depth) {
    public Dimension {
        validate(width, height, depth);
    }

    private void validate(Double width, Double height, Double depth) {
        if (width == null || width <= 0)
            throw new IllegalArgumentException("Width must be positive");

        if (height == null || height <= 0)
            throw new IllegalArgumentException("Height must be positive");

        if (depth == null || depth <= 0)
            throw new IllegalArgumentException("Depth must be positive");
    }
}

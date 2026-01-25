package com.rudinilly.api.command.seller;

import jakarta.validation.constraints.NotBlank;

public record SellerCommand(
        @NotBlank
        String name
) {
}

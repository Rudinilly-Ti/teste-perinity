package com.rudinilly.api.dto;

import java.time.LocalDate;
import java.util.UUID;

public record NewClientReport (
        UUID id,
        String name,
        LocalDate birthDate,
        LocalDate createdAt
) { }

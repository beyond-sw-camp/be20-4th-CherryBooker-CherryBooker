package com.cherry.cherrybookerbe.mylib.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterBookRequest(
        @NotNull Long userId,
        @NotBlank String keyword,
        String isbnHint
) {
}

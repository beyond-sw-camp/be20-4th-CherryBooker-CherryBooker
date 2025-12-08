package com.cherry.cherrybookerbe.mylib.command.application.dto;

import com.cherry.cherrybookerbe.mylib.command.domain.entity.BookStatus;
import jakarta.validation.constraints.NotNull;

public record BookStatusChangeRequest(
        @NotNull BookStatus targetStatus,
        StatusTrigger trigger
) {

    public StatusTrigger triggerOrDefault() {
        return trigger == null ? StatusTrigger.MANUAL : trigger;
    }
}

package com.ruslangainutdinov.message.service.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateMessageCmd(
    @NotNull UUID userId,
    @NotNull UUID chatId,
    @NotNull String payload
) {
}

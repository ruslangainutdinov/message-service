package com.ruslangainutdinov.message.service.dto;

import jakarta.validation.constraints.Min;
import java.util.UUID;

public record FetchMessagesCmd(
    UUID chatId,
    UUID userId,
    @Min(0) int page,
    @Min(1) int size
) {
}

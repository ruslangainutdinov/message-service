package com.ruslangainutdinov.message.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record EditMessageCmd(
    @NotNull UUID messageId,
    int version,
    @NotEmpty String payload
) {

}

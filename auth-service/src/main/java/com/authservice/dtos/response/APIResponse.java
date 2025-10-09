package com.authservice.dtos.response;

import lombok.Builder;
import java.util.List;

@Builder
public record APIResponse<T>(
        String responseCode,
        String responseMessage,
        long execTime,
        List<ErrorDTO> errors,
        T results
) {}
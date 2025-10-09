package com.authservice.dtos.response;

import lombok.Builder;

@Builder
public record ErrorDTO(
        String code,
        String message,
        String field
) {}
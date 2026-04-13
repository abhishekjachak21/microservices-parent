package com.project.middleware.adapter.in.web.response;


import org.slf4j.MDC;

import java.time.Instant;
import java.util.UUID;

public record ApiResponse<T>(
        T data,
        ResponseMetadata metadata
) {
    public static <T> ApiResponse<T> of(T data) {

        String corrId = MDC.get("correlationId");

        return new ApiResponse<>(
                data,
                new ResponseMetadata(
                        corrId != null ? corrId : UUID.randomUUID().toString(),
                        Instant.now()
                )        );
    }
}
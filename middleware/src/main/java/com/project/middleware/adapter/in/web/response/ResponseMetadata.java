package com.project.middleware.adapter.in.web.response;


import java.time.Instant;

public record ResponseMetadata(
        String correlationId,
        Instant timestamp
) {}
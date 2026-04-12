package com.project.middleware.adapter.in.web.exception;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class MiddlewareExceptionHandler {

    // 🔴 Downstream (system-api errors)
    @ExceptionHandler(FeignException.class)
    public ProblemDetail handleFeign(FeignException ex) {

        int status = switch (ex.status()) {
            case 400 -> 400;
            case 404 -> 404;
            default -> 502; // Bad Gateway
        };

        ProblemDetail pd = ProblemDetail.forStatus(status);
        pd.setTitle("Downstream Service Error");
        pd.setDetail(ex.getMessage());

        return pd;
    }

    // 🔴 Circuit breaker OPEN
    @ExceptionHandler(CallNotPermittedException.class)
    public ProblemDetail handleCircuitOpen(CallNotPermittedException ex) {

        ProblemDetail pd = ProblemDetail.forStatus(503);
        pd.setTitle("Service Temporarily Unavailable");
        pd.setDetail("Circuit breaker is OPEN");

        return pd;
    }

    // 🔴 Generic fallback
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex) {

        ProblemDetail pd = ProblemDetail.forStatus(500);
        pd.setTitle("Internal Server Error");
        pd.setDetail(ex.getMessage());

        return pd;
    }
}
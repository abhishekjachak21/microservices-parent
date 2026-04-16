package com.project.middleware.application.metrics;

import io.micrometer.core.instrument.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMetrics {

    private final MeterRegistry registry;

    private Counter ordersCreated;
    private Counter ordersFailed;
    private Timer orderProcessing;

    @PostConstruct
    void init() {

        ordersCreated = Counter.builder("orders.created.total")
                .description("Total orders created")
                .tag("service", "middleware")
                .register(registry);

        ordersFailed = Counter.builder("orders.failed.total")
                .description("Total orders failed")
                .tag("service", "middleware")
                .register(registry);

        orderProcessing = Timer.builder("order.processing.duration")
                .description("Order processing time")
                .publishPercentiles(0.5, 0.95, 0.99)
                .register(registry);
    }

    public void recordSuccess() {
        ordersCreated.increment();
    }

    public void recordFailure() {
        ordersFailed.increment();
    }

    public Timer.Sample startTimer() {
        return Timer.start(registry);
    }

    public void stopTimer(Timer.Sample sample) {
        sample.stop(orderProcessing);
    }
}
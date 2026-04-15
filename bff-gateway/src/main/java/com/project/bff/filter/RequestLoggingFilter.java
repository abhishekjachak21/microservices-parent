package com.project.bff.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RequestLoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        long start = System.currentTimeMillis();

        String path = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethod().name();

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {

                    long time = System.currentTimeMillis() - start;

                    int status = exchange.getResponse().getStatusCode().value();

                    System.out.println(
                            "Request -> " + method +
                                    " " + path +
                                    " | Status: " + status +
                                    " | Time: " + time + "ms"
                    );
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
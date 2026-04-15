package com.project.bff.config;


import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class KeyResolverConfig {

    @Bean
    public KeyResolver userKeyResolver() {

        return exchange -> {
            String ip = exchange.getRequest()
                    .getHeaders()
                    .getFirst("X-Forwarded-For");

            if (ip == null) {
                ip = exchange.getRequest()
                        .getRemoteAddress()
                        .getAddress()
                        .getHostAddress();
            }

            return Mono.just(ip);
        };
    }
}
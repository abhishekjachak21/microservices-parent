package com.project.bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                .headers(headers -> headers
                        .frameOptions(frame -> frame.mode(
                                org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode.DENY
                        ))
                        .contentTypeOptions(content -> {})
                )
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/actuator/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/api/**").hasRole("USER")
                        .pathMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")

                        .anyExchange().authenticated()
                )

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtDecoder(token -> Mono.just(
                                        Jwt.withTokenValue(token)
                                                .header("alg", "none")
                                                .claim("roles", List.of("USER"))
                                                .build()
                                ))
                                .jwtAuthenticationConverter(this::convertJwt)
                        )
                )
                .build();
    }

    /**
     * Convert JWT roles -> Spring Security roles
     */
    private Mono<JwtAuthenticationToken> convertJwt(Jwt jwt) {

        List<String> roles = jwt.getClaimAsStringList("roles");

        if (roles == null) {
            roles = List.of("USER"); // fallback for testing
        }

        Collection<SimpleGrantedAuthority> authorities =
                roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());

        return Mono.just(new JwtAuthenticationToken(jwt, authorities));
    }
}
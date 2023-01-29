package com.zshopping.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class CustomSecurityConfig {

    @Bean
    public SecurityWebFilterChain customSecurityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        return serverHttpSecurity
                .csrf().disable()
                .authorizeExchange(authorizeExchangeSpec -> {
                    authorizeExchangeSpec
                            .pathMatchers("/eureka/**").permitAll()
                            .anyExchange().authenticated();
                }).oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt).build();
    }

}

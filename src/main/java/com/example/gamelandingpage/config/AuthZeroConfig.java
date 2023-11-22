package com.example.gamelandingpage.config;

import com.auth0.AuthenticationController;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "auth-controller.type", havingValue = "prod")
public class AuthZeroConfig {

    @Value("${module.auth.connection.domain}")
    private String domain;
    @Value("${module.auth.client_id}")
    private String clientId;
    @Value("${module.auth.client_secret}")
    private String clientSecret;

    @Bean
    public AuthenticationController authenticationController() {
        final JwkProvider jwkProvider = new JwkProviderBuilder(domain).build();
        return AuthenticationController.newBuilder(domain, clientId, clientSecret)
                .withJwkProvider(jwkProvider)
                .build();
    }
}

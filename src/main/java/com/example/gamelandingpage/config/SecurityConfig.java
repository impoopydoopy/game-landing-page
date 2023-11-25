package com.example.gamelandingpage.config;

import com.auth0.AuthenticationController;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${module.auth.connection.domain}")
    private String domain;
    @Value("${module.auth.client_id}")
    private String clientId;
    @Value("${module.auth.client_secret}")
    private String clientSecret;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                cors()
                .and()
                .csrf().disable();
//                .authorizeRequests()
//                .antMatchers("/callback", "/login", "/proceed/logout", "/user", "/callback-logout", "/", "/**/img/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated();
    }

    @Bean
    public AuthenticationController authenticationController() {
        final JwkProvider jwkProvider = new JwkProviderBuilder(domain).build();
        return AuthenticationController.newBuilder(domain, clientId, clientSecret)
                .withJwkProvider(jwkProvider)
                .build();
    }
}
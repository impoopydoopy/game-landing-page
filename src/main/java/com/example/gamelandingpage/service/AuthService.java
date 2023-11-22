package com.example.gamelandingpage.service;

import com.auth0.AuthenticationController;
import com.auth0.Tokens;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.gamelandingpage.repository.model.PlatformUser;
import com.example.gamelandingpage.service.utils.UrlBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationController authenticationController;
    private final UrlBuilder urlBuilder;
    private final UserManagementService userManagementService;

    private final static String AUTH0_SCOPE = "openid profile email";

    @Value("${module.auth.audience}")
    private String auth0Audience;
    @Value("${module.auth.connection.domain}")
    private String auth0Domain;
    @Value("${module.auth.client_id}")
    private String auth0ClientId;
    @Value("${module.auth.logout_template}")
    private String logoutUrlTemplate;

    public String buildLoginUrl(HttpServletRequest request, HttpServletResponse response) {
        return authenticationController.buildAuthorizeUrl(request, response, urlBuilder.getCallbackLoginUri())
                .withScope(AUTH0_SCOPE)
                .withAudience(auth0Audience)
                .build();
    }

    public String buildRedirectUrl(HttpServletRequest request, HttpServletResponse response) {
        String token;
        try {
            final Tokens tokens = authenticationController.handle(request, response);

            final DecodedJWT decodedJwt = JWT.decode(tokens.getIdToken());
            final TestingAuthenticationToken authToken = new TestingAuthenticationToken(decodedJwt.getSubject(), decodedJwt.getToken());
            authToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authToken);

            final String email = decodedJwt.getClaim("email").asString();
            if (!userManagementService.userPresentByEmail(email)) {
                userManagementService.createUser(PlatformUser.builder()
                        .firstName(decodedJwt.getClaim("given_name").asString())
                        .lastName(decodedJwt.getClaim("family_name").asString())
                        .email(email)
                        .build());
            }
            return urlBuilder.getRedirectToSuccessPageUri();
        } catch (Exception e) {
            log.error("User login failed: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public String buildLogoutUrl() {
        return String.format(logoutUrlTemplate, auth0Domain, auth0ClientId, urlBuilder.getCallbackLogoutUri());
    }
}

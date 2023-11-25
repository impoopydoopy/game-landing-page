package com.example.gamelandingpage.api.controller;

import com.example.gamelandingpage.service.AuthService;
import com.example.gamelandingpage.service.utils.UrlBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.gamelandingpage.service.utils.UrlBuilder.*;


@Log4j2
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UrlBuilder urlBuilder;

    @GetMapping
    public void homePage(HttpServletResponse response) throws IOException {
        response.sendRedirect(urlBuilder.getRedirectToMainPageUri());
    }

    @GetMapping(value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(authService.buildLoginUrl(request, response));
    }

    @GetMapping(value = "/proceed/logout")
    public void logout(HttpServletResponse response) throws IOException {
        response.sendRedirect(authService.buildLogoutUrl());
    }

    @GetMapping(value = CALLBACK_URI_PREFIX)
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(authService.buildRedirectUrl(request, response));
    }

    @GetMapping(value = CALLBACK_URI_LOGOUT_PREFIX)
    public void callbackLogout(HttpServletResponse response) throws IOException {
        SecurityContextHolder.clearContext();
        response.sendRedirect(urlBuilder.getRedirectToMainPageUri());
    }

    @GetMapping(value = SUCCESS_URI_PREFIX)
    public ModelAndView success() {
        return new ModelAndView("success.html");
    }

    @GetMapping("/profile")
    public void getAuthorization(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String platformId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(StringUtils.equals(platformId, "anonymousUser")) {
            response.sendRedirect(authService.buildLoginUrl(request, response));
        } else {
            response.sendRedirect("http://localhost:8080/profile/view");
        }
    }
}


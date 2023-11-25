package com.example.gamelandingpage.service.utils;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class UrlBuilder {

    public static final String CALLBACK_URI_PREFIX = "/callback";
    public static final String SUCCESS_URI_PREFIX = "/success";
    public static final String CALLBACK_URI_LOGOUT_PREFIX = "/callback-logout";
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/proceed/logout";
    private static final String ROOT_DOMAIN = "http://localhost:8080";
    private static final String MAIN_PAGE_PREFIX = "/";


    private final String callbackLoginUri;
    private final String callbackLogoutUri;
    private final String redirectToMainPageUri;
    private final String redirectToSuccessPageUri;

    public UrlBuilder() {
        this.callbackLoginUri = ROOT_DOMAIN + CALLBACK_URI_PREFIX;
        this.callbackLogoutUri = ROOT_DOMAIN + CALLBACK_URI_LOGOUT_PREFIX;
        this.redirectToMainPageUri = ROOT_DOMAIN + MAIN_PAGE_PREFIX;
        this.redirectToSuccessPageUri = ROOT_DOMAIN + SUCCESS_URI_PREFIX;
    }
}
/*
 * Copyright (c) 2019, LZx
 */

package space.nature.spring.security;

import org.springframework.http.HttpHeaders;

/**
 * JWT Token处理相关的常量
 */
public interface JwtTokenConstants {

    String HEADER_TOKEN_NAME = HttpHeaders.AUTHORIZATION;

    String COOKIE_TOKEN_NAME = "Jwt-Token";

}

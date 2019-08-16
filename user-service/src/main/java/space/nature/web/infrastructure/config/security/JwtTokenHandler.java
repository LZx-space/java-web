/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.infrastructure.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import space.nature.util.JwtUtils;
import space.nature.web.domain.user.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

/**
 * JWT Token
 */
@Component
public class JwtTokenHandler {

    @Value("${jwt.token.timeout:30}")
    private int tokenTimeout;

    /**
     * 创建Token
     *
     * @param authentication 认证信息对象
     * @return
     */
    public String create(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date now = new Date();
        Date exp = Date.from(now.toInstant().plus(tokenTimeout, MINUTES));
        List<String> roles = user.getAuthorities().parallelStream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setAudience(user.getUsername())
                .setIssuer("web-user")
                .setIssuedAt(now)
                .setExpiration(exp)
                .setId(UUID.randomUUID().toString())
                .claim("role", roles)
                .compact();
    }

    /**
     * 解析Token
     *
     * @param token JWT token
     * @return
     */
    public Authentication parse(String token) {
        Claims claims = JwtUtils.parse(token);
        List<SimpleGrantedAuthority> roles = ((List<String>) claims.get("role")).stream().map(e -> new SimpleGrantedAuthority(e)).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims.getAudience(), null, roles);
    }

}

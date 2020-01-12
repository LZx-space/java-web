/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.security.spring;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import space.nature.common.core.util.JwtUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.MINUTES;

/**
 * JWT Token
 */
@Getter
public class JwtTokenHandler {

    private final int tokenTimeout;

    public JwtTokenHandler(int tokenTimeout) {
        this.tokenTimeout = tokenTimeout;
    }

    /**
     * 创建Token
     *
     * @param authentication 认证信息对象
     * @return
     */
    public String create(@Validated Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date exp = Date.from(now.toInstant().plus(tokenTimeout, MINUTES));
        String roles = userDetails.getAuthorities().parallelStream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .setAudience(userDetails.getUsername())
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
        List<SimpleGrantedAuthority> roles = Stream.of(((String) claims.get("role")).split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims.getAudience(), null, roles);
    }

}

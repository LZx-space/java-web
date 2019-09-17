/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.SECONDS;

public abstract class JwtUtils {

    public static String create() {
        Date now = new Date();
        Date exp = Date.from(now.toInstant().plus(2000, SECONDS));
        return Jwts.builder()
                .setAudience("LZx")
                .setIssuer("web")
                .setIssuedAt(now)
                .setExpiration(exp)
                .setId(UUID.randomUUID().toString())
                .compact();
    }

    public static Claims parse(String jwtStr) {
        Jwt jwt = Jwts.parser().parse(jwtStr);
        Header header = jwt.getHeader();
        return (Claims) jwt.getBody();
    }

    public static void main(String[] args) {
        String jwt = create();
        System.out.println(jwt);
        parse(jwt);
    }

}

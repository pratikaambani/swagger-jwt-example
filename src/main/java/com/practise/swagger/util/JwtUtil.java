package com.practise.swagger.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Pratik Ambani on 3/5/2017.
 */
public class JwtUtil {

    public static String generateToken(String subject, String signingKey) {
        long currentTime = System.currentTimeMillis();
        Date now = new Date(currentTime);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        return builder.compact();
    }

    public String getSubject(HttpServletRequest request, String tokenCookieName, String signingKey) {
        String token = CookieUtil.getValue(request, tokenCookieName);
        if (token == null)
            return null;
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

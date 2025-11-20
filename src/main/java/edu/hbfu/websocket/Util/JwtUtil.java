package edu.hbfu.websocket.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    //配置JWT相关内容
    private static final Key KEY = Keys.hmacShaKeyFor("secret123123asjdhuiesdhk".getBytes());
    private static final long EXPIRATION_TIME = 24 * 60 * 60  * 1000;

    public static  String generateToken(String subject, Map<String,Object> claims) {
      long now = System.currentTimeMillis();
      return Jwts.builder()
              .setSubject(subject)
              .setClaims(claims)
              .setIssuedAt(new Date(now))
              .setExpiration(new Date(now+EXPIRATION_TIME))
              .signWith(KEY, SignatureAlgorithm.HS256)
              .compact();
    }
    public static Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
    }
}

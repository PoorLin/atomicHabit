package org.atomicHabit.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.atomicHabit.model.User;

import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Jwt{

    // 生成 JWT
    public static String generateToken(String subject, User user, long ttlMillis, String secretKey) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        Date now = new Date();
        Date exp = new Date(now.getTime() + ttlMillis);

        return Jwts.builder()
                .setSubject(subject)
                .claim("user",user)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }

    // 驗證 JWT
    public static Claims parseToken(String token, String secretKey) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String parseSHA256(String target){
        String shaStr = null ;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(target.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            shaStr=hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
return  shaStr;

    }

    public static void main(String[] args) {

       String secretKey = parseSHA256("your-secret-key");
        System.out.println(secretKey);
        String subject = "user123";
        long ttlMillis = 3600000; // 1 hour
        // 生成 JWT
//        String token = generateToken(subject, ttlMillis, secretKey);
//        System.out.println("Generated JWT: " + token);
//
//        // 驗證 JWT
//        Claims claims = parseToken(token, secretKey);
//        System.out.println("Subject: " + claims.getSubject());
//        System.out.println("Expiration: " + claims.getExpiration());
    }
}
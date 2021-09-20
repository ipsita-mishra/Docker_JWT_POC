package com.learning.zoro.concepts.Utility;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenUtility implements Serializable {
    private final long serialID = 1L;
    private final long JWT_TOKEN_EXPIRY = 5 * 60 * 60;
    @Value("${jwt.secret}")
    private String secretKey;

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getUserNameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails findUserRequest){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims,findUserRequest.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return  Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_EXPIRY * 1000)).signWith(SignatureAlgorithm.HS512,secretKey).compact();
    }

    //Validate Token
    public Boolean validateToken(String token , UserDetails findUserRequest){
        final String userName = getUserNameFromToken(token);
        return (userName.equals(findUserRequest.getUsername()) && !isTokenExpired(token));
    }

}

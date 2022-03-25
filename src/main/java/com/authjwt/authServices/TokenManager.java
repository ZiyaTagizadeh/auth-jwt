package com.authjwt.authServices;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
//import io.jsonwebtoken.impl.crypto.MacProvider;


import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenManager {


    private  static final int valitity = 5*60*1000;
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
//    private static final byte[] secretBytes = secret.getEncoded();
//    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);


    public  String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer("ziko")
                .setExpiration(new Date(System.currentTimeMillis()+valitity))
                .signWith(key)
                .compact();

    }

    public  boolean validateToken(String token){
        if (getUserFromToken(token) !=null && isExpired(token)){
            return  true;
        }
        return false;
    }

    public  String  getUserFromToken(String token){
        Claims claims = getClaims(token);
        return  claims.getSubject();
    }

    public  boolean  isExpired(String token){
        Claims claims = getClaims(token);
        return  claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}

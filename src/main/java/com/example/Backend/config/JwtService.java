package com.example.Backend.config;

import com.example.Backend.token.Token;
import com.example.Backend.token.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "28472B4B6250655368566D5971337336763979244226452948404D635166546A";
    @Autowired
    private TokenRepository tokenRepository;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60*60*24);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Optional<Token> optionalToken = tokenRepository.findByEmployeeEmail(userDetails.getUsername());
                if (optionalToken.isPresent()) {
                    Token tokenObj = optionalToken.get();
                    tokenObj.setExpired(true);
                    tokenObj.setRevoked(true);
                    tokenRepository.save(tokenObj);
                }
            }
        }, expiration);
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();


    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    public  boolean isTokenValid(String token,UserDetails userDetails){
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {

        boolean isExpired = extractExpiration(token).before(new Date());
        if (isExpired) {
            updateTokenExpiredAndRevoked(token);
        }
        return isExpired;
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private void updateTokenExpiredAndRevoked(String token) {
        tokenRepository.setExpiredandRevokedtotrue(token);
    }
}

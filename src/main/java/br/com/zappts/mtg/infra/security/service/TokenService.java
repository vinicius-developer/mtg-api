package br.com.zappts.mtg.infra.security.service;

import br.com.zappts.mtg.domain.user.entities.UserEntity;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import java.util.Date;

@Service
public class TokenService {

    @Value("${mtg.jwt.expiration}")
    private String expiration;

    @Value("${mtg.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        Date today = new Date();

        Date expiration = new Date(today.getTime() + Long.parseLong(this.expiration));

        return Jwts.builder()
                .setIssuer("MTG api")
                .setSubject(userEntity.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        return Long.parseLong(Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }
}

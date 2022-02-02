package com.issami.iam.security.service.impl;

import com.issami.iam.dto.TokenDTO;
import com.issami.iam.entity.Cliente;
import com.issami.iam.exception.JwtTokenException;
import com.issami.iam.security.service.TokenService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.lang.Long.valueOf;
import static java.lang.System.currentTimeMillis;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public TokenDTO gerarToken(Authentication authentication) {
        try {
            final Cliente cliente = (Cliente) authentication.getPrincipal();
            final Date hoje = new Date();
            final Date dataDeExpiracao = new Date();
            dataDeExpiracao.setTime(currentTimeMillis() + valueOf(jwtExpiration));

            return TokenDTO.builder().token(Jwts.builder()
                            .setIssuer("API IAM-SECURITY")
                            .setSubject(cliente.getId().toString())
                            .setIssuedAt(hoje)
                            .setExpiration(dataDeExpiracao)
                            .signWith(HS256, jwtSecret)
                            .compact()
                    )
                    .type("Bearer")
                    .build();
        } catch (Exception exception) {
            throw new JwtTokenException();
        }
    }
}

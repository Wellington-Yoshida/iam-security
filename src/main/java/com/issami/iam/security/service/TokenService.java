package com.issami.iam.security.service;

import com.issami.iam.dto.TokenDTO;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface TokenService {
    TokenDTO gerarToken(Authentication authentication);
    boolean isTokenValido(String token);
    String getIdUsuario(String token);
    Claims getClaims(String token);
}

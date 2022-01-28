package com.issami.iam.security.service;

import com.issami.iam.dto.TokenDTO;
import org.springframework.security.core.Authentication;

public interface TokenService {
    TokenDTO gerarToken(Authentication authentication);
}

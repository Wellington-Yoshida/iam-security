package com.issami.iam.controller;

import com.issami.iam.dto.LoginDTO;
import com.issami.iam.dto.TokenDTO;
import com.issami.iam.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody @Validated LoginDTO loginDTO){
        final TokenDTO tokenDTO = tokenService.gerarToken(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUser(), loginDTO.getPassword())));
        return ResponseEntity.ok(tokenDTO);
    }
}

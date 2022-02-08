package com.issami.iam.controller;

import com.issami.iam.dto.AutorizadoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Boolean.TRUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    @GetMapping
    public ResponseEntity<AutorizadoDTO> authorization() {
        return ok(AutorizadoDTO.builder().autorizado(TRUE).build());
    }
}

package com.issami.iam.controller;


import com.issami.iam.dto.ClienteDTO;
import com.issami.iam.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/authenticatedUser")
public class AuthenticatedUser {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<ClienteDTO> authenticatedUser() {
        return ok(clienteService.findByUsuario());
    }
}

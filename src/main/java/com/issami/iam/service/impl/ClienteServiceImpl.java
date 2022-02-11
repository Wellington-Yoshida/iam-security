package com.issami.iam.service.impl;

import com.issami.iam.dto.ClienteDTO;
import com.issami.iam.entity.Cliente;
import com.issami.iam.repository.ClienteRepository;
import com.issami.iam.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDTO findByUsuario() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Cliente cliente = (Cliente) authentication.getPrincipal();
        return ClienteDTO.builder().user(cliente.getEmail()).password(cliente.getPassword()).build();
    }

}

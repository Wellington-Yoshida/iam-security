package com.issami.iam.security.service;

import com.issami.iam.entity.Cliente;
import com.issami.iam.exception.ClienteNaoEncontratoException;
import com.issami.iam.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class AutenticacaoServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Cliente cliente = clienteRepository.findByEmail(email);
        if (isEmpty(cliente)) {
            throw  new ClienteNaoEncontratoException();
        }
        return cliente;
    }

}

package com.issami.iam.security.filter;

import com.issami.iam.entity.Cliente;
import com.issami.iam.exception.AutenticacaoException;
import com.issami.iam.repository.ClienteRepository;
import com.issami.iam.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static io.micrometer.core.instrument.util.StringUtils.isBlank;

@Component
public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = recuperaToken(request);
        if (tokenService.isTokenValido(token)) {
            autenticarCliente(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        final Optional<Cliente> clienteOptional = clienteRepository.findById(tokenService.getIdUsuario(token));
        if (clienteOptional.isEmpty()) {
            throw new AutenticacaoException();
        }
        SecurityContextHolder.getContext()
                             .setAuthentication(new UsernamePasswordAuthenticationToken(clienteOptional.get(), null, clienteOptional.get().getAuthorities()));
    }

    private String recuperaToken(HttpServletRequest request) {
        final String token = request.getHeader("Authorization");
        if (isBlank(token) || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }

}

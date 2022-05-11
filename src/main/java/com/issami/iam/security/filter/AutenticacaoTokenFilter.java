package com.issami.iam.security.filter;

import com.issami.iam.exception.AutenticacaoException;
import com.issami.iam.repository.ClienteRepository;
import com.issami.iam.security.service.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static io.micrometer.core.instrument.util.StringUtils.isBlank;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = recuperaToken(request);
        final Claims claims = tokenService.getClaims(token);
        if (!isEmpty(claims)) {
            autenticarCliente(claims);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(Claims claims) {
        final List<String> authorities = (List) claims.get("authorities");
        if (isEmpty(claims)) {
            throw new AutenticacaoException();
        }
        SecurityContextHolder.getContext()
                             .setAuthentication(new UsernamePasswordAuthenticationToken(claims, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(toList())));
    }

    private String recuperaToken(HttpServletRequest request) {
        final String token = request.getHeader("Authorization");
        if (isBlank(token) || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }

}

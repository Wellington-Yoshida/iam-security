package com.issami.iam.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class Perfil implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = -5334129812744142128L;

    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}

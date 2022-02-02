package com.issami.iam.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@Document(collection = "cliente")
public class Cliente implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 2926378560008088016L;

    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private boolean contaEstaBloqueada;
    private boolean contaEstaExpirada;
    private List<Perfil> perfils = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getPerfils();
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return Boolean.FALSE.equals(this.isContaEstaExpirada());
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.FALSE.equals(this.isContaEstaBloqueada());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.FALSE.equals(this.isContaEstaBloqueada());
    }
}

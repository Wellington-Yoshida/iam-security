package com.issami.iam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class AutorizadoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7865722226436479630L;

    @JsonProperty("autorizado")
    private boolean autorizado;
}

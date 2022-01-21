package com.issami.iam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class TokenDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2654212022062174948L;

    @JsonProperty("type")
    private String type;

    @JsonProperty("token")
    private String token;

}

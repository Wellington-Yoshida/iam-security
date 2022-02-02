package com.issami.iam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class LoginDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4384698399666023363L;

    @JsonProperty("user")
    private String user;

    @JsonProperty("password")
    private String password;

}

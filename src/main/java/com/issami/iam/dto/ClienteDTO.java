package com.issami.iam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class ClienteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6719156866011841089L;

    @JsonProperty("user")
    private String user;

    @JsonProperty("password")
    private String password;

}

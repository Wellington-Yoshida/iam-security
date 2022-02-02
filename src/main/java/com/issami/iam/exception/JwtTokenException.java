package com.issami.iam.exception;

public class JwtTokenException extends RuntimeException {
    public JwtTokenException() {
        super("Erro ao tentar gerar Token.");
    }
}

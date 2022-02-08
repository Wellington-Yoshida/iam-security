package com.issami.iam.exception;

public class AutenticacaoException extends RuntimeException {

    public AutenticacaoException() {
        super("Erro ao tentar se autenticar.");
    }

}

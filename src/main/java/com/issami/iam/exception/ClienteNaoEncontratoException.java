package com.issami.iam.exception;

public class ClienteNaoEncontratoException extends RuntimeException {

    public ClienteNaoEncontratoException() {
        super("Não foi localizado uma conta com email informado.");
    }

}

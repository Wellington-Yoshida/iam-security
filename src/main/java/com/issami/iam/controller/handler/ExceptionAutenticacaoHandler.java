package com.issami.iam.controller.handler;

import com.issami.iam.dto.Error;
import com.issami.iam.exception.ClienteNaoEncontratoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionAutenticacaoHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ClienteNaoEncontratoException.class})
    public ResponseEntity<Error> clienteNaoEncontradoHandler(ClienteNaoEncontratoException clienteNaoEncontratoException) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(Error.builder()
                           .codigo(NOT_FOUND.getReasonPhrase())
                           .mensagem(clienteNaoEncontratoException.getMessage())
                    .build());
    }

}

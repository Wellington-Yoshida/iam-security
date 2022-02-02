package com.issami.iam.controller.handler;

import com.issami.iam.dto.Error;
import com.issami.iam.exception.ClienteNaoEncontratoException;
import com.issami.iam.exception.JwtTokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
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

    @ExceptionHandler(value = {JwtTokenException.class})
    public ResponseEntity<Error> clienteNaoEncontradoHandler(JwtTokenException jwtTokenException) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(Error.builder()
                        .codigo(BAD_REQUEST.getReasonPhrase())
                        .mensagem(jwtTokenException.getMessage())
                        .build());
    }
}

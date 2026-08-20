package com.techdesk.techdesk.chamados.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.techdesk.techdesk.chamados.dto.ErroDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<ErroDTO> tratar(CategoriaNaoEncontradaException ex) {
        return ResponseEntity.status(404).body(new ErroDTO(ex.getMessage()));
    }
}
package com.dnss.api_ti9.controller;

import com.dnss.api_ti9.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidoCPF.class)
    public ResponseEntity<String> handleCpfInvalidoException(InvalidoCPF ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidoNome.class)
    public ResponseEntity<String> handleNomeInvalidoException(InvalidoNome ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidoSalario.class)
    public ResponseEntity<String> handleSalarioInvalidoException(InvalidoSalario ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidoDataAdmissao.class)
    public ResponseEntity<String> handleDataAdmissaonvalidoException(InvalidoDataAdmissao ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(FuncionarioNaoExiste.class)
    public ResponseEntity<String> handleFuncionarioNaoExisteException(FuncionarioNaoExiste ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(DependenteNaoExiste.class)
    public ResponseEntity<String> handleDependenteNaoExisteException(DependenteNaoExiste ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(InvalidoDataNascimento.class)
    public ResponseEntity<String> handleDependenteNaoExisteException(InvalidoDataNascimento ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(InvalidoParentesco.class)
    public ResponseEntity<String> handleDependenteNaoExisteException(InvalidoParentesco ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(CampoObrigatorio.class)
    public ResponseEntity<String> handleDependenteNaoExisteException(CampoObrigatorio ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}

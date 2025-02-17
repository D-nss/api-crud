package com.dnss.api_ti9.exception;

public class CampoObrigatorio extends RuntimeException {
    public CampoObrigatorio(String message) {
        super(message);
    }
}

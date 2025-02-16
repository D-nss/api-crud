package com.dnss.api_ti9.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.Date;

public record DependenteDTO( String nome,
                             LocalDate data_de_nascimento,
                             String parentesco) {
}

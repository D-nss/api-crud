package com.dnss.api_ti9.dto;

import com.dnss.api_ti9.model.Dependente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


public record FuncionarioDTO(String nome,
                             String cpf,
                             String cargo,
                             BigDecimal salario,
                             LocalDate data_de_admissao,
                             Dependente dependentes) {
}

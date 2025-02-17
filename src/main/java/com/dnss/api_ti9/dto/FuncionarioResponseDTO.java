package com.dnss.api_ti9.dto;

import com.dnss.api_ti9.model.Dependente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FuncionarioResponseDTO(String nome,
                                     String cpf,
                                     String cargo,
                                     BigDecimal salario,
                                     LocalDate data_de_admissao,
                                     List<Dependente> dependentes) {
}

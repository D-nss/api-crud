package com.dnss.api_ti9.dto;

import com.dnss.api_ti9.model.Dependente;
import com.dnss.api_ti9.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public record FuncionarioDTO(String nome,
                             String cpf,
                             String cargo,
                             BigDecimal salario,
                             String data_de_admissao,
                             List<DependenteDTO> dependentes) {
}

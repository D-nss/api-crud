package com.dnss.api_ti9.dto;

import com.dnss.api_ti9.model.Dependente;
import java.util.Date;


public record FuncionarioDTO(String nome,
                             String cpf,
                             String cargo,
                             Integer salario,
                             Date data_de_admissao,
                             Dependente dependentes) {
}

package com.dnss.api_ti9.service;

import com.dnss.api_ti9.dto.FuncionarioDTO;
import com.dnss.api_ti9.model.Funcionario;
import com.dnss.api_ti9.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public UUID createFuncionario(FuncionarioDTO funcionarioDTO){
        Funcionario funcionario = new Funcionario(
                funcionarioDTO.nome(),
                funcionarioDTO.cpf(),
                funcionarioDTO.cargo(),
                funcionarioDTO.salario(),
                funcionarioDTO.data_de_admissao(),
                funcionarioDTO.dependentes());

        var funcionarioCreated = funcionarioRepository.save(funcionario);
        return funcionarioCreated.getId();
    }
}

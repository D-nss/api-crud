package com.dnss.api_ti9.service;

import com.dnss.api_ti9.dto.DependenteDTO;
import com.dnss.api_ti9.dto.FuncionarioDTO;
import com.dnss.api_ti9.model.Dependente;
import com.dnss.api_ti9.model.Funcionario;
import com.dnss.api_ti9.repository.DependenteRepository;
import com.dnss.api_ti9.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DependenteService {

    private FuncionarioRepository funcionarioRepository;
    private DependenteRepository dependenteRepository;

    public DependenteService(FuncionarioRepository funcionarioRepository, DependenteRepository dependenteRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.dependenteRepository = dependenteRepository;
    }


}


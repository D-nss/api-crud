package com.dnss.api_ti9.service;

import com.dnss.api_ti9.repository.FuncionarioRepository;

public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
}

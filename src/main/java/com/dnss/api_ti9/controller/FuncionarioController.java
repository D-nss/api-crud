package com.dnss.api_ti9.controller;

import com.dnss.api_ti9.dto.FuncionarioDTO;
import com.dnss.api_ti9.dto.FuncionarioResponseDTO;
import com.dnss.api_ti9.model.Funcionario;
import com.dnss.api_ti9.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody FuncionarioDTO funcionarioDTO){
        var id = funcionarioService.createFuncionario(funcionarioDTO);
        return ResponseEntity.created(URI.create("/funcionarios/" + id.toString())).build();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Funcionario> getFuncionarioById(@PathVariable("id") String id){
        return null;
    }
}

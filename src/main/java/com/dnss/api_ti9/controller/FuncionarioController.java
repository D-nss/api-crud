package com.dnss.api_ti9.controller;

import com.dnss.api_ti9.dto.FuncionarioDTO;
import com.dnss.api_ti9.model.Funcionario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody FuncionarioDTO funcionarioDTO){
        return null;
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Funcionario> getFuncionarioById(@PathVariable("id")){
        return null;
    }
}

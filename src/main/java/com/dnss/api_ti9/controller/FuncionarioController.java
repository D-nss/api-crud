package com.dnss.api_ti9.controller;

import com.dnss.api_ti9.dto.FuncionarioDTO;
import com.dnss.api_ti9.model.Funcionario;
import com.dnss.api_ti9.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        return new ResponseEntity("ID do novo Funcionario: " + id.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Funcionario> getFuncionarioById(@PathVariable("id") String id){
        var funcionario = funcionarioService.getFuncionarioById(id);
        return new ResponseEntity(funcionario.get(), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public  ResponseEntity<Funcionario> getAllFuncionarios(){
        var funcionarios = funcionarioService.getAllFuncionarios();
        return new ResponseEntity(funcionarios, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Funcionario> deleteFuncionarioById(@PathVariable("id") String id){
        funcionarioService.deleteFuncionarioById(id);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
}

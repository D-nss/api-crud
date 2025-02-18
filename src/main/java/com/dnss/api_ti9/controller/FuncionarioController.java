package com.dnss.api_ti9.controller;

import com.dnss.api_ti9.dto.FuncionarioDTO;
import com.dnss.api_ti9.dto.FuncionarioResponseDTO;
import com.dnss.api_ti9.model.Funcionario;
import com.dnss.api_ti9.service.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/funcionarios")
@Tag(name = "Funcionarios")
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
        FuncionarioResponseDTO dto = new FuncionarioResponseDTO(funcionario.get().getNome(), funcionario.get().getCpf(), funcionario.get().getCargo(), funcionario.get().getSalario(), funcionario.get().getData_de_admissao(), funcionario.get().getDependentes());
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public  ResponseEntity<FuncionarioResponseDTO> getAllFuncionarios(){
        var funcionarios = funcionarioService.getAllFuncionarios();
        return new ResponseEntity(funcionarios, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Funcionario> deleteFuncionarioById(@PathVariable("id") String id){
        funcionarioService.deleteFuncionarioById(id);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Funcionario> updateFuncionarioById(@PathVariable("id") String id, @RequestBody FuncionarioDTO funcionarioDTO){
        var funcionario =funcionarioService.updateFuncionarioById(id, funcionarioDTO, null);
        FuncionarioResponseDTO dto = new FuncionarioResponseDTO(funcionario.getNome(), funcionario.getCpf(), funcionario.getCargo(), funcionario.getSalario(), funcionario.getData_de_admissao(), funcionario.getDependentes());
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/{idDependente}")
    public  ResponseEntity<Funcionario> updateFuncionarioDependenteById(@PathVariable("id") String id, @RequestBody FuncionarioDTO funcionarioDTO,  @PathVariable("idDependente") String idDependente){
        var funcionario =funcionarioService.updateFuncionarioById(id, funcionarioDTO, idDependente);
        return new ResponseEntity(funcionario, HttpStatus.CREATED);
    }
}

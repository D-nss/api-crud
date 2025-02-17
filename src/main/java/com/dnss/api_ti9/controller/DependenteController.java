package com.dnss.api_ti9.controller;

import com.dnss.api_ti9.dto.DependenteDTO;
import com.dnss.api_ti9.dto.FuncionarioDTO;
import com.dnss.api_ti9.dto.FuncionarioResponseDTO;
import com.dnss.api_ti9.model.Dependente;
import com.dnss.api_ti9.model.Funcionario;
import com.dnss.api_ti9.service.DependenteService;
import com.dnss.api_ti9.service.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dependentes")
@Tag(name = "Dependentes")
public class DependenteController {

    private DependenteService dependenteService;

    public DependenteController(DependenteService dependenteService) {
        this.dependenteService = dependenteService;
    }
    @PostMapping("/{idFuncionario}")
    public ResponseEntity<Dependente> createFuncionario(@PathVariable("idFuncionario") String idFuncionario, @RequestBody DependenteDTO dependenteDTO){
        var id = dependenteService.createDependente(idFuncionario, dependenteDTO);
        return new ResponseEntity("ID do novo Dependente: " + id.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Dependente> getFuncionarioById(@PathVariable("id") String id){
        var dependente = dependenteService.getDependenteById(id);
        return new ResponseEntity(dependente, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public  ResponseEntity<Dependente> getAllFuncionarios(){
        var dependentes = dependenteService.getAllDependentes();
        return new ResponseEntity(dependentes, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Dependente> deleteFuncionarioById(@PathVariable("id") String id){
        dependenteService.deleteDependenteById(id);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Dependente> updateDependenteById(@PathVariable("id") String id, @RequestBody DependenteDTO dependenteDTO){
        var dependente =dependenteService.updateDependenteById(id, dependenteDTO);
        return new ResponseEntity(dependente, HttpStatus.CREATED);
    }
}

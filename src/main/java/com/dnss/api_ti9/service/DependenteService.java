package com.dnss.api_ti9.service;

import com.dnss.api_ti9.dto.DependenteDTO;
import com.dnss.api_ti9.dto.FuncionarioDTO;
import com.dnss.api_ti9.dto.FuncionarioResponseDTO;
import com.dnss.api_ti9.exception.*;
import com.dnss.api_ti9.model.Dependente;
import com.dnss.api_ti9.model.Funcionario;
import com.dnss.api_ti9.repository.DependenteRepository;
import com.dnss.api_ti9.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DependenteService {

    private FuncionarioRepository funcionarioRepository;
    private DependenteRepository dependenteRepository;

    public DependenteService(FuncionarioRepository funcionarioRepository, DependenteRepository dependenteRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.dependenteRepository = dependenteRepository;
    }

    public UUID createDependente(String idFuncionario, DependenteDTO dependenteDTO){
        validateCampos(dependenteDTO);
        validateDataNascimento(dependenteDTO.data_de_nascimento());
        validateParentesco(dependenteDTO.parentesco());
        Dependente dependente = new Dependente(
                dependenteDTO.nome(),
                dependenteDTO.data_de_nascimento(),
                dependenteDTO.parentesco(),
                funcionarioRepository.findById(UUID.fromString(idFuncionario)).get());

        var dependenteCreated = dependenteRepository.save(dependente);
        return dependenteCreated.getId();

    }

    public Optional<Dependente> getDependenteById(String id){
        var dependente = dependenteRepository.findById(UUID.fromString(id));
        if(dependente.isEmpty()){
            throw new DependenteNaoExiste("Dependente não existe!");
        }
        return dependente;
    }

    public List<Dependente> getAllDependentes() {
        var dependentes = dependenteRepository.findAll();
        if(dependentes.isEmpty()){
            throw new DependenteNaoExiste("Nenhum dependente cadastrado!");
        }
        return dependentes;
    }

    public void deleteDependenteById(String id){
        var dependente = getDependenteById(id);
        dependenteRepository.deleteById(UUID.fromString(id));
    }

    public Dependente updateDependenteById(String id, DependenteDTO dependenteDTO){
        var dependente = getDependenteById(id);
        if(dependenteDTO.data_de_nascimento()!=null){
            dependente.get().setData_de_nascimento(dependenteDTO.data_de_nascimento());
        }
        if(dependenteDTO.parentesco()!=null){
            dependente.get().setParentesco(dependenteDTO.parentesco());
        }
        if(dependenteDTO.nome()!=null){
            dependente.get().setNome(dependenteDTO.nome());
        }
        dependenteRepository.save(dependente.get());
        return dependente.get();
    }

//VALIDACOES//////////////////////////////////////////////////////////////////////

    public void validateCampos(DependenteDTO dependenteDTO){
        if(dependenteDTO.nome()==null){
            throw new CampoObrigatorio("Campo obrigatório não preenchido!");
        }
        if(dependenteDTO.parentesco()==null){
            throw new CampoObrigatorio("Campo obrigatório não preenchido!");
        }
        if(dependenteDTO.data_de_nascimento() == null){
            throw new CampoObrigatorio("Campo obrigatório não preenchido!");
        }
    }

    public void validateDataNascimento(LocalDate data){
        if (data.isAfter(LocalDate.now())){
            throw new InvalidoDataNascimento("A data deve ser do passado");
        }
    }

    public String validateParentesco(String parentesco){
        if(parentesco.equals("Filho(a)")){
            return null;
        }
        if(parentesco.equals("Esposa")){
            return null;
        }
        if(parentesco.equals("Marido")){
            return null;
        }
        if(parentesco.equals("Outro")){
            return null;
        }
        throw new InvalidoParentesco("Parentesco inválido!");
    }

}


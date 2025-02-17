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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;
    private DependenteService dependenteService;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, DependenteService dependenteService) {
        this.funcionarioRepository = funcionarioRepository;
        this.dependenteService = dependenteService;
    }

    public UUID createFuncionario(FuncionarioDTO funcionarioDTO){
        validateCampos(funcionarioDTO);
        validateCPF(funcionarioDTO.cpf());
        validateNome(funcionarioDTO.nome());
        validateSalario(funcionarioDTO.salario());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataFinal = LocalDate.parse(funcionarioDTO.data_de_admissao(), formatter);
        validateDataAdmissao(dataFinal);

        Funcionario funcionario = new Funcionario(
                funcionarioDTO.nome(),
                funcionarioDTO.cpf(),
                funcionarioDTO.cargo(),
                funcionarioDTO.salario(),
                dataFinal);

        if (funcionarioDTO.dependentes() != null) {
            for (DependenteDTO dependenteDTO : funcionarioDTO.dependentes()) {
                dependenteService.validateCampos(dependenteDTO);
                dependenteService.validateParentesco(dependenteDTO.parentesco());
                dependenteService.validateDataNascimento(dependenteDTO.data_de_nascimento());
                Dependente dependente = new Dependente(
                        dependenteDTO.nome(),
                        dependenteDTO.data_de_nascimento(),
                        dependenteDTO.parentesco(),
                        funcionario
                );
                funcionario.addDependente(dependente);
            }
        }

        var funcionarioCreated = funcionarioRepository.save(funcionario);
        return funcionarioCreated.getId();

    }

    public Optional<Funcionario> getFuncionarioById(String id){
        var funcionario = funcionarioRepository.findById(UUID.fromString(id));
        if(funcionario.isEmpty()){
            throw new FuncionarioNaoExiste("Funcionário não existe!");
        }
        return funcionario;
    }

    public List<FuncionarioResponseDTO> getAllFuncionarios() {
        var funcionarios = funcionarioRepository.findAll();
        if(funcionarios.isEmpty()){
            throw new FuncionarioNaoExiste("Nenhum funcionário cadastrado!");
        }
        List<FuncionarioResponseDTO> lista = new ArrayList<>();
        for(Funcionario funcionario: funcionarios){
            FuncionarioResponseDTO dto = new FuncionarioResponseDTO(funcionario.getNome(), funcionario.getCpf(), funcionario.getCargo(), funcionario.getSalario(), funcionario.getData_de_admissao(), funcionario.getDependentes());
            lista.add(dto);
        }
        return lista;
    }

    public void deleteFuncionarioById(String id){
        var funcionario = getFuncionarioById(id);
        funcionarioRepository.deleteById(UUID.fromString(id));
    }

    public Funcionario updateFuncionarioById(String id, FuncionarioDTO funcionarioDTO, String idDependente){
        var funcionario = getFuncionarioById(id);
        if(funcionarioDTO.nome()!=null){
            validateNome(funcionarioDTO.nome());
            funcionario.get().setNome(funcionarioDTO.nome());
        }
        if(funcionarioDTO.cargo()!=null){
            funcionario.get().setCargo(funcionarioDTO.cargo());
        }
        if(funcionarioDTO.cpf()!=null){
            validateCPF(funcionarioDTO.cpf());
            funcionario.get().setCpf(funcionarioDTO.cpf());
        }
        if(funcionarioDTO.salario()!=null){
            validateSalario(funcionarioDTO.salario());
            funcionario.get().setSalario(funcionarioDTO.salario());
        }
        if(funcionarioDTO.data_de_admissao()!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataFinal = LocalDate.parse(funcionarioDTO.data_de_admissao(), formatter);
            validateDataAdmissao(dataFinal);
            funcionario.get().setData_de_admissao(dataFinal);
        }
        if(funcionarioDTO.dependentes()!=null){
            for(DependenteDTO dependenteDTO: funcionarioDTO.dependentes()){
                dependenteService.updateDependenteById(idDependente, dependenteDTO);
                }
            }


        funcionarioRepository.save(funcionario.get());
        return funcionario.get();
    }

//VALIDACOES//////////////////////////////////////////////////////////////////////

    public void validateCampos(FuncionarioDTO funcionarioDTO){
        if(funcionarioDTO.nome()==null){
            throw new CampoObrigatorio("Campo obrigatório não preenchido!");
        }
        if(funcionarioDTO.salario()==null){
            throw new CampoObrigatorio("Campo obrigatório não preenchido!");
        }
        if(funcionarioDTO.cargo()==null){
            throw new CampoObrigatorio("Campo obrigatório não preenchido!");
        }
        if(funcionarioDTO.data_de_admissao()==null){
            throw new CampoObrigatorio("Campo obrigatório não preenchido!");
        }
        if(funcionarioDTO.cpf()==null){
            throw new CampoObrigatorio("Campo obrigatório não preenchido!");
        }
    }

    public void validateCPF(String cpf){
        if(!isCPF(cpf)) {
            throw new InvalidoCPF("CPF inválido!");
        }
        var funcionario = funcionarioRepository.findByCpf(cpf);
        if(funcionario.isPresent()){
            throw new InvalidoCPF("CPF já cadastrado!");
        }
    }

    public void validateNome(String nome){
        if(nome.length()<3){
            throw new InvalidoNome("Nome muito curto, deve ter mais de duas letras!");
        }
    }

    public void validateSalario(BigDecimal salario){
        if(!(salario.intValue()>0)){
            throw new InvalidoSalario("Salário deve ser maior que zero!");
        }
    }

    public  void validateDataAdmissao(LocalDate data){
        if (data.isAfter(LocalDate.now())){
            throw new InvalidoDataAdmissao("A data deve ser do passado ou presente!");
        }
    }

    public static boolean isCPF(String CPF) {
        // considera-se erro CPF"s formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere "0" no inteiro 0
                // (48 eh a posicao de "0" na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }


}
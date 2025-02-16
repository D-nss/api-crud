package com.dnss.api_ti9.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name ="nome")
    private String nome;
    {
        this.nome = nome;
    }
    @Column(name ="cpf")
    private String cpf;
    {
        this.cpf = cpf;
    }
    @Column(name ="cargo")
    private String cargo;
    {
        this.cargo = cargo;
    }
    @Column(name ="salario")
    private BigDecimal salario;
    {
        this.salario = salario;
    }
    @Column(name ="data_de_admissao")
    private LocalDate data_de_admissao;
    {
        this.data_de_admissao = data_de_admissao;
    }
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dependente> dependentes = new ArrayList<>();
    @CreationTimestamp
    private Instant creationTimeStamp;
    @UpdateTimestamp
    private Instant updateTimeStamp;



    public Funcionario( String nome, String cpf, String cargo, BigDecimal salario, LocalDate data_de_admissao) {
        this.data_de_admissao = data_de_admissao;
        this.salario = salario;
        this.cargo = cargo;
        this.cpf = cpf;
        this.nome = nome;
        this.creationTimeStamp = Instant.now();
    }

    public void addDependente(Dependente dependente) {
        var dependdentesLocal = this.getDependentes();
        dependdentesLocal.add(dependente);
        this.setDependentes(dependdentesLocal);
        dependente.setFuncionario(this);
    }

    public void removeDependente(Dependente dependente) {
        var dependdentesLocal = this.getDependentes();
        dependdentesLocal.remove(dependente);
        this.setDependentes(dependdentesLocal);
        dependente.setFuncionario(null);
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    public void setCreationTimeStamp(Instant creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public void setUpdateTimeStamp(Instant updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public Instant getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public Instant getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public LocalDate getData_de_admissao() {
        return data_de_admissao;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void setData_de_admissao(LocalDate data_de_admissao) {
        this.data_de_admissao = data_de_admissao;
    }
}
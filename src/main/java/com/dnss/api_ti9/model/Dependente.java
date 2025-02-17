package com.dnss.api_ti9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name ="nome")
    private String nome;{
        this.nome = nome;
    }
    @Column(name ="data_de_nascimento")
    private LocalDate data_de_nascimento;
    {
        this.data_de_nascimento = data_de_nascimento;
    }
    @Column(name ="parentesco")
    private String parentesco;
    {
        this.parentesco = parentesco;
    }
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
    {
        this.funcionario = funcionario;
    }
    @CreationTimestamp
    private Instant creationTimeStamp;
    @UpdateTimestamp
    private Instant updateTimeStamp;

    public Dependente() {
    }

    public Dependente(String nome, LocalDate data_de_nascimento, String parentesco, Funcionario funcionario) {
        this.nome = nome;
        this.data_de_nascimento = data_de_nascimento;
        this.parentesco = parentesco;
        this.funcionario = funcionario;
        this.creationTimeStamp = Instant.now();
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData_de_nascimento(LocalDate data_de_nascimento) {
        this.data_de_nascimento = data_de_nascimento;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData_de_nascimento() {
        return data_de_nascimento;
    }

    public String getParentesco() {
        return parentesco;
    }
}

package com.dnss.api_ti9.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "funcionarios")
@Getter
@Setter
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name ="nome")
    private String nome;
    @Column(name ="cpf")
    private String cpf;
    @Column(name ="cargo")
    private String cargo;
    @Column(name ="salario")
    private BigDecimal salario;
    @Column(name ="data_de_admissao")
    private LocalDate data_de_admissao;
    @ManyToOne
    @JoinColumn(name="dependente_id")
    private Dependente dependentes;

    public Funcionario( String nome, String cpf, String cargo, BigDecimal salario, LocalDate data_de_admissao, Dependente dependentes) {
        this.dependentes = dependentes;
        this.data_de_admissao = data_de_admissao;
        this.salario = salario;
        this.cargo = cargo;
        this.cpf = cpf;
        this.nome = nome;
        this.id = UUID.randomUUID();
    }
}

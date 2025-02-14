package com.dnss.api_ti9.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name ="nome")
    private String nome;
    @Column(name ="data_de_nascimento")
    private Date data_de_nascimento;
    @Column(name ="parentesco")
    private String parentesco;
    @JoinColumn(name="funcionario_id")
    @Column(name ="funcionario")
    private Funcionario funcionario;

    public Dependente(String nome, Date data_de_nascimento, String parentesco, Funcionario funcionario) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.data_de_nascimento = data_de_nascimento;
        this.parentesco = parentesco;
        this.funcionario = funcionario;
    }
}

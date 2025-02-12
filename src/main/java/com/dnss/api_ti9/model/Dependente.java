package com.dnss.api_ti9.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private Date data_de_nascimento;
    private String parentesco;
    @JoinColumn(name="funcionario_id")
    @OneToOne
    private Funcionario funcionario;
}

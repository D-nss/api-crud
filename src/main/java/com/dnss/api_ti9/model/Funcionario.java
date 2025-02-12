package com.dnss.api_ti9.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "funcionarios")
@Getter
@Setter
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String cpf;
    private String cargo;
    private Integer salario;
    private Date data_de_admissao;
    @OneToMany
    @JoinColumn(name="dependente_id")
    private Dependente dependentes;
}

package com.dnss.api_ti9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dnss.api_ti9.model.Funcionario;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
}

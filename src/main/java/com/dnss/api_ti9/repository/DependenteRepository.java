package com.dnss.api_ti9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dnss.api_ti9.model.Dependente;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente,UUID> {
}

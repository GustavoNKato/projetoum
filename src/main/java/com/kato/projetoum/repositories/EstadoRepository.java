package com.kato.projetoum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kato.projetoum.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}

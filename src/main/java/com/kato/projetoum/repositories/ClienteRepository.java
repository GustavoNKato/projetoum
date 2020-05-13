package com.kato.projetoum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kato.projetoum.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

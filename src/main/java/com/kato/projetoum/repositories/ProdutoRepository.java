package com.kato.projetoum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kato.projetoum.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
}

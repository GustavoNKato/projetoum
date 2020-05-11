package com.kato.projetoum;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kato.projetoum.domain.Categoria;
import com.kato.projetoum.domain.Produto;
import com.kato.projetoum.repositories.CategoriaRepository;
import com.kato.projetoum.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoumApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 4000.00);
		Produto p2 = new Produto(null, "Mouse", 80.00);
		Produto p3 = new Produto(null, "Monitor", 2500.00);
		Produto p4 = new Produto(null, "Impressora", 800.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4));
		cat2.getProdutos().addAll(Arrays.asList(p4));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat1, cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		
	}

}

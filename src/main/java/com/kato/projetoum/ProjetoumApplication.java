package com.kato.projetoum;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kato.projetoum.domain.Categoria;
import com.kato.projetoum.domain.Cidade;
import com.kato.projetoum.domain.Cliente;
import com.kato.projetoum.domain.Endereco;
import com.kato.projetoum.domain.Estado;
import com.kato.projetoum.domain.Produto;
import com.kato.projetoum.domain.enums.TipoCliente;
import com.kato.projetoum.repositories.CategoriaRepository;
import com.kato.projetoum.repositories.CidadeRepository;
import com.kato.projetoum.repositories.ClienteRepository;
import com.kato.projetoum.repositories.EnderecoRepository;
import com.kato.projetoum.repositories.EstadoRepository;
import com.kato.projetoum.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoumApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "Guarulhos", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Rayan Salewski", "rayansalewski@gmail.com", "26392882630", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("18826699", "2777399919"));
		
		Endereco e1 = new Endereco(null, "R. Que Nao Percebe", "24", "Jardim Dosoma e Gomorra", "01224024", cli1, c3);
		Endereco e2 = new Endereco(null, "R. Logo ali", "69", "Jardim do Eden", "04232000", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
	}

}

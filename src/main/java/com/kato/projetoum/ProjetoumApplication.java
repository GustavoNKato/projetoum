package com.kato.projetoum;

import java.text.SimpleDateFormat;
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
import com.kato.projetoum.domain.ItemPedido;
import com.kato.projetoum.domain.Pagamento;
import com.kato.projetoum.domain.PagamentoComBoleto;
import com.kato.projetoum.domain.PagamentoComCartao;
import com.kato.projetoum.domain.Pedido;
import com.kato.projetoum.domain.Produto;
import com.kato.projetoum.domain.enums.EstadoPagamento;
import com.kato.projetoum.domain.enums.TipoCliente;
import com.kato.projetoum.repositories.CategoriaRepository;
import com.kato.projetoum.repositories.CidadeRepository;
import com.kato.projetoum.repositories.ClienteRepository;
import com.kato.projetoum.repositories.EnderecoRepository;
import com.kato.projetoum.repositories.EstadoRepository;
import com.kato.projetoum.repositories.ItemPedidoRepository;
import com.kato.projetoum.repositories.PagamentoRepository;
import com.kato.projetoum.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/07/2019 21:32"), e1, cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2019 19:21"), e2, cli1);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2019 23:59"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 4000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p2, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p4, 100.0, 1, 800.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2));
		p4.getItens().addAll(Arrays.asList(ip3));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}

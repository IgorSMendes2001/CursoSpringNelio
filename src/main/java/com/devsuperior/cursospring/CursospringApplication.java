package com.devsuperior.cursospring;

import com.devsuperior.cursospring.domain.*;
import com.devsuperior.cursospring.domain.enuns.TipoCliente;
import com.devsuperior.cursospring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private ProdutoRepository produtoRepository;
	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1=new Categoria(null,"Informática");
		Categoria cat2= new Categoria(null,"Escritório");
		Produto prod1=new Produto(null,"Computador",4500.00);
		Produto prod2=new Produto(null,"Impressora",300.00);
		Produto prod3=new Produto(null,"Mouse",80.00);

		cat1.getProdutos().addAll(Arrays.asList(prod1,prod2,prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		repository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3));

		Estado est1=new Estado(null,"Minas Gerais");
		Estado est2=new Estado(null,"São Paulo");
		Cidade c1=new Cidade(null,"Patos de Minas",est1);
		Cidade c2=new Cidade(null,"São Paulo",est2);
		Cidade c3=new Cidade(null,"Campinas",est2);
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

		Cliente cli1=new Cliente(null,"Maria Silva","maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("3789920232","99823924"));
		Endereco e1=new Endereco(null,"Rua Flores","300","Apto 303","Jardim","38220834",cli1,c1);
		Endereco e2=new Endereco(null,"Avenida Matos","105","Sala 900","Centro","38177251",cli1,c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}
}

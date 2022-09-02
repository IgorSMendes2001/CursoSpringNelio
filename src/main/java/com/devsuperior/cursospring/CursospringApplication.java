package com.devsuperior.cursospring;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.domain.Produto;
import com.devsuperior.cursospring.repositories.CategoriaRepository;
import com.devsuperior.cursospring.repositories.ProdutoRepository;
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

	}
}

package com.devsuperior.cursospring;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1=new Categoria(null,"Informática");
		Categoria cat2= new Categoria(null,"Escritório");
		repository.saveAll(Arrays.asList(cat1,cat2));

	}
}

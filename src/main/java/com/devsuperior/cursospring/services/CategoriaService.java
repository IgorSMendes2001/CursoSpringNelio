package com.devsuperior.cursospring.services;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;
    public List<Categoria> buscar(){
        List<Categoria> resposta=repository.findAll();
        return resposta;
    }
    public Optional<Categoria> findById(Integer id){
        Optional<Categoria> obj=repository.findById(id);
        return obj;

    }
}

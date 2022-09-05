package com.devsuperior.cursospring.services;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.domain.Cliente;
import com.devsuperior.cursospring.exceptions.ObjectNotFoundException;
import com.devsuperior.cursospring.repositories.CategoriaRepository;
import com.devsuperior.cursospring.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    public List<Cliente> buscar(){
        List<Cliente> resposta=repository.findAll();
        return resposta;
    }
    public Optional<Cliente> findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return Optional.ofNullable(obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())));
    }
}

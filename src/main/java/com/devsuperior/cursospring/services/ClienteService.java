package com.devsuperior.cursospring.services;

import com.devsuperior.cursospring.domain.Cliente;
import com.devsuperior.cursospring.exceptions.DataIntegrityException;
import com.devsuperior.cursospring.exceptions.ObjectNotFoundException;
import com.devsuperior.cursospring.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    public List<Cliente> findAll(){
        List<Cliente> resposta=repository.findAll();
        return resposta;
    }
    public Optional<Cliente> findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return Optional.ofNullable(obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())));
    }
    public Cliente insert(Cliente obj){
        obj.setId(null);
        return repository.save(obj);
    }
    public Cliente update(Cliente obj){
        findById(obj.getId());
        return repository.save(obj);
    }
    public void  delete(Integer id){
        findById(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir um Cliente que possui vínculos com produtos!");
        }
    }
}

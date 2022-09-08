package com.devsuperior.cursospring.services;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.dto.CategoriaDTO;
import com.devsuperior.cursospring.exceptions.DataIntegrityException;
import com.devsuperior.cursospring.exceptions.ObjectNotFoundException;
import com.devsuperior.cursospring.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;
    public List<Categoria> findAll() {
        List<Categoria> resposta = repository.findAll();
        return resposta;
    }
    public Page<Categoria> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),
                orderBy);
        return repository.findAll(pageRequest);
    }
    public Optional<Categoria> findById(Integer id){
        Optional<Categoria> obj = repository.findById(id);
        return Optional.ofNullable(obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())));
    }
    public Categoria insert(Categoria obj){
        obj.setId(null);
        return repository.save(obj);
    }
    public Categoria update(Categoria obj){
        findById(obj.getId());
        return repository.save(obj);
    }
    public void  delete(Integer id){
        findById(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui vínculos com produtos!");
        }
    }
    public Categoria fromDTO(CategoriaDTO objDTO){
        return new Categoria(objDTO.getId(),objDTO.getNome());
    }
}

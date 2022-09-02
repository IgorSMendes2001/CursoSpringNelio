package com.devsuperior.cursospring.resources;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {
    @Autowired
    private CategoriaService service;
    @GetMapping
    public List<Categoria> listarTodos(){
        List<Categoria>lista=service.buscar();
        return lista;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Categoria>> findById(@PathVariable Integer id){
        Optional<Categoria> response=service.findById(id);
        return ResponseEntity.ok().body(response);
    }
}

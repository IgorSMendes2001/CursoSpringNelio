package com.devsuperior.cursospring.resources;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {
    @Autowired
    private CategoriaService service;
    @GetMapping
    public List<Categoria> listarTodos(){
        List<Categoria>lista=service.findAll();
        return lista;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Categoria>> findById(@PathVariable Integer id){
        Optional<Categoria> response=service.findById(id);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping()
    public ResponseEntity<Void> insert(@RequestBody Categoria obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Categoria obj,@PathVariable Integer id){
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
            service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

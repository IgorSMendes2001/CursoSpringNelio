package com.devsuperior.cursospring.resources;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.dto.CategoriaDTO;
import com.devsuperior.cursospring.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {
    @Autowired
    private CategoriaService service;
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findALl(){
        List<Categoria>lista=service.findAll();
        List<CategoriaDTO>listDto= lista.stream().map(obj-> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page",defaultValue = "0") Integer page,@RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam (value = "orderBy",defaultValue = "nome") String orderBy,@RequestParam (value = "direction",defaultValue = "ASC") String direction){
        Page<Categoria>lista=service.findPage(page,linesPerPage,orderBy,direction);
        Page<CategoriaDTO>listDto= lista.map(obj-> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id){
        Categoria response=service.findById(id);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){
        Categoria obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO,@PathVariable Integer id){
        Categoria obj = service.fromDTO(objDTO);
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

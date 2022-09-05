package com.devsuperior.cursospring.resources;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.domain.Cliente;
import com.devsuperior.cursospring.services.CategoriaService;
import com.devsuperior.cursospring.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService service;
    @GetMapping
    public List<Cliente> listarTodos(){
        List<Cliente>lista=service.buscar();
        return lista;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Cliente>> findById(@PathVariable Integer id){
        Optional<Cliente> response=service.findById(id);
        return ResponseEntity.ok().body(response);
    }
}

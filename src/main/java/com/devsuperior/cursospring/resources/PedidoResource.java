package com.devsuperior.cursospring.resources;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.domain.Pedido;
import com.devsuperior.cursospring.dto.CategoriaDTO;
import com.devsuperior.cursospring.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
    @Autowired
    private PedidoService service;

    @GetMapping
    public List <Pedido> listarTodos() {
        List <Pedido> lista = service.buscar();
        return lista;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
        Pedido response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}

package com.devsuperior.cursospring.resources;

import com.devsuperior.cursospring.domain.Pedido;
import com.devsuperior.cursospring.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Optional<Pedido>> findById(@PathVariable Integer id) {
        Optional <Pedido> response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }
}

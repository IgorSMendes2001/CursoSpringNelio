package com.devsuperior.cursospring.resources;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.domain.Produto;
import com.devsuperior.cursospring.dto.CategoriaDTO;
import com.devsuperior.cursospring.dto.ProdutoDTO;
import com.devsuperior.cursospring.resources.utils.URL;
import com.devsuperior.cursospring.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> listarTodos() {
        List<Produto> lista = service.buscar();
        return lista;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {
        Produto response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "search")
    public ResponseEntity<Page<ProdutoDTO>> search(@RequestParam(value = "nome", defaultValue = "") String nome, @RequestParam(value = "categorias", defaultValue = "") String categorias, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                   @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> lista = service.search(nome, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = lista.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}

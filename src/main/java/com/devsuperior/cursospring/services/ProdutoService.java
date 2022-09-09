package com.devsuperior.cursospring.services;

import com.devsuperior.cursospring.domain.Categoria;
import com.devsuperior.cursospring.domain.Pedido;
import com.devsuperior.cursospring.domain.Produto;
import com.devsuperior.cursospring.exceptions.ObjectNotFoundException;
import com.devsuperior.cursospring.repositories.CategoriaRepository;
import com.devsuperior.cursospring.repositories.PedidoRepository;
import com.devsuperior.cursospring.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    public List<Produto> buscar(){
        List<Produto> resposta=repository.findAll();
        return resposta;
    }
    public Produto findById(Integer id){
        Produto obj = repository.findById(id).get();
        if(obj==null){
        throw new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
    }
        return obj;
    }
    public Page<Produto> search (String nome, List<Integer> ids,Integer page,Integer linesPerPage,String orderBy,String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),
                orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repository.search(nome,categorias,pageRequest);
    }

}

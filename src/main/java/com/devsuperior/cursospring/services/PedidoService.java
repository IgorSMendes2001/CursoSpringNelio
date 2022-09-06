package com.devsuperior.cursospring.services;

import com.devsuperior.cursospring.domain.Pedido;
import com.devsuperior.cursospring.domain.Pedido;
import com.devsuperior.cursospring.exceptions.ObjectNotFoundException;
import com.devsuperior.cursospring.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    public List<Pedido> buscar(){
        List<Pedido> resposta=repository.findAll();
        return resposta;
    }
    public Optional<Pedido> findById(Integer id){
        Optional<Pedido> obj = repository.findById(id);
        return Optional.ofNullable(obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())));
    }
}

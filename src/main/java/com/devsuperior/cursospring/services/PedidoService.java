package com.devsuperior.cursospring.services;

import com.devsuperior.cursospring.domain.*;
import com.devsuperior.cursospring.domain.Pedido;
import com.devsuperior.cursospring.domain.enuns.EstadoPagamento;
import com.devsuperior.cursospring.exceptions.ObjectNotFoundException;
import com.devsuperior.cursospring.repositories.ItemPedidoRepository;
import com.devsuperior.cursospring.repositories.PagamentoRepository;
import com.devsuperior.cursospring.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    public List<Pedido> buscar(){
        List<Pedido> resposta=repository.findAll();
        return resposta;
    }
    public Pedido findById(Integer id) {
        Pedido obj = repository.findById(id).get();
        if(obj == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
        }
        return obj;
    }
    public Pedido insert(Pedido obj){
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto=(PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
        }
        obj = repository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for(ItemPedido ip : obj.getItens()){
            ip.setDesconto(0.00);
            ip.setPreco(produtoService.findById(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }
}

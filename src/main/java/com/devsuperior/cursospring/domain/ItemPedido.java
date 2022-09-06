package com.devsuperior.cursospring.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class ItemPedido implements Serializable {
    private static final long serialVersionUID=1L;
    @EmbeddedId
    private ItemPedidoPk id = new ItemPedidoPk();
    private Double desconto;
    private Integer quantidade;
    private Double preco;
    public ItemPedido(){
    }

    public ItemPedido(Pedido pedido,Produto produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    public Pedido getPedido(){
        return id.getPedido();
    }
    public Produto getProduto(){
        return id.getProduto();
    }

    public ItemPedidoPk getId() {
        return id;
    }

    public void setId(ItemPedidoPk id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.devsuperior.cursospring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
@Entity
public class ItemPedido implements Serializable {
    private static final long serialVersionUID=1L;
    @JsonIgnore
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
    public Double getSubtotal(){
        return (preco - desconto)*quantidade;
    }
    @JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }
    public void setPedido(Pedido pedido){
        id.setPedido(pedido);
    }

    public Produto getProduto(){
        return id.getProduto();
    }
    public void setProduto(Produto produto){
        id.setProduto(produto);
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

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        final StringBuilder sb = new StringBuilder("ItemPedido{");
        sb.append(getProduto().getNome());
        sb.append(", Qte: ");
        sb.append(getQuantidade());
        sb.append(", Preço unitário: ");
        sb.append(getPreco());
        sb.append(", SubTotal: ");
        sb.append(nf.format(getSubtotal()));
        sb.append("\n");
        return sb.toString();
    }
}

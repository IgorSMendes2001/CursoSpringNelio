package com.devsuperior.cursospring.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID=1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
private Date instante;
@OneToOne(cascade = CascadeType.ALL,mappedBy = "pedido")
private Pagamento pagamento;
@ManyToOne
@JoinColumn(name = "cliente_id")
private Cliente cliente;
@ManyToOne
@JoinColumn(name = "endereco_de_entrega_id")
private Endereco enderecoDeEntrega;

    public Pedido() {
    }

    public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

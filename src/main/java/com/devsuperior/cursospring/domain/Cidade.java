package com.devsuperior.cursospring.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cidade implements Serializable {
private static final long serialVersionUID=1l;
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
private Integer id;
private String nome;
@ManyToOne @JoinColumn(name = "estado_id")
private Estado estado;

    public Cidade(Integer id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public Cidade() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}

package com.pdv.padaria.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="produtos")
@Data
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer estoque;
}

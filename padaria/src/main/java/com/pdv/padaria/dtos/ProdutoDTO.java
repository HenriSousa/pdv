package com.pdv.padaria.dtos;

public record ProdutoDTO(Long id,
                        String nome,
                        Integer quantidade, 
                        Double preco)
                        {}

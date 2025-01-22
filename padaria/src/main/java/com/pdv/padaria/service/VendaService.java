package com.pdv.padaria.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdv.padaria.dtos.VendaDTO;
import com.pdv.padaria.model.Produto;
import com.pdv.padaria.model.Venda;
import com.pdv.padaria.repository.ProdutoRepository;
import com.pdv.padaria.repository.VendaRepository;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda registrarVenda(VendaDTO vendaDTO) {
        Venda venda = new Venda();
        venda.setDataHora(LocalDateTime.now());

        // Calcular o total da venda
        Double total = vendaDTO.produtos().stream()
                               .mapToDouble(produtoDTO -> produtoDTO.preco() * produtoDTO.quantidade())
                               .sum();
        venda.setTotal(total);


        // Relacionar os produtos
        List<Produto> produtos = vendaDTO.produtos().stream()
                                         .map(produtoDTO -> produtoRepository.findById(produtoDTO.id())
                                                                             .orElseThrow(() -> new RuntimeException("Produto não encontrado")))
                                         .toList();
        venda.setProdutos(produtos);

        // Salvar a venda
        return vendaRepository.save(venda);
    }
}

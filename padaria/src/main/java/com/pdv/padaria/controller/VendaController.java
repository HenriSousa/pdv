package com.pdv.padaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pdv.padaria.dtos.VendaDTO;
import com.pdv.padaria.model.Venda;
import com.pdv.padaria.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> registrarVenda(@RequestBody VendaDTO vendaDTO) {
        Venda venda = vendaService.registrarVenda(vendaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(venda);
    }
}

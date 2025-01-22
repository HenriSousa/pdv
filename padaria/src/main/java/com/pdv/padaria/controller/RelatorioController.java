package com.pdv.padaria.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pdv.padaria.model.Venda;
import com.pdv.padaria.service.RelatorioService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {
    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/vendas")
    public ResponseEntity<List<Venda>> gerarRelatorioDeVendas(
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim) {
        List<Venda> vendas = relatorioService.gerarRelatorioDeVendas(dataInicio, dataFim);
        return ResponseEntity.ok(vendas);
    }
}

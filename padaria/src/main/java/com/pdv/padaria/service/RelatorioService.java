package com.pdv.padaria.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdv.padaria.model.Venda;
import com.pdv.padaria.repository.VendaRepository;

@Service
public class RelatorioService {
    @Autowired
    private VendaRepository vendaRepository;

    public List<Venda> gerarRelatorioDeVendas(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio != null && dataFim != null) {
            return vendaRepository.findByDataHoraBetween(dataInicio.atStartOfDay(), dataFim.atTime(23, 59));
        } else {
            return vendaRepository.findAll();
        }
    }
}

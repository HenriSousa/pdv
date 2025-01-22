package com.pdv.padaria.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdv.padaria.model.Venda;



public interface VendaRepository extends JpaRepository<Venda, Long> {
    
    List<Venda> findByDataHoraBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
}

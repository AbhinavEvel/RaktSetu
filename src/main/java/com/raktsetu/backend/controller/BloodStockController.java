package com.raktsetu.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktsetu.backend.entity.BloodStock;
import com.raktsetu.backend.service.BloodStockService;

@RestController
@RequestMapping("/api/stock")
public class BloodStockController {

    private final BloodStockService stockService;

    public BloodStockController(BloodStockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BloodStock>> getAllStock() {
        return ResponseEntity.ok(stockService.getAllStock());
    }
}

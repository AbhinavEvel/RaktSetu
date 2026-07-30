package com.rakthsetu.bloodbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakthsetu.bloodbank.dto.BloodComponentDTO;
import com.rakthsetu.bloodbank.service.BloodComponentService;

import jakarta.validation.Valid;

@RestController                          // Har method ka return JSON banega, HTML page nahi
@RequestMapping("/api/components")       // Is Controller ke saare endpoints "/api/components" se shuru honge
public class BloodComponentController {

    @Autowired
    private BloodComponentService service;

    // POST /api/components/add
    @PostMapping("/add")
    public ResponseEntity<BloodComponentDTO> addComponent(@Valid @RequestBody BloodComponentDTO dto) {
        BloodComponentDTO saved = service.addComponent(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);   // 201 status
    }

    // GET /api/components/all
    @GetMapping("/all")
    public ResponseEntity<List<BloodComponentDTO>> getAllComponents() {
        List<BloodComponentDTO> list = service.getAllComponents();
        return new ResponseEntity<>(list, HttpStatus.OK);         // 200 status
    }

    // GET /api/components/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BloodComponentDTO> getComponentById(@PathVariable Integer id) {
        BloodComponentDTO dto = service.getComponentById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // PUT /api/components/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<BloodComponentDTO> updateComponent(
            @PathVariable Integer id,
            @Valid @RequestBody BloodComponentDTO dto) {
        BloodComponentDTO updated = service.updateComponent(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // DELETE /api/components/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComponent(@PathVariable Integer id) {
        service.deleteComponent(id);
        return new ResponseEntity<>("Component deleted successfully", HttpStatus.OK);
    }
}

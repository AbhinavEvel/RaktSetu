package com.rakthsetu.bloodbank.service.impl;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakthsetu.bloodbank.dto.BloodComponentDTO;
import com.rakthsetu.bloodbank.entity.BloodComponent;
import com.rakthsetu.bloodbank.exception.ResourceNotFoundException;
import com.rakthsetu.bloodbank.mapper.BloodComponentMapper;
import com.rakthsetu.bloodbank.repository.BloodComponentRepository;
import com.rakthsetu.bloodbank.service.BloodComponentService;
//import com.raktsetu.bloodbank.dto.BloodComponentDTO;
//import com.raktsetu.bloodbank.entity.BloodComponent;
//import com.raktsetu.bloodbank.exception.ResourceNotFoundException;
//import com.raktsetu.bloodbank.mapper.BloodComponentMapper;
//import com.raktsetu.bloodbank.repository.BloodComponentRepository;
//import com.raktsetu.bloodbank.service.BloodComponentService;

@Service   // Batata hai: yeh class business logic hold karti hai, Spring isse bean bana kar container me register karega
public class BloodComponentServiceImpl implements BloodComponentService {

    @Autowired                                        // Spring khud BloodComponentRepository ka object inject karega
    private BloodComponentRepository repository;

    @Override
    public BloodComponentDTO addComponent(BloodComponentDTO dto) {
        BloodComponent entity = BloodComponentMapper.toEntity(dto);
        entity.setComponentId(null);          // Safety: naya record hai, ID DB khud generate karega
        BloodComponent saved = repository.save(entity);
        return BloodComponentMapper.toDTO(saved);
    }

    @Override
    public List<BloodComponentDTO> getAllComponents() {
        return repository.findAll()
                .stream()
                .map(BloodComponentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BloodComponentDTO getComponentById(Integer id) {
        BloodComponent entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blood component not found with id: " + id));
        return BloodComponentMapper.toDTO(entity);
    }

    @Override
    public BloodComponentDTO updateComponent(Integer id, BloodComponentDTO dto) {
        BloodComponent existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blood component not found with id: " + id));

        existing.setComponentName(dto.getComponentName());
        existing.setComponentCode(dto.getComponentCode());
        existing.setShelfLifeDays(dto.getShelfLifeDays());
        existing.setDescription(dto.getDescription());
        existing.setIsActive(dto.getIsActive());

        BloodComponent updated = repository.save(existing);
        return BloodComponentMapper.toDTO(updated);
    }

    @Override
    public void deleteComponent(Integer id) {
        BloodComponent existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blood component not found with id: " + id));
        repository.delete(existing);
    }
}

package com.rakthsetu.bloodbank.service;


import java.util.List;

import com.rakthsetu.bloodbank.dto.BloodComponentDTO;


public interface BloodComponentService {

    BloodComponentDTO addComponent(BloodComponentDTO dto);

    List<BloodComponentDTO> getAllComponents();

    BloodComponentDTO getComponentById(Integer id);

    BloodComponentDTO updateComponent(Integer id, BloodComponentDTO dto);

    void deleteComponent(Integer id);
}

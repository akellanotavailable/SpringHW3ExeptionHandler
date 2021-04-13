package com.epam.expositions.service;

import com.epam.expositions.dto.ExpositionDTO;
import com.epam.expositions.entity.Exposition;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpositionService {
    Exposition findById(Long id);

    List<ExpositionDTO> findByDate(LocalDateTime date);

    List<ExpositionDTO> findByHostId(Long id);

    List<ExpositionDTO> findALL(boolean showLegacy);

    Exposition create(Exposition entity);

    Exposition update(Exposition entity, Long id);

    boolean deleteById(Long id);

    boolean delete(Exposition entity);
}

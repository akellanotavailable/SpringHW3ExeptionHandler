package com.epam.expositions.dao;

import com.epam.expositions.entity.Exposition;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExpositionDAO extends GenericDAO<Exposition, Long>{
    List<Exposition> findByDate(LocalDateTime date);
    Optional<Exposition> findByHostId(Long id);
}

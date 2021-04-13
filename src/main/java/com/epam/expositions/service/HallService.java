package com.epam.expositions.service;

import com.epam.expositions.dto.DurationDTO;
import com.epam.expositions.dto.HallTimetableDTO;
import com.epam.expositions.entity.Hall;

import java.util.List;

public interface HallService {
    List<HallTimetableDTO> findAll();
    HallTimetableDTO findById(Long id);
    List<DurationDTO> getTimeUnavailable(Hall hall);
    boolean createHallReservation(Long hallId, Long expositionId);
    HallTimetableDTO create(HallTimetableDTO entity);

    HallTimetableDTO update(HallTimetableDTO entity, Long id);

    boolean delete(HallTimetableDTO entity);

}

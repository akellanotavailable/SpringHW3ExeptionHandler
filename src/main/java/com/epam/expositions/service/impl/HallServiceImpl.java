package com.epam.expositions.service.impl;

import com.epam.expositions.annotation.Timed;
import com.epam.expositions.dao.ExpositionDAO;
import com.epam.expositions.dao.HallDAO;
import com.epam.expositions.dto.DurationDTO;
import com.epam.expositions.dto.HallExpositionDTO;
import com.epam.expositions.dto.HallTimetableDTO;
import com.epam.expositions.entity.Exposition;
import com.epam.expositions.entity.Hall;
import com.epam.expositions.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Timed
public class HallServiceImpl implements HallService {
    private final HallDAO hallDAO;
    private final ExpositionDAO expositionDAO;

    @Override
    public List<HallTimetableDTO> findAll() {
        List<Hall> halls = hallDAO.findALL();
        List<Exposition> expositions = expositionDAO.findALL();
        List<HallExpositionDTO> hallExpositions = hallDAO.getHallExpositionReservation();

        List<HallTimetableDTO> hallTimetables = new ArrayList<>();

        halls.forEach(hall -> {
            List<DurationDTO> durationsList = new ArrayList<>();

            hallExpositions.forEach(hallExpositionDTO -> {
                if (hall.getId().equals(hallExpositionDTO.getHallId())) {
                    Exposition e = expositions.stream()
                            .filter(exposition -> exposition.getId().equals(hallExpositionDTO.getExpositionId()))
                            .findFirst().orElse(null);
                    assert e != null;
                    durationsList.add(new DurationDTO(e.getDateStart(), e.getDateEnd()));
                }
            });

            hallTimetables.add(new HallTimetableDTO(hall, durationsList));
        });

        return hallTimetables;
    }

    @Override
    public HallTimetableDTO findById(Long id) {
        List<HallTimetableDTO> hallTimetables = findAll();
        return hallTimetables.stream()
                .filter(hallTimetable ->
                        hallTimetable.getHall().getId().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<DurationDTO> getTimeUnavailable(Hall hall) {
        return findById(hall.getId()).getReservationDateTime();
    }

    @Override
    public HallTimetableDTO create(HallTimetableDTO entity) {
        return null;
    }

    @Override
    public HallTimetableDTO update(HallTimetableDTO entity, Long id) {
        return null;
    }

    @Override
    public boolean delete(HallTimetableDTO entity) {
        return false;
    }

    public boolean createHallReservation(Long hallId, Long expositionId){
        return hallDAO.createHallReservation(hallId, expositionId);
    }
}

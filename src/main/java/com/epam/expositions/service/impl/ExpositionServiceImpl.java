package com.epam.expositions.service.impl;

import com.epam.expositions.dao.ExpositionDAO;
import com.epam.expositions.dao.impl.ExpositionDAOImpl;
import com.epam.expositions.dto.ExpositionDTO;
import com.epam.expositions.entity.Exposition;
import com.epam.expositions.entity.Status;
import com.epam.expositions.service.ExpositionService;
import com.epam.expositions.util.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.epam.expositions.util.Converter.convertLocalDateTimeToSQLDateString;

@Service
@RequiredArgsConstructor
public class ExpositionServiceImpl implements ExpositionService {
    private final ExpositionDAO expositionDAO;

    @Override
    public Exposition findById(Long id) {
        return expositionDAO.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<ExpositionDTO> findByDate(LocalDateTime date) {
        List<Exposition> expositions = expositionDAO.findByDate(date);
        List<ExpositionDTO> expositionDTOS = new ArrayList<>();
        for (Exposition e :
                expositions) {
            expositionDTOS.add(ExpositionDTO.builder()
                    .id(e.getId())
                    .topic(e.getTopic())
                    .dateStart(convertLocalDateTimeToSQLDateString(e.getDateStart()))
                    .dateEnd(convertLocalDateTimeToSQLDateString(e.getDateEnd()))
                    .price(e.getPrice())
                    .capacity(e.getCapacity())
                    .imagePath(e.getImagePath())
                    .detailsLink(e.getDetailsLink())
                    .build());
        }
        return expositionDTOS;
    }

    @Override
    public List<ExpositionDTO> findByHostId(Long id) {
        List<ExpositionDTO> expositionDTOS = new ArrayList<>();
        List<Exposition> expositions = expositionDAO.findALL();
        expositions.forEach(exposition -> {
            if(exposition.getHostId().equals(id)){
                expositionDTOS.add(ExpositionDTO.builder()
                        .id(exposition.getId())
                        .topic(exposition.getTopic())
                        .dateStart(Converter.parseExpositionDateToDTOString(exposition.getDateStart()))
                        .dateEnd(Converter.parseExpositionDateToDTOString(exposition.getDateEnd()))
                        .price(exposition.getPrice())
                        .capacity(exposition.getCapacity())
                        .imagePath(exposition.getImagePath())
                        .detailsLink(exposition.getDetailsLink())
                        .statusName(exposition.getStatusName())
                        .build());
            }
        });
        return expositionDTOS;
    }

    @Override
    public List<ExpositionDTO> findALL(boolean showLegacy) {
        List<ExpositionDTO> expositionDTOS = new ArrayList<>();
        List<Exposition> expositions = expositionDAO.findALL();
        for (Exposition item :
                expositions) {

            if (!showLegacy && item.getStatusName().equals(Status.PURCHASED.getName())) {
                expositionDTOS.add(ExpositionDTO.builder()
                        .id(item.getId())
                        .topic(item.getTopic())
                        .dateStart(Converter.parseExpositionDateToDTOString(item.getDateStart()))
                        .dateEnd(Converter.parseExpositionDateToDTOString(item.getDateEnd()))
                        .price(item.getPrice())
                        .capacity(item.getCapacity())
                        .imagePath(item.getImagePath())
                        .detailsLink(item.getDetailsLink())
                        .statusName(item.getStatusName())
                        .build());
            }

            if (showLegacy) {
                expositionDTOS.add(ExpositionDTO.builder()
                        .id(item.getId())
                        .topic(item.getTopic())
                        .dateStart(Converter.parseExpositionDateToDTOString(item.getDateStart()))
                        .dateEnd(Converter.parseExpositionDateToDTOString(item.getDateEnd()))
                        .price(item.getPrice())
                        .capacity(item.getCapacity())
                        .imagePath(item.getImagePath())
                        .detailsLink(item.getDetailsLink())
                        .statusName(item.getStatusName())
                        .build());
            }

        }
        return expositionDTOS;
    }

    @Override
    public Exposition create(Exposition entity) {
        return expositionDAO.create(entity);
    }

    @Override
    public Exposition update(Exposition entity, Long id) {
        return expositionDAO.update(entity, id);
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean delete(Exposition entity) {
        return false;
    }
}

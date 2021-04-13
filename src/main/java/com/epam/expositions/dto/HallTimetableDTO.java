package com.epam.expositions.dto;

import com.epam.expositions.entity.Hall;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HallTimetableDTO {
    private Hall hall;
    private List<DurationDTO> reservationDateTime;
}

package com.epam.expositions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallExpositionDTO {
    private Long hallId;
    private Long expositionId;
}

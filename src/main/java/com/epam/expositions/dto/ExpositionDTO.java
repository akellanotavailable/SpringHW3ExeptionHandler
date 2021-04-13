package com.epam.expositions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpositionDTO {
    private Long id;
    private String topic;
    private String dateStart;
    private String dateEnd;
    private BigDecimal price;
    private Long capacity;
    private String imagePath;
    private String detailsLink;
    private String statusName;
}

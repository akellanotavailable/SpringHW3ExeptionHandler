package com.epam.expositions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DurationDTO {
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
}

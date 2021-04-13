package com.epam.expositions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase implements Persistable<Long> {
    private Long expositionId;
    private Long userId;
    private Status status;

    @Override
    public Long getId() {
        return expositionId;
    }
}

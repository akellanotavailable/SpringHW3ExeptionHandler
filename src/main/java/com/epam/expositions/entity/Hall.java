package com.epam.expositions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hall implements Persistable<Long>{
    private Long id;
    private String name;
}

package com.epam.expositions.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Status {
    DONE("done", 3L), PURCHASED("purchased", 1L),
    REFUNDED("refunded", 2L), WAITING("waiting", 4L);
    private final String name;
    private final Long id;

    public static Status fromName(String name) {
        return Arrays.stream(values())
                .filter(status -> status.name.equals(name))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public static Status fromId(Long id) {
        return Arrays.stream(values())
                .filter(status -> status.id.equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}

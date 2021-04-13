package com.epam.expositions.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Persistable<Long> {
    private Long id;
    private String login;
    private String password;
    private String email;
    private Role role;
}

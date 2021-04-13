package com.epam.expositions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterDTO {
    private String login;
    private String email;
    private String password;
    private String rePassword;
}

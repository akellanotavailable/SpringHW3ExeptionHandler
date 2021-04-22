package com.epam.expositions.service;

import com.epam.expositions.dto.LoginDTO;
import com.epam.expositions.exception.InvalidDataException;

public interface AuthService {
    String authenticate(LoginDTO data) throws InvalidDataException;
}

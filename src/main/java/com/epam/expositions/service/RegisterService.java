package com.epam.expositions.service;

import com.epam.expositions.dto.RegisterDTO;
import com.epam.expositions.exception.InvalidDataException;

public interface RegisterService {
    void register(RegisterDTO data) throws InvalidDataException;
}

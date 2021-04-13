package com.epam.expositions.service.impl;

import com.epam.expositions.dao.UserDAO;
import com.epam.expositions.dao.impl.UserDAOImpl;
import com.epam.expositions.dto.RegisterDTO;
import com.epam.expositions.entity.Role;
import com.epam.expositions.entity.RoleName;
import com.epam.expositions.entity.User;
import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserDAO userDAO;

    @Override
    public void register(RegisterDTO data) throws InvalidDataException {

        if (!data.getPassword().equals(data.getRePassword())) {
            throw new InvalidDataException("Passwords do not match.");
        }

        if (userDAO.findByLogin(data.getLogin()).isPresent()) {
            throw new InvalidDataException("This user already exists");
        }

        if (userDAO.findByEmail(data.getEmail()).isPresent()) {
            throw new InvalidDataException("This email is already registered");
        }

        userDAO.create(User.builder()
                .login(data.getLogin())
                .email(data.getEmail())
                .password(data.getPassword())
                .role(new Role(3L, RoleName.USER.getRoleName()))
                .build());
    }
}

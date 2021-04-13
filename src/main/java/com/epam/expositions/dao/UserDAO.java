package com.epam.expositions.dao;

import com.epam.expositions.entity.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User, Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
}

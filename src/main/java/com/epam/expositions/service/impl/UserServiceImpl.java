package com.epam.expositions.service.impl;

import com.epam.expositions.dao.UserDAO;
import com.epam.expositions.dao.impl.UserDAOImpl;
import com.epam.expositions.entity.User;
import com.epam.expositions.exception.UserNotFoundException;
import com.epam.expositions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public User findByLogin(String login) {
        return userDAO.findByLogin(login).orElseThrow(() -> new UserNotFoundException("User not found - " + login));
    }

    @Override
    public List<User> findALL() {
        return userDAO.findALL();
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User not found - " + id));
    }

    @Override
    public User create(User entity) {
        return userDAO.create(entity);
    }

    @Override
    public User update(User entity, Long id) {
        return userDAO.update(entity, id);
    }

    @Override
    public boolean deleteById(Long id) {
        return userDAO.deleteById(id);
    }

    @Override
    public boolean delete(User entity) {
        return userDAO.delete(entity);
    }

}

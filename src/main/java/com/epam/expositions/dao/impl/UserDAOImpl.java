package com.epam.expositions.dao.impl;

import com.epam.expositions.dao.AbstractDAO;
import com.epam.expositions.dao.UserDAO;
import com.epam.expositions.dao.mapper.ResultSetMapper;
import com.epam.expositions.dao.mapper.UserMapper;
import com.epam.expositions.entity.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import static com.epam.expositions.dao.query.UserQueries.*;

@Repository
public class UserDAOImpl extends AbstractDAO<User, Long> implements UserDAO {
    public UserDAOImpl(Connection connection, ResultSetMapper<User> mapper) {
        super(connection, mapper);
    }

    @Override
    protected String getSelectQuery() {
        return SELECT_FROM_USER;
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_USER_BY_ID;
    }

    @Override
    protected String getSelectLastInsertedQuery() {
        return SELECT_LAST_USER_INSERTED;
    }

    @Override
    protected String getCreateQuery() {
        return CREATE_USER;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_USER_BY_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_USER_BY_ID;
    }

    @Override
    @SneakyThrows
    protected void prepareInsertStatement(PreparedStatement statement, User object) {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getPassword());
        statement.setString(3, object.getEmail());
        statement.setLong(4, object.getRole().getId());
    }

    @Override
    @SneakyThrows
    protected void prepareUpdateStatement(PreparedStatement statement, User object) {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getPassword());
        statement.setString(3, object.getEmail());
        statement.setLong(4, object.getRole().getId());
        statement.setLong(5, object.getId());
    }

    @Override
    @SneakyThrows
    public Optional<User> findByLogin(String login) {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USER_BY_LOGIN);
        preparedStatement.setString(1, login);

        ResultSet resultSet = preparedStatement.executeQuery();

        return mapper.map(resultSet).stream().findFirst();
    }

    @Override
    @SneakyThrows
    public Optional<User> findByEmail(String email) {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USER_BY_EMAIL);
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();

        return mapper.map(resultSet).stream().findFirst();
    }
}

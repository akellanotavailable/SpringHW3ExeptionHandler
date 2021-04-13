package com.epam.expositions.dao;

import com.epam.expositions.dao.mapper.ResultSetMapper;
import com.epam.expositions.entity.Persistable;
import lombok.SneakyThrows;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T extends Persistable<ID>, ID> implements GenericDAO<T, ID> {

    protected final Connection connection;
    protected ResultSetMapper<T> mapper;

    public AbstractDAO(Connection connection, ResultSetMapper<T> mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }

    protected abstract String getSelectQuery();

    protected abstract String getSelectByIdQuery();

    protected abstract String getSelectLastInsertedQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract void prepareInsertStatement(PreparedStatement statement, T object);

    protected abstract void prepareUpdateStatement(PreparedStatement statement, T object);

    @Override
    @SneakyThrows
    public Optional<T> findById(ID id) {
        String query = getSelectByIdQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> res = mapper.map(resultSet);
        return res.stream().findFirst();
    }

    @Override
    @SneakyThrows
    public List<T> findALL() {
        String query = getSelectQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return mapper.map(resultSet);
    }

    @Override
    @SneakyThrows
    public T create(T entity) {
        PreparedStatement preparedStatement = connection.prepareStatement(getCreateQuery());
        prepareInsertStatement(preparedStatement, entity);
        preparedStatement.executeUpdate();

        ResultSet resultSet = connection
                .prepareStatement(getSelectLastInsertedQuery())
                .executeQuery();

        return mapper.map(resultSet).get(0);
    }

    @Override
    @SneakyThrows
    public T update(T entity, ID id) {
        PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());
        prepareUpdateStatement(preparedStatement, entity);
        preparedStatement.executeUpdate();

        return findById(id).orElseThrow(SQLException::new);
    }

    @Override
    public boolean deleteById(ID id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery());
            preparedStatement.setLong(1, (Long) id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(T entity) {
        return deleteById(entity.getId());
    }
}

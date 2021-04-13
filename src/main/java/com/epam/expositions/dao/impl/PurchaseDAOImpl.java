package com.epam.expositions.dao.impl;

import com.epam.expositions.dao.AbstractDAO;
import com.epam.expositions.dao.PurchaseDAO;
import com.epam.expositions.dao.mapper.PurchaseMapper;
import com.epam.expositions.dao.mapper.ResultSetMapper;
import com.epam.expositions.dao.query.PurchaseQueries;
import com.epam.expositions.entity.Purchase;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PurchaseDAOImpl extends AbstractDAO<Purchase, Long> implements PurchaseDAO {

    public PurchaseDAOImpl(Connection connection, ResultSetMapper<Purchase> mapper) {
        super(connection, mapper);
    }

    @Override
    protected String getSelectQuery() {
        return PurchaseQueries.SELECT_PURCHASE;
    }

    @Override
    protected String getSelectByIdQuery() {
        return PurchaseQueries.SELECT_PURCHASE_BY_EXPOSITION_ID;
    }

    @Override
    protected String getSelectLastInsertedQuery() {
        return PurchaseQueries.SELECT_LAST_INSERTED_PURCHASE;
    }

    @Override
    protected String getCreateQuery() {
        return PurchaseQueries.CREATE_PURCHASE;
    }

    @Override
    protected String getUpdateQuery() {
        return PurchaseQueries.UPDATE_PURCHASE;
    }

    @Override
    protected String getDeleteQuery() {
        return PurchaseQueries.DELETE_BY_EXPOSITION_ID;
    }

    @Override
    @SneakyThrows
    protected void prepareInsertStatement(PreparedStatement statement, Purchase object) {
        statement.setLong(1, object.getExpositionId());
        statement.setLong(2, object.getUserId());
        statement.setLong(3, object.getStatus().getId());
    }

    @Override
    @SneakyThrows
    protected void prepareUpdateStatement(PreparedStatement statement, Purchase object) {
        statement.setLong(1, object.getExpositionId());
        statement.setLong(2, object.getUserId());
        statement.setLong(3, object.getStatus().getId());
        statement.setLong(4, object.getExpositionId());
    }

    @Override
    @SneakyThrows
    public List<Purchase> findByExpositionId(Long id) {
        PreparedStatement preparedStatement = connection
                .prepareStatement(PurchaseQueries.SELECT_PURCHASE_BY_EXPOSITION_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return mapper.map(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Purchase> findByUserId(Long id) {
        PreparedStatement preparedStatement = connection
                .prepareStatement(PurchaseQueries.SELECT_PURCHASE_BY_USER_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return mapper.map(resultSet);
    }

    @Override
    @SneakyThrows
    public Purchase create(Purchase entity) {
        PreparedStatement preparedStatement = connection.prepareStatement(getCreateQuery());
        prepareInsertStatement(preparedStatement, entity);
        preparedStatement.executeUpdate();

        preparedStatement = connection
                .prepareStatement(getSelectLastInsertedQuery());
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getExpositionId());

        ResultSet resultSet = preparedStatement.executeQuery();

        return mapper.map(resultSet).get(0);
    }

    @Override
    public boolean deleteByExpositionId(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(PurchaseQueries.DELETE_BY_EXPOSITION_ID);
            preparedStatement.setLong(1, (Long) id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByUserId(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(PurchaseQueries.DELETE_BY_USER_ID);
            preparedStatement.setLong(1, (Long) id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}

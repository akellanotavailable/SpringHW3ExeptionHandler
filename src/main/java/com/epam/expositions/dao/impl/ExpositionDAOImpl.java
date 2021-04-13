package com.epam.expositions.dao.impl;

import com.epam.expositions.dao.AbstractDAO;
import com.epam.expositions.dao.ExpositionDAO;
import com.epam.expositions.dao.mapper.ExpositionMapper;
import com.epam.expositions.dao.mapper.ResultSetMapper;
import com.epam.expositions.dao.query.ExpositionQueries;
import com.epam.expositions.entity.Exposition;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.epam.expositions.entity.Status;
import org.springframework.stereotype.Repository;

@Repository
public class ExpositionDAOImpl extends AbstractDAO<Exposition, Long> implements ExpositionDAO {

    public ExpositionDAOImpl(Connection connection, ResultSetMapper<Exposition> mapper) {
        super(connection, mapper);
    }

    @Override
    protected String getSelectQuery() {
        return ExpositionQueries.SELECT_FROM_EXPOSITION ;
    }

    @Override
    protected String getSelectByIdQuery() {
        return ExpositionQueries.SELECT_FROM_EXPOSITION_BY_ID;
    }

    @Override
    protected String getSelectLastInsertedQuery() {
        return ExpositionQueries.SELECT_LAST_EXPOSITION_INSERTED;
    }

    @Override
    protected String getCreateQuery() {
        return ExpositionQueries.CREATE_EXPOSITION;
    }

    @Override
    protected String getUpdateQuery() {
        return ExpositionQueries.UPDATE_EXPOSITION_BY_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return ExpositionQueries.DELETE_EXPOSITION_BY_ID;
    }

    private void preparePreparedStatement(PreparedStatement statement, Exposition object) throws SQLException {
        statement.setLong(1, object.getHostId());
        statement.setString(2, object.getTopic());
        statement.setObject(3, object.getDateStart());
        statement.setObject(4, object.getDateEnd());
        statement.setBigDecimal(5, object.getPrice());
        statement.setLong(6, object.getCapacity());
        statement.setLong(7, Status.fromName(object.getStatusName()).getId());
    }

    private void prepareDetailsInsertStatement(PreparedStatement statement, Exposition object) throws SQLException {
        statement.setLong(1, object.getId());
        statement.setString(2, object.getImagePath());
        statement.setString(3, object.getDetailsLink());
    }

    private void prepareDetailsUpdateStatement(PreparedStatement statement, Exposition object) throws SQLException {
        statement.setString(1, object.getImagePath());
        statement.setString(2, object.getDetailsLink());
        statement.setLong(3, object.getId());
    }

    @Override
    @SneakyThrows
    protected void prepareInsertStatement(PreparedStatement statement, Exposition object) {
        preparePreparedStatement(statement, object);
    }

    @Override
    @SneakyThrows
    protected void prepareUpdateStatement(PreparedStatement statement, Exposition object) {
        preparePreparedStatement(statement, object);
        statement.setLong(8, object.getId());
    }

    @Override
    @SneakyThrows
    public List<Exposition> findByDate(LocalDateTime date) {
        PreparedStatement preparedStatement = connection.prepareStatement(ExpositionQueries.SELECT_FROM_EXPOSITION_BY_DATE);
        preparedStatement.setObject(1, date);
        preparedStatement.setObject(2, date);
        ResultSet resultSet = preparedStatement.executeQuery();
        return mapper.map(resultSet);
    }

    @Override
    @SneakyThrows
    public Exposition create(Exposition entity) {
        PreparedStatement preparedExpositionStatement = connection.prepareStatement(getCreateQuery());
        prepareInsertStatement(preparedExpositionStatement, entity);
        preparedExpositionStatement.executeUpdate();

        ResultSet resultSet = connection.prepareStatement(ExpositionQueries.GET_LAST_EXPOSITION_INSERT_ID)
                .executeQuery();
        resultSet.next();
        Long id = resultSet.getLong("id");
        entity.setId(id);

        PreparedStatement preparedExpositionDetailsStatement = connection.prepareStatement(ExpositionQueries.CREATE_EXPOSITION_DETAILS);
        prepareDetailsInsertStatement(preparedExpositionDetailsStatement, entity);
        preparedExpositionDetailsStatement.executeUpdate();

        resultSet = connection
                .prepareStatement(getSelectLastInsertedQuery())
                .executeQuery();

        return mapper.map(resultSet).get(0);
    }

    @Override
    @SneakyThrows
    public Exposition update(Exposition entity, Long aLong) {
        connection.setAutoCommit(false);

        PreparedStatement preparedExpositionStatement = connection.prepareStatement(getUpdateQuery());
        prepareUpdateStatement(preparedExpositionStatement, entity);
        preparedExpositionStatement.executeUpdate();

        PreparedStatement preparedExpositionDetailsStatement = connection.prepareStatement(ExpositionQueries.UPDATE_EXPOSITION_DETAILS_BY_ID);
        prepareDetailsUpdateStatement(preparedExpositionDetailsStatement, entity);
        preparedExpositionDetailsStatement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);

        PreparedStatement statement = connection.prepareStatement(ExpositionQueries.SELECT_FROM_EXPOSITION_BY_ID);
                statement.setLong(1, entity.getId());
        ResultSet resultSet = statement.executeQuery();

        return mapper.map(resultSet).get(0);
    }

    @Override
    public Optional<Exposition> findByHostId(Long id) {
        return Optional.empty();
    }
}

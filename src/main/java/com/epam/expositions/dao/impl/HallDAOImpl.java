package com.epam.expositions.dao.impl;

import com.epam.expositions.dao.AbstractDAO;
import com.epam.expositions.dao.HallDAO;
import com.epam.expositions.dao.mapper.HallMapper;
import com.epam.expositions.dao.mapper.ResultSetMapper;
import com.epam.expositions.dao.query.HallQueries;
import com.epam.expositions.dto.HallExpositionDTO;
import com.epam.expositions.entity.Hall;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HallDAOImpl extends AbstractDAO<Hall, Long> implements HallDAO {
    public HallDAOImpl(Connection connection, ResultSetMapper<Hall> mapper) {
        super(connection, mapper);
    }

    @Override
    protected String getSelectQuery() {
        return HallQueries.SELECT_FROM_HALL;
    }

    @Override
    protected String getSelectByIdQuery() {
        return HallQueries.SELECT_FROM_HALL_BY_ID;
    }

    @Override
    protected String getSelectLastInsertedQuery() {
        return HallQueries.SELECT_FROM_HALL_LAST;
    }

    @Override
    protected String getCreateQuery() {
        return HallQueries.CREATE_HALL;
    }

    @Override
    protected String getUpdateQuery() {
        return HallQueries.UPDATE_HALL_BY_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return HallQueries.DELETE_HALL;
    }

    @Override
    @SneakyThrows
    protected void prepareInsertStatement(PreparedStatement statement, Hall object) {
        statement.setLong(1, object.getId());
        statement.setString(2, object.getName());
    }

    @Override
    @SneakyThrows
    protected void prepareUpdateStatement(PreparedStatement statement, Hall object) {
        statement.setLong(1, object.getId());
        statement.setString(2, object.getName());
        statement.setLong(3, object.getId());
    }

    @SneakyThrows
    public List<HallExpositionDTO> getHallExpositionReservation() {
        List<HallExpositionDTO> list = new ArrayList<>();

        PreparedStatement statement = connection
                .prepareStatement("SELECT hall_id, exposition_id FROM hall_has_exposition");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            list.add(
                    new HallExpositionDTO(
                            resultSet.getLong("hall_id"),
                            resultSet.getLong("exposition_id")));
        }

        return list;
    }

    public boolean createHallReservation(Long hallId, Long expositionId) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO hall_has_exposition (hall_id, exposition_id) VALUES (?, ?)");
            statement.setLong(1, hallId);
            statement.setLong(2, expositionId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

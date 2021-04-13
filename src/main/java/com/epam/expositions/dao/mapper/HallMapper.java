package com.epam.expositions.dao.mapper;

import com.epam.expositions.entity.Hall;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class HallMapper implements ResultSetMapper<Hall>{
    @Override
    @SneakyThrows
    public List<Hall> map(ResultSet resultSet) {
        List<Hall> halls = new ArrayList<>();
        while (resultSet.next()) {
            Hall hall =
                    new Hall(resultSet.getLong("id"),
                            resultSet.getString("name"));
            halls.add(hall);
        }
        return halls;
    }
}

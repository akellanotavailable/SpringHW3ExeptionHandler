package com.epam.expositions.dao.mapper;

import com.epam.expositions.entity.Persistable;

import java.sql.ResultSet;
import java.util.List;

public interface ResultSetMapper<T extends Persistable<?>> {
    List<T> map(ResultSet resultSet);
}
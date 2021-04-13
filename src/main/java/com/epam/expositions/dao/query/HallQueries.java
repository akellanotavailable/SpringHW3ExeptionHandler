package com.epam.expositions.dao.query;

public class HallQueries {
    public static final String SELECT_FROM_HALL = "SELECT h.id, h.name FROM hall as h";
    public static final String SELECT_FROM_HALL_BY_ID = "SELECT h.id, h.name FROM hall as h WHERE h.id = ?";
    public static final String SELECT_FROM_HALL_LAST = "SELECT h.id, h.name FROM hall as h WHERE h.id = last_insert_id()";
    public static final String CREATE_HALL = "INSERT INTO hall (id, name) VALUES (?, ?)";
    public static final String UPDATE_HALL_BY_ID = "UPDATE hall SET id = ?, name = ? WHERE id = ?";
    public static final String DELETE_HALL = "DELETE FROM hall WHERE id = ?";
}

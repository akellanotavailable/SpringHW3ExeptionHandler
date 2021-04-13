package com.epam.expositions.dao.query;

public class ExpositionQueries {
    public static final String SELECT_FROM_EXPOSITION_BY_DATE = "SELECT e.id, e.host_user_id, e.topic, e.date_start, \n" +
            "e.date_end, e.price, e.capacity, ep.image, ep.details, s.name as status_name FROM exposition as e\n" +
            " join exposition_presentation as ep on e.id = ep.exposition_id\n" +
            " join status as s on status_id = s.id\n"+
            " where ? > date_start AND ? < date_end";

    public static final String SELECT_FROM_EXPOSITION_BY_ID = "SELECT e.id, e.host_user_id, e.topic, e.date_start, \n" +
            "e.date_end, e.price, e.capacity, ep.image, ep.details, s.name as status_name  FROM exposition as e\n" +
            " join exposition_presentation as ep on e.id = ep.exposition_id\n" +
            " join status as s on status_id = s.id\n"+
            " where e.id = ?";

    public static final String SELECT_FROM_EXPOSITION = "SELECT e.id, e.host_user_id, e.topic, e.date_start, \n" +
            "e.date_end, e.price, e.capacity, ep.image, ep.details, s.name as status_name  FROM exposition as e\n" +
            " join exposition_presentation as ep on e.id = ep.exposition_id"+
            " join status as s on status_id = s.id\n";

    public static final String SELECT_FROM_EXPOSITION_BY_HOST_ID = "SELECT e.id, e.host_user_id, e.topic, e.date_start, \n" +
            "e.date_end, e.price, e.capacity, ep.image, ep.details, s.name as status_name  FROM exposition as e\n" +
            " join exposition_presentation as ep on e.id = ep.exposition_id\n" +
            " join status as s on status_id = s.id\n"+
            " where e.host_user_id = ?";

    public static final String SELECT_LAST_EXPOSITION_INSERTED = "SELECT e.id, e.host_user_id, e.topic, e.date_start, \n" +
            "e.date_end, e.price, e.capacity, ep.image, ep.details, s.name as status_name  FROM exposition as e\n" +
            " join exposition_presentation as ep on e.id = ep.exposition_id\n"+
            " join status as s on status_id = s.id\n"+
            " where e.id = last_insert_id()";

    public static final String GET_LAST_EXPOSITION_INSERT_ID = "SELECT e.id FROM exposition as e WHERE e.id = last_insert_id()";

    public static final String CREATE_EXPOSITION =
            "INSERT INTO exposition (host_user_id, topic, date_start,\n" +
            "date_end, price, capacity, status_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String CREATE_EXPOSITION_DETAILS = "INSERT INTO exposition_presentation (exposition_id , image, details)\n" +
            "VALUES(?, ?, ?);";

    public static final String DELETE_EXPOSITION_BY_ID = "DELETE FROM exposition WHERE (id = ?)";

    public static final String UPDATE_EXPOSITION_BY_ID = "UPDATE exposition SET host_user_id = ?, topic = ?, date_start = ?,\n" +
            "date_end = ?, price = ?, capacity = ?, status_id = ? WHERE (id = ?)";

    public static final String UPDATE_EXPOSITION_DETAILS_BY_ID = "UPDATE exposition_presentation SET \n" +
            "image = ?, details = ? WHERE (exposition_id = ?)";

}

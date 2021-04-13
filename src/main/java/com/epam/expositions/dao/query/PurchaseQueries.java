package com.epam.expositions.dao.query;

public class PurchaseQueries {
    public static final String SELECT_PURCHASE = "SELECT p.exposition_id, p.user_id, s.name as status \n" +
            "FROM exposition_has_user as p JOIN status as s on p.status_id = s.id";

    public static final String SELECT_PURCHASE_BY_USER_ID = "SELECT p.exposition_id, p.user_id, s.name as status \n" +
            "FROM exposition_has_user as p JOIN status as s on p.status_id = s.id\n" +
            "where p.user_id = ?";

    public static final String SELECT_PURCHASE_BY_EXPOSITION_ID = "SELECT p.exposition_id, p.user_id, s.name as status \n" +
            "FROM exposition_has_user as p JOIN status as s on p.status_id = s.id\n" +
            "where p.exposition_id = ?";

    public static final String SELECT_LAST_INSERTED_PURCHASE = "SELECT p.exposition_id, p.user_id, s.name as status \n" +
            "FROM exposition_has_user as p JOIN status as s on p.status_id = s.id\n" +
            "where p.user_id = ? and p.exposition_id = ?";

    public static final String CREATE_PURCHASE = "INSERT INTO exposition_has_user (exposition_id, user_id, status_id) VALUES (?, ?, ?)";

    public static final String UPDATE_PURCHASE = "UPDATE exposition_has_user SET \n" +
            "exposition_id = ?, user_id = ?, status_id = ? WHERE (exposition_id = ?)";

    public static final String DELETE_BY_USER_ID = "DELETE FROM exposition_has_user WHERE user_id = ?";

    public static final String DELETE_BY_EXPOSITION_ID = "DELETE FROM exposition_has_user WHERE exposition_id = ?";

}

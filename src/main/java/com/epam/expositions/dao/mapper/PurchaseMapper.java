package com.epam.expositions.dao.mapper;

import com.epam.expositions.entity.Purchase;
import com.epam.expositions.entity.Status;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseMapper implements ResultSetMapper<Purchase> {
    @Override
    @SneakyThrows
    public List<Purchase> map(ResultSet resultSet) {
        List<Purchase> purchases = new ArrayList<>();
        while (resultSet.next()) {
            Purchase purchase =
                    new Purchase(resultSet.getLong("exposition_id"),
                            resultSet.getLong("user_id"),
                            Status.fromName(resultSet.getString("status")));
            purchases.add(purchase);
        }
        return purchases;
    }
}

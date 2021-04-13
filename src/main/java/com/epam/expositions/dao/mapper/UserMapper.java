package com.epam.expositions.dao.mapper;

import com.epam.expositions.entity.Role;
import com.epam.expositions.entity.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements ResultSetMapper<User>{

    @SneakyThrows
    public List<User> map(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = User.builder()
                    .id(resultSet.getLong("id"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .email(resultSet.getString("email"))
                    .role(new Role(resultSet.getLong("role_id"),
                            resultSet.getString("name")))
                    .build();
            users.add(user);
        }
        return users;
    }
}

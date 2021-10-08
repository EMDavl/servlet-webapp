package ru.itis.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInRepositoryImpl implements SignInRepository {
    private Connection connection;

    //language = SQL
    private String SQL_FIND_PASSWORD_BY_EMAIL =
            "SELECT * FROM users WHERE LOWER(email)=LOWER(?)";
    public SignInRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String findPasswordByEmail(String email) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(SQL_FIND_PASSWORD_BY_EMAIL);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next() ?
                    resultSet.getString("hash_pass") : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

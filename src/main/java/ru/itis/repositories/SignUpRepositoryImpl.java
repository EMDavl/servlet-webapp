package ru.itis.repositories;

import java.sql.*;

public class SignUpRepositoryImpl implements SignUpRepository{
    private Connection connection;

    //language = SQL
    private String SQL_IS_UNIQUE_VALUES = "SELECT * FROM users WHERE LOWER(email)=LOWER(?)";
    //language = SQL
    private String SQL_CREATE_USER = "INSERT INTO users(email, name, surname, hash_pass) VALUES(?, ?, ?, ?)";


    public SignUpRepositoryImpl(Connection connection) {
        this.connection = connection;
    }



    @Override
    public boolean isUniqueEmail(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_IS_UNIQUE_VALUES);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getString("email").isEmpty();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void signUp(String email, String name, String surname, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER);
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, password);

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}

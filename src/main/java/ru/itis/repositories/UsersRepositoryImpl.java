package ru.itis.repositories;

import java.sql.Connection;

public class UsersRepositoryImpl implements UsersRepository {
    private Connection connection;
    public UsersRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
}

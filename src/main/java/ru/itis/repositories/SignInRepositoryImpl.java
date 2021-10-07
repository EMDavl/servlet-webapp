package ru.itis.repositories;

import java.sql.Connection;

public class SignInRepositoryImpl implements SignInRepository {
    private Connection connection;
    public SignInRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
}

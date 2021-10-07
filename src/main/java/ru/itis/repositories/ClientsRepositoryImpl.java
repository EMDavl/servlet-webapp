package ru.itis.repositories;

import java.sql.Connection;

public class ClientsRepositoryImpl implements ClientsRepository {

    private Connection connection;

    public ClientsRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
}

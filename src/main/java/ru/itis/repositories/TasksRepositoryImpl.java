package ru.itis.repositories;

import java.sql.Connection;

public class TasksRepositoryImpl implements TasksRepository {
    private Connection connection;
    public TasksRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
}

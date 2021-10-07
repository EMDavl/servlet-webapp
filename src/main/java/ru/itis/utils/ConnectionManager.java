package ru.itis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ConnectionManager {

    private static List<Connection> connections;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    PropertiesParser.getProperty("db.url"),
                    PropertiesParser.getProperty("db.user"),
                    PropertiesParser.getProperty("db.password"));
            connections.add(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnections() {
        for (Connection connection : connections) {
            try {
                if (!connection.isClosed()) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

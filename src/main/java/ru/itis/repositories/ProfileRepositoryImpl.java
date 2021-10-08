package ru.itis.repositories;

import ru.itis.models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProfileRepositoryImpl implements ProfileRepository {
    private Connection connection;
    public ProfileRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    //language = SQL
    private String SQL_FIND_BY_EMAIL =
            "SELECT \n" +
                    "    \"email\",\n" +
                    "    \"name\",\n" +
                    "    \"surname\",\n" +
                    "    (SELECT \n" +
                    "            COUNT(id_c) \n" +
                    "    FROM users_clients \n" +
                    "        INNER JOIN users u \n" +
                    "            on users_clients.id_u = u.id \n" +
                    "    WHERE LOWER(u.email) = LOWER(?)) as clients_count,\n" +
                    "    (SELECT \n" +
                    "            COUNT(id_t) \n" +
                    "    FROM users_tasks \n" +
                    "        INNER JOIN users u \n" +
                    "            on users_tasks.id_u = u.id \n" +
                    "    WHERE LOWER(u.email) = LOWER(?)) as tasks_count\n" +
                    "FROM users\n" +
                    "    WHERE LOWER(email) = LOWER(?);";

    ProceedResultSet proceedSet = (resultSet) -> {

        UserModel model = new UserModel();
        model.setEmail(resultSet.getString("email"));
        model.setFullName(
                resultSet.getString("name") +
                        " " +
                        resultSet.getString("surname"));
        model.setClientsNow(resultSet.getInt("clients_count"));
        model.setTasksNow(resultSet.getInt("tasks_count"));
        return model;

    };

    @Override
    public UserModel findByEmail(String email) {
        UserModel model = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL);

            statement.setString(1, email);
            statement.setString(2, email);
            statement.setString(3, email);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                model = proceedSet.proceed(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
}

@FunctionalInterface
interface ProceedResultSet{
    UserModel proceed(ResultSet set) throws SQLException;
}

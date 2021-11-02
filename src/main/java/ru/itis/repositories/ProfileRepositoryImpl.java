package ru.itis.repositories;

import ru.itis.models.SimpleUserModel;
import ru.itis.models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepositoryImpl implements ProfileRepository {
    private Connection connection;

    public ProfileRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    //language = SQL
    private String SQL_IS_UNIQUE_EMAIL =
            "SELECT * FROM users WHERE LOWER(email)=LOWER(?)";

    //language = SQL
    private String SQL_FIND_PASSWORD_BY_EMAIL =
            "SELECT * FROM users WHERE LOWER(email)=LOWER(?)";

    //language = SQL
    private String SQL_CREATE_USER =
            "INSERT INTO users(email, name, surname, hash_pass) VALUES(?, ?, ?, ?)";

    //language = SQL
    private String SQL_UPDATE_PASSWORD =
            "UPDATE users SET hash_pass = ? WHERE email = ?";

    //language = SQL
    private String SQL_UPDATE_EMAIL =
            "UPDATE users SET email=? WHERE email=?";

    //language = SQL
    private String SQL_FIND_ALL_EXCEPT_ALREADY_ADDED =
            "SELECT \n" +
                    "       id,\n" +
                    "       email,\n" +
                    "       name,\n" +
                    "       surname \n" +
                    "FROM \n" +
                    "     users \n" +
                    "WHERE \n" +
                    "      id \n" +
                    "          NOT IN \n" +
                    "      (SELECT \n" +
                    "              id \n" +
                    "      FROM \n" +
                    "           users \n" +
                    "               JOIN users_clients uc \n" +
                    "                   on users.id = uc.id_u \n" +
                    "      WHERE id_c = ?)";

    //language = SQL
    private String SQL_FIND_ID_BY_EMAIL =
            "SELECT id FROM users WHERE email=?";

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

    @Override
    public boolean isUniqueEmail(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_IS_UNIQUE_EMAIL);
            statement.setString(1, email);

            ResultSet emailResultSet = statement.executeQuery();

            return !emailResultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmail(String newEmail, String oldEmail) {
        try{
            PreparedStatement statement =
                    connection.prepareStatement(SQL_UPDATE_EMAIL);
            statement.setString(1, newEmail);
            statement.setString(2, oldEmail);

            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public int findIdByEmail(String email) {
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ID_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getInt("id");

        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void updatePassword(String password, String email) {
        try{
            PreparedStatement statement =
                    connection.prepareStatement(SQL_UPDATE_PASSWORD);

            statement.setString(1, password);
            statement.setString(2, email);

            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<SimpleUserModel> findAllExceptAlreadyAddedToClient(int cid) {
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_EXCEPT_ALREADY_ADDED);
            statement.setInt(1, cid);
            ResultSet resultSet = statement.executeQuery();
            List<SimpleUserModel> users = new ArrayList<>();

            while(resultSet.next()){
                SimpleUserModel model = new SimpleUserModel();
                model.setEmail(resultSet.getString("email"));
                model.setName(resultSet.getString("name"));
                model.setSurname(resultSet.getString("surname"));
                model.setId(resultSet.getInt("id"));
                users.add(model);
            }

            return users;
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
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

@FunctionalInterface
interface ProceedResultSet{
    UserModel proceed(ResultSet set) throws SQLException;
}

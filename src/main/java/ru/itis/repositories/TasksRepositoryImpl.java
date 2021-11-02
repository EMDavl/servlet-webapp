package ru.itis.repositories;

import ru.itis.models.TaskModel;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TasksRepositoryImpl implements TasksRepository {
    private Connection connection;
    public TasksRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    //language = SQL
    private String SQL_FIND_TASKS_BY_EMAIL =
            "SELECT\n" +
                    "       task_name,\n" +
                    "       description,\n" +
                    "       date_time,\n" +
                    "       (SELECT COUNT(id_u) FROM users_tasks WHERE id_t = tasks.id) as members \n" +
                    "FROM\n" +
                    "     tasks\n" +
                    "         INNER JOIN users_tasks ut on (tasks.id = ut.id_t and ut.id_u = ?) \n" +
                    "WHERE date_time > now()";

    //language = SQL
    private String SQL_CREATE_TASK =
            "INSERT INTO tasks(task_name, description, date_time, creation_time) VALUES (?, ?, ?, ?) RETURNING id";

    private String SQL_CONNECT_USER_CREATOR_WITH_TASK =
            "INSERT INTO users_tasks(id_u, id_t, status) VALUES (?, ?, 'creator')";

    //language=SQL
    private String SQL_REMOVE_OUTDATED_TASKS =
            "DELETE FROM tasks WHERE date_time < now()";

    //language=SQL
    private String SQL_REMOVE_CONNECTED_USERS =
            "DELETE FROM users_tasks WHERE id_t IN (SELECT id FROM tasks WHERE date_time < now())";

    @Override
    public List<TaskModel> findAllTasksById(int id) {
        List<TaskModel> tasks = new ArrayList<>();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(SQL_FIND_TASKS_BY_EMAIL);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                tasks.add(proceedResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public void addTask(String taskName, String desc, Timestamp timestamp, int id) {
        Timestamp creationTime = Timestamp.valueOf(LocalDateTime.now());
        int id_t = createTask(taskName, desc, timestamp, creationTime);
        connectWithUser(id_t, id);
    }

    @Override
    public void removeOutdatedTasks() {
        try {
            removeConnectedUsers();
            removeTasks();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void removeTasks() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(SQL_REMOVE_OUTDATED_TASKS);
    }

    private void removeConnectedUsers() throws
            SQLException{
        Statement statement = connection.createStatement();
        statement.execute(SQL_REMOVE_CONNECTED_USERS);
    }

    private void connectWithUser(int taskId, int userId) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(SQL_CONNECT_USER_CREATOR_WITH_TASK);

            statement.setInt(1, userId);
            statement.setInt(2, taskId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int createTask(String taskName,
                            String desc,
                            Timestamp timestamp,
                            Timestamp creationTime) {

        int taskId = -1;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TASK);
            statement.setString(1, taskName);
            statement.setString(2, desc);
            statement.setTimestamp(3, timestamp);
            statement.setTimestamp(4, creationTime);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            taskId = resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskId;
    }

    private TaskModel proceedResultSet(ResultSet resultSet){
        TaskModel model = new TaskModel();
        try {
            model.setTaskName(resultSet.getString("task_name"));
            model.setDescription(resultSet.getString("description"));
            model.setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
}

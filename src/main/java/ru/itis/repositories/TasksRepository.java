package ru.itis.repositories;

import ru.itis.models.TaskModel;

import java.sql.Timestamp;
import java.util.List;

public interface TasksRepository {
    List<TaskModel> findAllTasksById(int id);

    void addTask(String taskName, String desc, Timestamp timestamp, int id);
    void removeOutdatedTasks();
}

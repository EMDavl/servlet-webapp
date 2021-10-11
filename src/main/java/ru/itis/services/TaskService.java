package ru.itis.services;

import ru.itis.models.TaskModel;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TaskService {

    List<TaskModel> getTasks(HttpSession session);

    void addTask(String taskName, String desc, String date, String time, HttpSession session);
    void removeOutdatedTasks();
}

package ru.itis.services;

import ru.itis.models.TaskModel;
import ru.itis.repositories.TasksRepository;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TasksRepository repository;

    public TaskServiceImpl(TasksRepository tasksRepo) {
        repository = tasksRepo;
    }

    @Override
    public List<TaskModel> getTasks(HttpSession session) {
        int id = (Integer) session.getAttribute("id");
        List<TaskModel> tasks = repository.findAllTasksById(id);
        return tasks;
    }

    @Override
    public void addTask(String taskName, String desc, String date, String time, HttpSession session) {
        Timestamp timestamp = Timestamp.valueOf(date + " " + time + ":00");
        int id = (int) session.getAttribute("id");

        repository.addTask(taskName, desc, timestamp, id);
    }

    @Override
    public void removeOutdatedTasks() {
        repository.removeOutdatedTasks();
    }
}

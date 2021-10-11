package ru.itis.servlets;

import ru.itis.models.TaskModel;
import ru.itis.repositories.TasksRepository;
import ru.itis.services.TaskService;
import ru.itis.services.TaskServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TasksServlet extends HttpServlet {
    private TaskService taskService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        taskService = (TaskService) context.getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<TaskModel> tasks = taskService.getTasks(req.getSession());
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/views/jsp/tasks.jsp").forward(req, resp);

    }
}

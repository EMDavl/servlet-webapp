package ru.itis.servlets;

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

@WebServlet("/add-task")
public class AddTaskServlet extends HttpServlet {

    private TaskService taskService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        taskService = (TaskService) context.getAttribute("taskService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskName = req.getParameter("taskName");
        String desc = req.getParameter("taskDescription");
        String date = req.getParameter("taskDate"); //yyyy-mm-dd
        String time = req.getParameter("taskTime"); //hh:mm

        taskService.addTask(taskName, desc, date, time, req.getSession());
        resp.sendRedirect("/tasks");
    }
}

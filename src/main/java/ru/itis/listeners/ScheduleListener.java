package ru.itis.listeners;

import ru.itis.services.TaskService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleListener implements ServletContextListener {
    private ScheduledExecutorService executorService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        executorService = Executors.newSingleThreadScheduledExecutor();
        TaskService taskService = (TaskService) sce.getServletContext().getAttribute("taskService");
        executorService.scheduleAtFixedRate(taskService::removeOutdatedTasks, 0, 4, TimeUnit.HOURS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        executorService.shutdown();
    }
}

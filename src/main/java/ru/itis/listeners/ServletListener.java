package ru.itis.listeners;

import ru.itis.repositories.*;
import ru.itis.utils.ConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("signUpRepo",
                new SignUpRepositoryImpl(ConnectionManager.getConnection()));

        servletContext.setAttribute("userRepo",
                new UsersRepositoryImpl(ConnectionManager.getConnection()));

        servletContext.setAttribute("tasksRepo",
                new TasksRepositoryImpl(ConnectionManager.getConnection()));

        servletContext.setAttribute("signInRepo",
                new SignInRepositoryImpl(ConnectionManager.getConnection()));

        servletContext.setAttribute("clientsRepo",
                new ClientsRepositoryImpl(ConnectionManager.getConnection()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionManager.closeConnections();
    }

}

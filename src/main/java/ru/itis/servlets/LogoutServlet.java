package ru.itis.servlets;

import ru.itis.services.LogoutService;
import ru.itis.services.LogoutServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private LogoutService logoutService;

    @Override
    public void init() throws ServletException {
        logoutService = new LogoutServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logoutService.logout(req.getSession());
        resp.sendRedirect("/");

    }
}

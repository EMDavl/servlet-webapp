package ru.itis.servlets;

import ru.itis.models.UserModel;
import ru.itis.services.ProfileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private ProfileService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        service = (ProfileService) context.getAttribute("profileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        UserModel model = service.getUserModel(req.getSession());
        req.setAttribute("user", model);
        req.getRequestDispatcher("/WEB-INF/views/jsp/profile.jsp").forward(req, resp);
    }
}

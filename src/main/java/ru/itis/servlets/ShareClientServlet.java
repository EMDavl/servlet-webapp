package ru.itis.servlets;

import ru.itis.models.SimpleUserModel;
import ru.itis.models.UserModel;
import ru.itis.services.ClientService;
import ru.itis.services.ProfileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/shareClient")
public class ShareClientServlet extends HttpServlet {
    private ClientService clientService;
    private ProfileService profileService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer cid = Integer.parseInt(req.getParameter("cid"));
        List<SimpleUserModel> users = profileService.getAllUsersExceptAlreadyAddedToClient(cid);
        if(users.isEmpty()){
            resp.sendRedirect("/clients");
            return;
        }
        req.setAttribute("clientId", cid);
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/jsp/shareClient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashSet<String> parameters = new HashSet<>(req.getParameterMap().keySet());
        int cid = Integer.parseInt(req.getParameter("cid"));
        parameters.remove("cid");
        clientService.connectUsers(parameters, cid);
        resp.sendRedirect("/clients");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.clientService = (ClientService) servletContext.getAttribute("clientService");
        this.profileService = (ProfileService) servletContext.getAttribute("profileService");
    }
}

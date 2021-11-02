package ru.itis.servlets;

import ru.itis.services.ClientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/clients/delete-client")
public class DeleteClientServlet extends HttpServlet {
    private ClientService clientService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.clientService = (ClientService) servletContext.getAttribute("clientService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("id");
        String clientId = req.getParameter("cid");

        if(clientService.isClientIdValid(clientId) && clientService.isClientConnectedWithUser(clientId, userId)){
            clientService.deleteClient(Integer.parseInt(clientId));
        }

        resp.sendRedirect("/clients");
    }
}

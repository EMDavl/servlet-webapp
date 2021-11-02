package ru.itis.servlets;

import ru.itis.forms.ClientForm;
import ru.itis.models.ClientModel;
import ru.itis.services.ClientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/clients")
public class ClientsListServlet extends HttpServlet {

    private ClientService clientService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (int) req.getSession().getAttribute("id");
        List<ClientModel> clients = clientService.getClientsList(userId);
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/WEB-INF/views/jsp/clients.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientForm clientForm = clientService.getFormFromRequest(req);
        int creatorId = (int) req.getSession().getAttribute("id");
        clientService.saveClient(clientForm, creatorId);
        resp.sendRedirect("/clients");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.clientService = (ClientService) servletContext.getAttribute("clientService");
    }
}

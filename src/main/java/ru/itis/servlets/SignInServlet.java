package ru.itis.servlets;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.forms.SignInForm;
import ru.itis.repositories.ProfileRepository;
import ru.itis.services.SignInService;
import ru.itis.services.SignInServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private SignInService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        service = new SignInServiceImpl(
                (ProfileRepository) context.getAttribute("profileRepo"),
                (PasswordEncoder) context.getAttribute("passwordEncoder")
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/jsp/sign_in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInForm form = new SignInForm(req.getParameter("email"),
                req.getParameter("password"));

        if(service.signIn(form, req.getSession())){
            resp.sendRedirect("/profile");
            return;
        }

        req.setAttribute("error", true);
        req.getRequestDispatcher("/views/jsp/sign_in.jsp").forward(req, resp);
    }
}

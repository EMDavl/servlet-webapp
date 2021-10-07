package ru.itis.servlets;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.forms.SignUpForm;
import ru.itis.repositories.SignUpRepository;
import ru.itis.services.SignUpService;
import ru.itis.services.SignUpServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.signUpService = new SignUpServiceImpl(
                (SignUpRepository) context.getAttribute("signUpRepo"),
                (PasswordEncoder) context.getAttribute("passwordEncoder"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/jsp/sign_up.jsp").forward(req, resp);
    }

    // Мб вынести создание формы в отдельный метод или в класс формы
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        SignUpForm form = new SignUpForm(
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("email"),
                req.getParameter("password")
        );

        if (signUpService.signUp(form)){
            resp.sendRedirect("/sign-in");
            return;
        }

        req.setAttribute("error", true);
        req.getRequestDispatcher("/views/jsp/sign_up.jsp").forward(req, resp);
    }
}

package ru.itis.servlets;

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
                (SignUpRepository) context.getAttribute("signUpRepo"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/jsp/sign_up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm form = SignUpForm.getFormFromRequest(req);
        if (signUpService.isValidEmail(form.getEmail())){
            signUpService.signUp(form);
            resp.sendRedirect("/semwork/sign-in");
            return;
        }

        resp.sendRedirect("/semwork/sign-up");
    }
}

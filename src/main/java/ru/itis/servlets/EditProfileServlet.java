package ru.itis.servlets;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.ProfileRepository;
import ru.itis.services.ProfileEditService;
import ru.itis.services.ProfileEditServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-profile")
public class EditProfileServlet extends HttpServlet {

    ProfileEditService profileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        profileService = (ProfileEditService) context.getAttribute("profileEditService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/jsp/edit_profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String newEmail = req.getParameter("email");
        String oldPass = req.getParameter("oldPass");
        String newPass = req.getParameter("newPass");
        boolean emailErr = false;
        boolean passwordErr = false;

        if (!profileService.editEmail(newEmail, req.getSession())) {
            req.setAttribute("emailError", true);
            emailErr = true;
        }

        if (!profileService.editPassword(oldPass, newPass, req.getSession())) {
            req.setAttribute("passwordError", true);
            passwordErr = true;
        }

        if (emailErr || passwordErr) {
            req.getRequestDispatcher("/views/jsp/edit_profile.jsp").forward(req, resp);
        }

        resp.sendRedirect("/profile");
    }
}

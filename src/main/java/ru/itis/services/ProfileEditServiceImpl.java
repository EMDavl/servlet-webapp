package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.ProfileRepository;

import javax.servlet.http.HttpSession;

public class ProfileEditServiceImpl implements ProfileEditService {

    private PasswordEncoder encoder;
    private ProfileRepository repository;

    public ProfileEditServiceImpl(PasswordEncoder encoder,
                                  ProfileRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @Override
    public boolean editEmail(String newEmail, HttpSession session) {
        String oldEmail = (String) session.getAttribute("email");
        if (oldEmail.equals(newEmail)) return true;
        if(!repository.isUniqueEmail(newEmail)) return false;

        repository.updateEmail(newEmail, oldEmail);
        session.removeAttribute("email");
        session.setAttribute("email", newEmail);

        return true;
    }

    @Override
    public boolean editPassword(String oldPassword,
                                String newPassword,
                                HttpSession session) {
        String email = (String) session.getAttribute("email");

        if(!encoder.matches(oldPassword,
                repository.findPasswordByEmail(email))){
            return false;
        }

        if(oldPassword.equals(newPassword)) return true;

        repository.updatePassword(encoder.encode(newPassword), email);
        return true;
    }
}

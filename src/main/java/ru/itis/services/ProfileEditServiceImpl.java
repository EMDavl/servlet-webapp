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
    public boolean processEmail(String newEmail, HttpSession session) {
        String oldEmail = (String) session.getAttribute("email");

        if (oldEmail.equals(newEmail)) return true;
        if(!repository.isUniqueEmail(newEmail)) return false;

        repository.updateEmail(newEmail, oldEmail);
        session.setAttribute("email", newEmail);

        return true;
    }

    @Override
    public boolean processPassword(String oldPassword,
                                   String newPassword,
                                   HttpSession session) {
        String email = (String) session.getAttribute("email");

        if(oldPassword.equals(newPassword)) return true;

        if(!encoder.matches(oldPassword,
                repository.findPasswordByEmail(email))){
            return false;
        }

        repository.updatePassword(encoder.encode(newPassword), email);
        return true;
    }
}

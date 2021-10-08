package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.forms.SignInForm;
import ru.itis.repositories.SignInRepository;

import javax.servlet.http.HttpSession;

public class SignInServiceImpl implements SignInService {

    private SignInRepository repository;
    private PasswordEncoder passwordEncoder;

    public SignInServiceImpl(SignInRepository repository,
                             PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isPasswordSame(String email, String password) {
        String passwordByEmail = repository.findPasswordByEmail(email);
        return passwordByEmail != null && passwordEncoder.matches(password, passwordByEmail);
    }

    @Override
    public boolean signIn(SignInForm form, HttpSession session) {

        if(!isPasswordSame(form.getEmail(), form.getPassword())){
            return false;
        }

        session.setAttribute("email", form.getEmail());
        session.setMaxInactiveInterval(30*60);

        return true;
    }
}

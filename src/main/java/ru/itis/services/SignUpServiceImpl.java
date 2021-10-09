package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.forms.SignUpForm;
import ru.itis.repositories.ProfileRepository;


public class SignUpServiceImpl implements SignUpService {
    private ProfileRepository repository;
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(ProfileRepository repository,
                             PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isValidEmail(String email) {
        return repository.isUniqueEmail(email);
    }

    @Override
    public boolean signUp(SignUpForm form) {
        if(!isValidEmail(form.getEmail())) return false;
        repository.signUp(form.getEmail(),
                form.getName(),
                form.getSurname(),
                passwordEncoder.encode(form.getPassword()));
        return true;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}

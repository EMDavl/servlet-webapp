package ru.itis.services;

import ru.itis.forms.SignUpForm;
import ru.itis.repositories.SignUpRepository;
import ru.itis.repositories.SignUpRepositoryImpl;

public class SignUpServiceImpl implements SignUpService {
    private SignUpRepository repository;

    public SignUpServiceImpl(SignUpRepository repository) {
        this.repository = repository;
    }

    public SignUpRepository getRepository() {
        return repository;
    }

    public void setRepository(SignUpRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValidEmail(String email) {
        return repository.isUniqueEmail(email);
    }

    @Override
    public void signUp(SignUpForm form) {
        repository.signUp(form.getEmail(), form.getName(), form.getSurname(), form.getPassword());
    }
}

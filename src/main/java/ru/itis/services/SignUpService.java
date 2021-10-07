package ru.itis.services;

import ru.itis.forms.SignUpForm;

import javax.xml.ws.Service;

public interface SignUpService {
    boolean isValidEmail(String email);
    void signUp(SignUpForm form);
}

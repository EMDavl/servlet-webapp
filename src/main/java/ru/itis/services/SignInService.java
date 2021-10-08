package ru.itis.services;

import ru.itis.forms.SignInForm;

import javax.servlet.http.HttpSession;

public interface SignInService {
    boolean isPasswordSame(String email,
                           String password);

    boolean signIn(SignInForm form, HttpSession session);
}

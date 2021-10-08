package ru.itis.services;

import javax.servlet.http.HttpSession;

public interface LogoutService {
    void logout(HttpSession session);
}

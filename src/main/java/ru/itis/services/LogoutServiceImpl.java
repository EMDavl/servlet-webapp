package ru.itis.services;

import javax.servlet.http.HttpSession;

public class LogoutServiceImpl implements LogoutService {

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }
}

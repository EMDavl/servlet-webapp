package ru.itis.services;


import javax.servlet.http.HttpSession;

public interface ProfileEditService {
    boolean processEmail(String newEmail, HttpSession session);
    boolean processPassword(String oldPassword,
                            String newPassword,
                            HttpSession session);
}

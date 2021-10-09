package ru.itis.services;


import javax.servlet.http.HttpSession;

public interface ProfileEditService {
    boolean editEmail(String newEmail, HttpSession session);
    boolean editPassword(String oldPassword,
                         String newPassword,
                         HttpSession session);
}

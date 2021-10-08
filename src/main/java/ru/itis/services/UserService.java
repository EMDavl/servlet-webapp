package ru.itis.services;

import ru.itis.models.UserModel;

import javax.servlet.http.HttpSession;

public interface UserService {

    UserModel getUserModel(HttpSession session);

    boolean editUser(UserModel model);

    boolean changePassword(String newPass);
}

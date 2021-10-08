package ru.itis.services;

import ru.itis.models.UserModel;

import javax.servlet.http.HttpSession;

public interface ProfileService {

    UserModel getUserModel(HttpSession session);

}

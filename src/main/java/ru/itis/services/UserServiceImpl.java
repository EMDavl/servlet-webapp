package ru.itis.services;

import ru.itis.models.UserModel;
import ru.itis.repositories.UsersRepository;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserModel getUserModel(HttpSession session) {
        return null;
    }

    @Override
    public boolean editUser(UserModel model) {
        return false;
    }

    @Override
    public boolean changePassword(String newPass) {
        return false;
    }
}

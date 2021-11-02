package ru.itis.services;

import ru.itis.models.SimpleUserModel;
import ru.itis.models.UserModel;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProfileService {

    UserModel getUserModel(HttpSession session);

    List<SimpleUserModel> getAllUsersExceptAlreadyAddedToClient(int cid);

}

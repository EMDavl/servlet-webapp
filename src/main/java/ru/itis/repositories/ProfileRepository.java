package ru.itis.repositories;

import ru.itis.models.SimpleUserModel;
import ru.itis.models.UserModel;

import java.util.List;

public interface ProfileRepository {

    UserModel findByEmail(String email);
    String findPasswordByEmail(String email);
    boolean isUniqueEmail(String email);
    void signUp(String email, String name, String surname, String password);
    void updateEmail(String newEmail, String oldEmail);
    int findIdByEmail(String email);
    void updatePassword(String encode, String email);

    List<SimpleUserModel> findAllExceptAlreadyAddedToClient(int cid);
}

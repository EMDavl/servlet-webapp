package ru.itis.repositories;

import ru.itis.models.UserModel;

public interface ProfileRepository {

    UserModel findByEmail(String email);
    String findPasswordByEmail(String email);
    boolean isUniqueEmail(String email);
    void signUp(String email, String name, String surname, String password);
    void updateEmail(String newEmail, String oldEmail);

    void updatePassword(String encode, String email);
}

package ru.itis.repositories;

import ru.itis.models.UserModel;

public interface ProfileRepository {
    UserModel findByEmail(String email);
}

package ru.itis.repositories;

public interface SignInRepository {
    String findPasswordByEmail(String email);
}

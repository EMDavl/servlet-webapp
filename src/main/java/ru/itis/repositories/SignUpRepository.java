package ru.itis.repositories;

public interface SignUpRepository{

    boolean isUniqueEmail(String email);
    void signUp(String email, String name, String surname, String password);

}

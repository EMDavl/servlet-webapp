package ru.itis.models;

import java.time.LocalDate;

public class ClientModel {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String mobileNum;
    private String description;
    private LocalDate birthDate;

    public ClientModel(int id, String email, String firstName, String lastName, String mobileNum, String description, LocalDate birthDate) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNum = mobileNum;
        this.description = description;
        this.birthDate = birthDate;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}

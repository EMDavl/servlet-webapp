package ru.itis.forms;

import java.time.LocalDate;

public class ClientForm {


    private String name;
    private String surname;
    private String email;
    private String mobileNum;
    private LocalDate birthDate;
    private String description;

    public ClientForm() {
    }

    public ClientForm(String name, String surname, String email, String mobileNum, LocalDate birthDate, String description) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.mobileNum = mobileNum;
        this.birthDate = birthDate;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

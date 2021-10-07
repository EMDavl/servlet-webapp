package ru.itis.models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String fullName;
    private String email;
    private int clientsNow;
    private int tasksNow;

    public UserModel(){
    }

    public UserModel(String name, String surname, String email, int clientsNow, int tasksNow) {
        this.fullName = name + " " + surname;
        this.email = email;
        this.clientsNow = clientsNow;
        this.tasksNow = tasksNow;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClientsNow() {
        return clientsNow;
    }

    public void setClientsNow(int clientsNow) {
        this.clientsNow = clientsNow;
    }

    public int getTasksNow() {
        return tasksNow;
    }

    public void setTasksNow(int tasksNow) {
        this.tasksNow = tasksNow;
    }
}

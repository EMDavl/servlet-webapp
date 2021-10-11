package ru.itis.models;

import java.time.LocalDateTime;

public class TaskModel {

    private String taskName;
    private String description;
    private LocalDateTime dateTime;
    private int membersCount;



    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public String getFormattedDateTime(){
        return dateTime.toString().replace('T', ' ');
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }
}

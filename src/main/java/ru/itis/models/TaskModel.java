package ru.itis.models;

import java.time.LocalDateTime;

public class TaskModel {

    private String taskName;
    private String description;
    private LocalDateTime dateTime;



    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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

}

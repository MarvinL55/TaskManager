package com.marvin.taskmanager;


import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="tasks")
public class Task {

    @Id
    private String id;

    private String title;
    private String description;
    private boolean completed;

    // Constructors

    // Getter and setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted(){
        return completed;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }
}

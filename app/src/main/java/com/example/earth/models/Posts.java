package com.example.earth.models;

public class Posts {
    public String id;
    public String description;
    public String email;
    public  String url;


    public Posts() {
    }

    public Posts(String id, String description, String email, String url) {
        this.id = id;
        this.description = description;
        this.email = email;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}

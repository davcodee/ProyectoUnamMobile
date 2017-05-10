package com.example.deyvi.proyectounammobile.pojo;

/**
 * Created by deyvi on 07/04/2017.
 */

public class User {
    String user;
    String content_title;
    String content_texto;
    String image;

    public User(String user, String content_title, String content_texto, String image) {
        this.user = user;
        this.content_title = content_title;
        this.content_texto = content_texto;
        this.image = image;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public String getContent_texto() {
        return content_texto;
    }

    public void setContent_texto(String content_texto) {
        this.content_texto = content_texto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

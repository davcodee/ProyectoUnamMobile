package com.example.deyvi.proyectounammobile.pojo;

/**
 * Created by deyvi on 22/04/2017.
 */

public class Store {
    String name;
    String image;
    double lat;
    double lon;

    public Store(String name, String image, double lat, double lon) {
        this.name = name;
        this.image = image;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}

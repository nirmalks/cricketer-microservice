package com.example.demo.model;

public class Cricketer {
    String id;
    String name;
    String country;
    int highestScore;

    public Cricketer(String id, String name, String country, int highestScore) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.highestScore = highestScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }
}

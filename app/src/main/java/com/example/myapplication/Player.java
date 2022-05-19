package com.example.myapplication;

public class Player {

    // variables for our course
    // name and description.
    private String id;
    private String name;
    private String speed;




    // creating constructor for our variables.
    public Player(String name, String speed, String id) {
        this.name = name;
        this.speed = speed;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeed() {
        return this.speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }


}

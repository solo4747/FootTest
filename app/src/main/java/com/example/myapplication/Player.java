package com.example.myapplication;

public class Player {

    // variables for our course
    // name and description.
    private int id;
    private String name;


    // creating constructor for our variables.
    public Player(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return this.id;
    }

    public void setCId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

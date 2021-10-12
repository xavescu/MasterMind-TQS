package org.example.model;

public class Player {
    private int role;
    private int points;

    public Player(int role){
        this.role = role;
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public int getRole() {
        return role;
    }

    public void changeRole() {

    }

    public void addPoints(int points){

    }
}

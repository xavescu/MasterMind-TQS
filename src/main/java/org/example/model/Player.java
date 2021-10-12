package org.example.model;

import static org.example.model.enums.Roles.CODE_BREAKER;
import static org.example.model.enums.Roles.CODE_MAKER;

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
        if(getRole() == CODE_MAKER){
            this.role = CODE_BREAKER;
        } else {
            this.role = CODE_MAKER;
        }
    }

    public void addPoints(int points){
        this.points += points;
    }
}

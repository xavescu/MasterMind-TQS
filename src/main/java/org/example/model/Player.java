package org.example.model;

import static org.example.model.constants.Roles.CODE_BREAKER;
import static org.example.model.constants.Roles.CODE_MAKER;

public class Player {
    private int role;
    private int points;
    private boolean isHuman;

    public Player(int role, boolean isHuman){
        this.role = role;
        this.points = 0;
        this.isHuman = isHuman;
    }

    public int getPoints() {
        return points;
    }

    public boolean isHuman() {
        return isHuman;
    }
    public void setHuman(boolean newValue){
        isHuman = newValue;
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

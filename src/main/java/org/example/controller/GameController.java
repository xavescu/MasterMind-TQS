package org.example.controller;

import org.example.model.GameModel;
import org.example.view.GameView;

public class GameController {
    private GameModel model;
    private GameView view;
    private int turn = 0;

    public GameController(GameView view, GameModel model){
        this.view = view;
        this.model = model;
    }

    public boolean isPlaying(){
        return true;
    }

    private void updateView(){

    }
}

package org.example.controller;

import org.example.model.GameModel;
import org.example.view.GameView;

public class GameController {
    private GameModel model = new GameModel();
    private GameView view = new GameView();
    private int turn = 0;

    public boolean isPlaying(){
        return true;
    }

    private void updateView(){

    }
}

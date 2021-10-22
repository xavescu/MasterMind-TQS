package org.example.controller;

import org.example.model.GameModel;
import org.example.view.GameView;

import java.io.IOException;
import java.io.InputStream;

public class GameController {
    private GameModel model;
    private GameView view;
    private int turn = 0;
    private int screen = 0;

    public GameController(GameView view, GameModel model){
        this.view = view;
        this.model = model;
    }

    public boolean isPlaying(){
        return true;
    }

    public int listenKeyBoard(){
        InputStream st = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        System.setIn(st);
        return 0;
    }

    public void updateView(){

    }
}

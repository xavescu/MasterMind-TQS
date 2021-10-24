package org.example;

import org.example.controller.GameController;
import org.example.model.GameModel;
import org.example.view.GameView;

public class App
{
    public static void main( String[] args )
    {
        GameView view = new GameView();
        GameModel model = new GameModel();
        GameController controller = new GameController(view, model);

        while(controller.isPlaying()){
            controller.updateView();
            controller.getKeyBoardInput();
            controller.processGame();
        }
    }
}

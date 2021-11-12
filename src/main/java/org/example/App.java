package org.example;

import org.example.controller.GameController;
import org.example.model.GameModel;
import org.example.view.GameView;

import static org.example.controller.constants.Screens.BYEBYE_SCREEN;
import static org.example.controller.constants.Screens.GAME_OVER_SCREEN;

public class App
{
    public static void main( String[] args )
    {
        GameView view = new GameView();
        GameModel model = new GameModel();
        GameController controller = new GameController(view, model);

        while(controller.isPlaying()){
            controller.updateView();
            if(controller.getScreen()!=GAME_OVER_SCREEN && controller.getScreen()!=BYEBYE_SCREEN){
                controller.getKeyBoardInput();
            }
            controller.processGame();
        }
    }
}

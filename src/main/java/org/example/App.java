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
        int turn = 0;
        int cursor = 0;

        // Game loop
        while(controller.isPlaying()){
            controller.listenKeyBoard();
            //display view [Choose your game]
                //Cursor for the view?
                //Arrow up/down + Return?

            //if P1vsP2 -> display view [P2, Define your code]
            //if P1vsCPU -> display view [Define your proposal]

            //while !codeResolved -> display view [Define your proposal]
                //Validate proposal
                //Update View

            //If codeResolved -> displayView [CODE_BREAKER wins]
                //Add points to CODE_MAKER
                //Change Players roles
                //Update View
                //If round = 4 -> displayView[Game_Over]
                //Close App

            //If moreThan10Turns -> DisplayView [CODE_MAKER wins]
                //Add points to CODE_MAKER
                //Change Players roles
                //Update View
                //If round = 4 -> displayView[Game_Over]
                //Close App
        }
    }
}

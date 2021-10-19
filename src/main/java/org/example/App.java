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

        // Game loop
        while(controller.isPlaying()){
            controller.listenKeyBoard();
            //display view [Choose your game]

            //if P1vsP2 -> display view [P2, Define your code]

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

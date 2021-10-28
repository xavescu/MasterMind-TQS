package org.example.controller;

import org.example.model.GameModel;
import org.example.model.Row;
import org.example.view.GameView;

import java.util.Objects;
import java.util.Scanner;

import static org.example.controller.constants.GameModes.PLAYER_VS_CPU;
import static org.example.controller.constants.GameModes.PLAYER_VS_PLAYER;
import static org.example.controller.constants.Screens.*;

// Game loop
//while(controller.isPlaying()){
//We listen the keyboard to change the status of the game

//if first screen -> display view [Choose your game]
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
//}

public class GameController {
    private GameModel model;
    private GameView view;
    private int turn = 0;
    private int screen = 0;
    private String input = "";
    private boolean play = true;
    private int gameMode = 0;

    public GameController(GameView view, GameModel model){
        this.view = view;
        this.model = model;
    }

    public boolean isPlaying(){
        return this.play;
    }

    public int getScreen() {
        return screen;
    }

    public int getGameMode() {
        return gameMode;
    }

    public int getTurn() {
        return turn;
    }

    public Row getSecretCode(){
        return model.getSecretCode();
    }

    public void getKeyBoardInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nINPUT:");
        this.input = scanner.nextLine();
    }

    public Row getRow(int i){
        return model.getBoard().getRow(i);
    }

    public void updateView(){
        //Clear app console
        clearConsole();

        if(this.screen == WELCOME_SCREEN){
            view.printWelcomeView();
        }
        if(this.screen == CODE_BREAKER_SCREEN){
            view.printPlayersPoints();
            view.printCodeBreakerView();
        }
        if(this.screen == CODE_MAKER_SCREEN){
            view.printCodeMakerView();
        }
        if(this.screen == BYEBYE_SCREEN){
            view.printByeByeView();
        }
    }

    public void processGame(){
        if(this.screen == WELCOME_SCREEN){
            if(Objects.equals(input, "1")){ //Start 1 player mode
                gameMode = PLAYER_VS_CPU;
                screen = CODE_BREAKER_SCREEN;
                //Todo: Initialize Players? (P1, CPU)
                //Todo: CPU Initialize it's CODE
                model.getBoard().defineRandomSecretCode();
                //Todo: Logic of the CODE_BREAKER
            }
            if(Objects.equals(input, "2")){ //Start 2 players mode
                gameMode = PLAYER_VS_PLAYER;
                screen = CODE_MAKER_SCREEN;
                //Todo: Initialize Players? (P1, P2)
                //Todo: Logic of the CODE_MAKER
            }
            if(Objects.equals(input, "3")){ //End game
                screen = BYEBYE_SCREEN;
                this.play = false;
            }
        }
    }

    private static void clearConsole(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

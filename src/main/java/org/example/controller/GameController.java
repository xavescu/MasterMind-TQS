package org.example.controller;

import org.example.model.GameModel;
import org.example.view.GameView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

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

    public int getTurn() {
        return turn;
    }

    public void getKeyBoardInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your option?");
        this.input = scanner.nextLine();
    }

    public void updateView(){
        //Clear app console
        clearConsole();

        if(this.screen == 0){
            System.out.println("<*> MASTERMIND <*>");
            System.out.println("1) 1 Player");
            System.out.println("2) 2 Players");
            System.out.println("3) Exit");
        }
        if(this.screen == 1){
            System.out.println("Expose your proposal");
        }
    }

    public void processGame(){
        if(this.screen == 0){
            if(Objects.equals(input, "1")){ //Start 1 player mode
            }
            if(Objects.equals(input, "2")){ //Start 2 players mode
            }
            if(Objects.equals(input, "3")){ //End game
                this.play = false;
            }
        }
    }

    private static void clearConsole(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

package org.example.controller;

import org.example.model.Board;
import org.example.model.GameModel;
import org.example.model.Row;
import org.example.model.constants.Clues;
import org.example.model.constants.Colors;
import org.example.view.GameView;

import java.util.ArrayList;
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

    public boolean isCodeResolved(){
        boolean resolved = true;
        if(getRowsCount()!=0){
            for(int i=0; i<5;i++){
                if(getSecretCode().getColor(i)!=getRow(getRowsCount()-1).getColor(i)){
                    resolved = false;
                }
            }
        }else{
            resolved = false;
        }
        return resolved;
    }

    public boolean isAllAttemptsDone(){
        return (getRowsCount()==10);
    }

    public Row getRow(int i){
        return model.getBoard().getRow(i);
    }

    public int getRowsCount(){
        return model.getBoard().getRowsCount();
    }

    public void updateView(){
        //Clear app console
        clearConsole();

        if(this.screen == WELCOME_SCREEN){
            view.printWelcomeView();
        }
        if(this.screen == CODE_BREAKER_SCREEN){
            view.printPlayersPoints();
            if(isCodeResolved()){//SecretCode is breaked
                view.printSecretCodeResolved(getSecretCode());//print secret code
            }
            view.printCodeBreakerView(model.getBoard()); //Todo: Avoid the hardcoding approach, use the actual data from the board
            if(!isCodeResolved()&&isAllAttemptsDone()){
                view.printCodeNotResolved(getSecretCode());
            }
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
                //model.getBoard().defineRandomSecretCode();
                model.getBoard().defineManualSecretCode("WWWWW"); //Temp Code
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
        if(this.screen == CODE_BREAKER_SCREEN){
            if(!isCodeResolved()&&!isAllAttemptsDone()){
                if(isValidCodeProposal(input)){
                    processCodeProposal(input); //Add a new try row with the colors
                    addCluesToRow(getRowsCount()-1);//Add the clues for that row
                }
            }else{
                screen = CODE_MAKER_SCREEN;
            }

        }
    }

    public boolean isValidCodeProposal(String input){
        if(input.length() != 5){
            return false;
        }
        for(int i=0; i<5; i++){
            if((input.charAt(i) != ('W'))&&(input.charAt(i) != ('Y'))&&(input.charAt(i) != ('G'))&&(input.charAt(i) != ('B'))&&(input.charAt(i) != ('R'))){
                return false;
            }
        }
        return true;
    }

    public void processCodeProposal(String input){
        ArrayList<Integer> codeProposal = new ArrayList<>();
        Row row = new Row();
        for(int i=0; i<5; i++){
            if(input.charAt(i) == ('W')){
                codeProposal.add(Colors.WHITE);
            }
            if(input.charAt(i) == ('G')){
                codeProposal.add(Colors.GREEN);
            }
            if(input.charAt(i) == ('B')){
                codeProposal.add(Colors.BLUE);
            }
            if(input.charAt(i) == ('R')){
                codeProposal.add(Colors.RED);
            }
            if(input.charAt(i) == ('Y')){
                codeProposal.add(Colors.YELLOW);
            }
        }
        row.setCode(codeProposal);
        model.addRow(row);
    }

    public void addCluesToRow(int rowNumber){
        Row secretCode = model.getSecretCode();
        Row row = getRow(rowNumber);
        ArrayList<Integer> clues = new ArrayList<>();
        for(int i=0; i<row.getCodeLength(); i++){
            if(secretCode.getColor(i)==row.getColor(i)){
                clues.add(Clues.EXACT_POSITION);
            }else{
                for(int j=0; j<row.getCodeLength(); j++){
                    if(secretCode.getColor(j)==row.getColor(i)){
                        clues.add(Clues.WRONG_POSITION);
                        break;
                    }
                }
            }
        }
        if(clues.size()!=5){
            int tempCluesSize = 5-clues.size();
            for(int i=0; i<tempCluesSize;i++){
                clues.add(Clues.NO_CLUE);
            }
        }
        row.setClues(clues);
    }

    public int getRedCluesCount(int rowNum){
        int redCluesCount = 0;
        Row row = model.getBoard().getRow(rowNum);
        for(int i=0; i<row.getClues().size(); i++){
            if(row.getClues().get(i).equals(Clues.EXACT_POSITION)){
                redCluesCount++;
            }
        }
        return redCluesCount;
    }
    public int getWhiteCluesCount(int rowNum){
        int redCluesCount = 0;
        Row row = model.getBoard().getRow(rowNum);
        for(int i=0; i<row.getClues().size(); i++){
            if(row.getClues().get(i).equals(Clues.WRONG_POSITION)){
                redCluesCount++;
            }
        }
        return redCluesCount;
    }
    public int getVoidCluesCount(int rowNum){
        int redCluesCount = 0;
        Row row = model.getBoard().getRow(rowNum);
        for(int i=0; i<row.getClues().size(); i++){
            if(row.getClues().get(i).equals(Clues.NO_CLUE)){
                redCluesCount++;
            }
        }
        return redCluesCount;
    }

    public Board getBoard(){
        return model.getBoard();
    }

    private static void clearConsole(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

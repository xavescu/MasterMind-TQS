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
import static org.example.model.constants.Roles.CODE_BREAKER;
import static org.example.model.constants.Roles.CODE_MAKER;

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

public class GameController {
    private GameModel model;
    private GameView view;
    private int round = 0;
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
                    break;
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
        view.clearConsole();

        if(this.screen == WELCOME_SCREEN){
            view.printWelcomeView();
        }
        if(this.screen == CODE_BREAKER_SCREEN){
            view.printPlayersPoints(getPlayerPoints(1),getPlayerPoints(2));
            if(isCodeResolved()){
                view.printSecretCodeResolved(getSecretCode());
            }
            view.printCodeBreakerView(getCodeBreakerID(),model.getBoard());
            if(!isCodeResolved()&&isAllAttemptsDone()){
                view.printCodeNotResolved(getSecretCode());
            }
        }
        if(this.screen == CODE_MAKER_SCREEN){
            view.printCodeMakerView(getCodeMakerID());
        }
        if(this.screen == BYEBYE_SCREEN){
            view.printByeByeView();
            this.play = false;
        }
        if(this.screen == GAME_OVER_SCREEN){
            if(getWinnerPlayerID()==0){
                view.printTieNobodyWinsView();
            }
            if(getWinnerPlayerID()==1){
                view.printPlayer1WinsView();
            }
            if(getWinnerPlayerID()==2){
                view.printPlayer2WinsView();
            }
            this.play = false;
        }
    }

    //Todo: refactor code to a switch case to decrease the complexity
    public void processGame(){
        for(int i=0; i<1; i++){ //Fix for BUG-2: Force execute just one
            if(this.screen == WELCOME_SCREEN){
                if(Objects.equals(input, "1")){ //Start 1 player mode
                    gameMode = PLAYER_VS_CPU;
                    screen = CODE_BREAKER_SCREEN;
                    model.getPlayer1().setHuman(true);
                    model.getPlayer2().setHuman(false);
                    model.getBoard().defineRandomSecretCode();
                    break;
                }
                if(Objects.equals(input, "2")){ //Start 2 players mode
                    gameMode = PLAYER_VS_PLAYER;
                    screen = CODE_MAKER_SCREEN;
                    model.getPlayer1().setHuman(true);
                    model.getPlayer2().setHuman(true);
                    break;
                }
                if(Objects.equals(input, "3")){ //End game
                    screen = BYEBYE_SCREEN;
                    break;
                }
            }
            if(this.screen == CODE_BREAKER_SCREEN){
                if(!isCodeResolved()&&!isAllAttemptsDone()){
                    if(isValidCodeProposal(input)){
                        processCodeProposal(input); //Add a new try row with the colors
                        addCluesToRow(getRowsCount()-1);//Add the clues for that row
                    }
                }
                if(isCodeResolved()){
                    addPointsToPlayers();
                    changePlayersRoles();
                    round++;
                    if(round == 4){
                        //END OF GAME
                        screen = GAME_OVER_SCREEN;
                    } else {
                        screen = CODE_MAKER_SCREEN;
                    }
                    break;
                }else{
                    if(isAllAttemptsDone()){
                        addPointsToPlayers();
                        changePlayersRoles();
                        round++;
                        if(round == 4){
                            //END OF GAME
                            screen = GAME_OVER_SCREEN;
                            break;
                        } else {
                            screen = CODE_MAKER_SCREEN;
                            break;
                        }
                    }
                }
            }
            if(this.screen == CODE_MAKER_SCREEN){
                if(isValidCodeProposal(input)){
                    //Add new secret code to board
                    model.getBoard().defineManualSecretCode(input);
                    //CleanUp the old rows attempts
                    model.cleanUpRows();
                    //Move to code BreakerScreen
                    screen = CODE_BREAKER_SCREEN; //But now the CPU will do it
                    break;
                }
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

    public void addPointsToPlayers(){
        if(model.getPlayer1().getRole() == CODE_BREAKER){
            if(isCodeResolved()){
                model.getPlayer1().addPoints(10);
                model.getPlayer2().addPoints(getRowsCount());
            } else {
                if(getRowsCount()==10){
                    model.getPlayer2().addPoints(11);
                }
            }
        } else {
            if(isCodeResolved()){
                model.getPlayer2().addPoints(10);
                model.getPlayer1().addPoints(getRowsCount());
            } else {
                if(getRowsCount()==10){
                    model.getPlayer1().addPoints(11);
                }
            }
        }
    }

    public int getPlayerPoints(int id){
        int points = 0;
        if(id == 1){
            points = model.getPlayer1().getPoints();
        } else {
            points =model.getPlayer2().getPoints();
        }
        return points;
    }

    public void changePlayersRoles(){
        model.getPlayer1().changeRole();
        model.getPlayer2().changeRole();
    }

    public int getCodeBreakerID(){
        int id;
        if(model.getPlayer1().getRole()==CODE_BREAKER){
            id = 1;
        } else {
            id = 2;
        }
        return id;
    }

    public boolean isCodeBreakerCPU(){
        boolean isCPU = false;
        if(getCodeBreakerID()==1){
            isCPU = !model.getPlayer1().isHuman();
        }
        if(getCodeBreakerID()==2){
            isCPU = !model.getPlayer2().isHuman();
        }
        return isCPU;
    }

    public int getCodeMakerID(){
        int id;
        if(model.getPlayer1().getRole()==CODE_MAKER){
            id = 1;
        } else {
            id = 2;
        }
        return id;
    }

    public boolean isCodeMakerCPU(){
        boolean isCPU;
        if(getCodeMakerID()==1){
            isCPU = !model.getPlayer1().isHuman();
        } else {
            isCPU = !model.getPlayer2().isHuman();
        }
        return isCPU;
    }

    public int getWinnerPlayerID(){
        int id=0;
        if(!(model.getPlayer1().getPoints()==model.getPlayer2().getPoints())){
            if(model.getPlayer1().getPoints()>model.getPlayer2().getPoints()){
                id = 1;
            } else {
                id = 2;
            }
        }
        return id;
    }
}

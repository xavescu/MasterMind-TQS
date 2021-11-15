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

public class GameController {
    private final GameModel model;
    private final GameView view;
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
        return getBoard().getRow(i);
    }

    public int getRowsCount(){
        return getBoard().getRowsCount();
    }

    //Todo: pending validate view functions are call
    public void updateView(){
        //Clear app console
        view.clearConsole();

        if(this.screen == WELCOME_SCREEN){
            view.printWelcomeView(model.getBestScore_Name(), model.getBestScore_Score());
        }
        if(this.screen == CODE_BREAKER_SCREEN){
            view.printPlayersPoints(getPlayerPoints(1),getPlayerPoints(2));
            if(isCodeResolved()){
                view.printSecretCodeResolved(getSecretCode());
            }
            view.printCodeBreakerView(getCodeBreakerID(),getBoard());
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
        if(this.screen == UPDATE_HIGH_SCORE_CREEN){
            view.printUpdateHighScoreScreen();
        }
    }

    public void processGame(){
        for(int i=0; i<1; i++){ //Fix for BUG-2: Force execute just one
            if(this.screen == WELCOME_SCREEN){
                if(Objects.equals(input, "1")){ //Start 1 player mode
                    gameMode = PLAYER_VS_CPU;
                    screen = CODE_BREAKER_SCREEN;
                    model.getPlayer1().setHuman(true);
                    model.getPlayer2().setHuman(false);
                    getBoard().defineManualSecretCode("WWWWW");
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
                    if(gameMode == PLAYER_VS_CPU){
                        addPointsToPlayers();
                        getBoard().defineRandomSecretCode();
                        model.cleanUpRows();
                    } else {
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
                    }
                }else{
                    if(isAllAttemptsDone()){
                        if(gameMode == PLAYER_VS_CPU){
                            //validar si es high score
                            //BUG: how we pass from String to valid integer
                            if(Integer.parseInt(model.getBestScore_Score()) > getPlayerPoints(getCodeBreakerID())){
                                screen = UPDATE_HIGH_SCORE_CREEN;
                                break;
                            }else{
                                screen = GAME_OVER_SCREEN;
                            }
                        } else {
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
                        }
                    }
                }
            }
            if(this.screen == CODE_MAKER_SCREEN){
                if(isValidCodeProposal(input)){
                    //Add new secret code to board
                    getBoard().defineManualSecretCode(input);
                    //CleanUp the old rows attempts
                    model.cleanUpRows();
                    //Move to code BreakerScreen
                    screen = CODE_BREAKER_SCREEN; //But now the CPU will do it
                    break;
                }
            }
            if(this.screen == UPDATE_HIGH_SCORE_CREEN){
                updateHighScore();
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
            switch (input.charAt(i)){
                case 'W':
                    codeProposal.add(Colors.WHITE);
                    break;
                case 'G':
                    codeProposal.add(Colors.GREEN);
                    break;
                case 'B':
                    codeProposal.add(Colors.BLUE);
                    break;
                case 'Y':
                    codeProposal.add(Colors.YELLOW);
                    break;
                case 'R':
                    codeProposal.add(Colors.RED);
                    break;
            }
        }
        row.setCode(codeProposal);
        model.addRow(row);
    }

    public void addCluesToRow(int rowNumber){
        Row secretCode = model.getSecretCode();
        Row row = getRow(rowNumber);
        ArrayList<Integer> clues = new ArrayList<>();
        clues = addRedAndWhiteClues(clues, row, secretCode);
        clues = addVoidClues(clues);
        row.setClues(clues);
    }

    public ArrayList<Integer> addRedAndWhiteClues(ArrayList<Integer> clues, Row row, Row secretCode){
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
        return clues;
    }

    public ArrayList<Integer> addVoidClues(ArrayList<Integer> clues){
        if(clues.size()!=5){
            int tempCluesSize = 5-clues.size();
            for(int i=0; i<tempCluesSize;i++){
                clues.add(Clues.NO_CLUE);
            }
        }
        return clues;
    }

    public int getRedCluesCount(int rowNum){
        int cluesCount = 0;
        Row row = getBoard().getRow(rowNum);
        for(int i=0; i<row.getClues().size(); i++){
            if(row.getClues().get(i).equals(Clues.EXACT_POSITION)){
                cluesCount++;
            }
        }
        return cluesCount;
    }
    public int getWhiteCluesCount(int rowNum){
        int cluesCount = 0;
        Row row = getBoard().getRow(rowNum);
        for(int i=0; i<row.getClues().size(); i++){
            if(row.getClues().get(i).equals(Clues.WRONG_POSITION)){
                cluesCount++;
            }
        }
        return cluesCount;
    }
    public int getVoidCluesCount(int rowNum){
        int cluesCount = 0;
        Row row = getBoard().getRow(rowNum);
        for(int i=0; i<row.getClues().size(); i++){
            if(row.getClues().get(i).equals(Clues.NO_CLUE)){
                cluesCount++;
            }
        }
        return cluesCount;
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

    public void updateHighScore(){
        getKeyBoardInput();
        int points = getPlayerPoints(getCodeBreakerID());
        model.setBestScore_NameAndScore(input, ""+points);
    }
}

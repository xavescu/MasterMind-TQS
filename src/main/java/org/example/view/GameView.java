package org.example.view;

import org.example.model.Board;
import org.example.model.Row;
import org.example.model.constants.Clues;
import org.example.model.constants.Colors;

import static org.example.model.constants.SetUp.NUM_OF_TRIES;

public class GameView {
    private final String WHITE_BOX = "\u001B[37m"+"█"+"\u001B[0m";
    private final String BLUE_BOX = "\u001B[34m"+"█"+"\u001B[0m";
    private final String RED_BOX = "\u001B[31m"+"█"+"\u001B[0m";
    private final String GREEN_BOX = "\u001B[32m"+"█"+"\u001B[0m";
    private final String YELLOW_BOX = "\u001B[33m"+"█"+"\u001B[0m";
    private final String WHITE_CROSS = "\u001B[37m"+"X"+"\u001B[0m";
    private final String RED_CROSS = "\u001B[31m"+"X"+"\u001B[0m";


    public void printWelcomeView(){
        System.out.println("<*> MASTERMIND <*>");
        System.out.println("1) 1 Player");
        System.out.println("2) 2 Players");
        System.out.println("3) Exit");
    }

    public void printCodeBreakerView(Board board){
        for(int i=NUM_OF_TRIES; i>=1; i--){ //i = NUM_OF_TRIES..1
            if(board.getRowsCount()>=i){
                System.out.println(printCodeBreakerView_Row(board.getRow(i-1), i-1));
            } else {
                System.out.println(i+" [ ][ ][ ][ ][ ] _____");
            }
        }
        System.out.println(WHITE_BOX+"(W),"+BLUE_BOX+"(B),"+RED_BOX+"(R),"+GREEN_BOX+"(G),"+YELLOW_BOX+"(Y)");
        System.out.println("Exist color: "+WHITE_CROSS+"    ExactPosition: "+RED_CROSS);
    }

    public String printCodeBreakerView_Row(Row row, int rowNum){
        StringBuilder output = new StringBuilder();
        //Set RowNum
        output.append(rowNum + 1);
        //Set WhiteSpace
        output.append(" ");
        //Set code cells
        output.append(appendSecretCode(row));
        //Set WhiteSpace
        output.append(" ");
        int redClues =0;
        int whiteClues = 0;
        //Set Clues
        for(int i=0; i<5; i++){
            if(row.getClue(i)== Clues.EXACT_POSITION){
                redClues++;
            }
        }
        for(int i=0; i<5; i++){
            if(row.getClue(i)== Clues.WRONG_POSITION){
                whiteClues++;
            }
        }
        for(int i=0; i<redClues; i++){
            output.append(RED_CROSS);
        }
        for(int i=0; i<whiteClues; i++){
            output.append(WHITE_CROSS);
        }
        if(redClues+whiteClues!=5){
            for(int i=0; i<5-(redClues+whiteClues); i++){
                output.append("_");
            }
        }
        return output.toString();
    }

    public void printCodeMakerView(){
        System.out.println(WHITE_BOX+"(W),"+BLUE_BOX+"(B),"+RED_BOX+"(R),"+GREEN_BOX+"(G),"+YELLOW_BOX+"(Y)");
        System.out.println("Define your code:");
    }

    public void printByeByeView(){
        System.out.println("Bye bye! :)");
    }

    public void printPlayersPoints(){
        System.out.println("Player 1: 0pts       Player 2: 0pts");
    }

    public void printSecretCodeResolved(Row secretCode){
        String output = "CODE RESOLVED! => " +
                appendSecretCode(secretCode);
        System.out.println(output);
    }

    public void printCodeNotResolved(Row secretCode) {
        String output = "CODE NOT RESOLVED! => " +
                appendSecretCode(secretCode);
        System.out.println(output);
    }

    private StringBuilder appendSecretCode(Row secretCode){
        StringBuilder output = new StringBuilder();
        for(int i=0;i<secretCode.getCodeLength(); i++){
            if(secretCode.getColor(i) == Colors.WHITE){
                output.append("[" + WHITE_BOX + "]");
            }
            if(secretCode.getColor(i) == Colors.RED){
                output.append("[" + RED_BOX + "]");
            }
            if(secretCode.getColor(i) == Colors.BLUE){
                output.append("[" + BLUE_BOX + "]");
            }
            if(secretCode.getColor(i) == Colors.GREEN){
                output.append("[" + GREEN_BOX + "]");
            }
            if(secretCode.getColor(i) == Colors.YELLOW){
                output.append("[" + YELLOW_BOX + "]");
            }
        }
        return output;
    }
}

package org.example.view;

public class GameView {
    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_BLACK = "\u001B[30m";
    private String ANSI_RED = "\u001B[31m";
    private String ANSI_GREEN = "\u001B[32m";
    private String ANSI_YELLOW = "\u001B[33m";
    private String ANSI_BLUE = "\u001B[34m";
    private String ANSI_PURPLE = "\u001B[35m";
    private String ANSI_CYAN = "\u001B[36m";
    private String ANSI_WHITE = "\u001B[37m";
    private String ASCII_BOX = "█";
    private String WHITE_BOX = "\u001B[37m"+"█"+"\u001B[0m";
    private String BLUE_BOX = "\u001B[34m"+"█"+"\u001B[0m";
    private String RED_BOX = "\u001B[31m"+"█"+"\u001B[0m";
    private String GREEN_BOX = "\u001B[32m"+"█"+"\u001B[0m";
    private String YELLOW_BOX = "\u001B[33m"+"█"+"\u001B[0m";
    private String WHITE_CROSS = "\u001B[37m"+"X"+"\u001B[0m";
    private String RED_CROSS = "\u001B[31m"+"X"+"\u001B[0m";


    public void printWelcomeView(){
        System.out.println("<*> MASTERMIND <*>");
        System.out.println("1) 1 Player");
        System.out.println("2) 2 Players");
        System.out.println("3) Exit");
    }

    public void printCodeBreakerView(){
        System.out.println("9 [ ][ ][ ][ ][ ] _____");
        System.out.println("8 [ ][ ][ ][ ][ ] _____");
        System.out.println("7 [ ][ ][ ][ ][ ] _____");
        System.out.println("6 [ ][ ][ ][ ][ ] _____");
        System.out.println("5 [ ][ ][ ][ ][ ] _____");
        System.out.println("4 [ ][ ][ ][ ][ ] _____");
        System.out.println("3 [ ][ ][ ][ ][ ] _____");
        System.out.println("2 [ ][ ][ ][ ][ ] _____");
        System.out.println("1 [ ][ ][ ][ ][ ] _____");
        System.out.println(WHITE_BOX+"(W),"+BLUE_BOX+"(B),"+RED_BOX+"(R),"+GREEN_BOX+"(G),"+YELLOW_BOX+"(Y)");
        System.out.println("Exist color: "+WHITE_CROSS+"    ExactPosition: "+RED_CROSS);
    }

    public void printCodeMakerView(){
        System.out.println("Define your code");
    }

    public void printByeByeView(){
        System.out.println("Bye bye! :)");
    }

    public void printPlayersPoints(){
        System.out.println("Player 1: 0pts       Player 2: 0pts");
    }
}

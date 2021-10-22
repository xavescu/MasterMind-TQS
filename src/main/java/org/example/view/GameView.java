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
    private String ASCII_BOX = "#"; //Todo: change to the correct char

    public void printDemoView(){
        System.out.println("1)"
                + (ANSI_PURPLE+ASCII_BOX)
                + (ANSI_YELLOW+ASCII_BOX)
                + (ANSI_RED+ASCII_BOX)
                + (ANSI_CYAN+ASCII_BOX)
                + (ANSI_RESET+"|")
                + (ANSI_RED+"x")
                + (ANSI_RESET+"x")
                + (ANSI_RESET+"x")
                + (ANSI_RESET+"_")
        );
    }
}

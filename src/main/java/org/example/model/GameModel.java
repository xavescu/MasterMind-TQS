package org.example.model;

import org.example.model.constants.Roles;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class GameModel {
    private Board board = new Board();
    private Player player1 = new Player(Roles.CODE_BREAKER,true);
    private Player player2 = new Player(Roles.CODE_MAKER,true);

    public Board getBoard() {
        return board;
    }

    public void cleanUpRows(){
        board.cleanUpRows();
    }

    public void addRow(Row row){
        board.addRow(row);
    }

    public Row getSecretCode(){
        return board.getSecretCode();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getBestScore_Name(){
        String name = "";

        File file = new File("HighScore.txt"); //Target file
        Scanner s = null;

        try {
            s = new Scanner(file);

            name = s.nextLine(); 	// we save the line in a String


        } catch (Exception ex) {
            System.out.println("Message: " + ex.getMessage());
        } finally {
            // Close file
            try {
                if (s != null)
                    s.close();
            } catch (Exception ex2) {
                System.out.println("Message 2: " + ex2.getMessage());
            }
        }
        return name;
    }

    public String getBestScore_Score(){
        String name = "";
        File file = new File("HighScore.txt");
        Scanner s = null;

        try {
            s = new Scanner(file);

            name = s.nextLine();// we save the line in a String
            name = s.nextLine();// we override the variable value with the next line


        } catch (Exception ex) {
            System.out.println("Message: " + ex.getMessage());
        } finally {
            // Close file
            try {
                if (s != null)
                    s.close();
            } catch (Exception ex2) {
                System.out.println("Message 2: " + ex2.getMessage());
            }
        }
        return name;
    }

    public void setBestScore_NameAndScore(String name, String score){
        String[] lines = {name, score};

        FileWriter file = null;
        try {

            file = new FileWriter("HighScore.txt");

            // Write lines in the file
            for (String linea : lines) {
                file.write(linea + "\n");
            }

            file.close();

        } catch (Exception ex) {
            System.out.println("Exception message: " + ex.getMessage());
        }
    }
}

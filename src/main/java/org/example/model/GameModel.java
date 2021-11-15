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
        // Fichero del que queremos leer
        File fichero = new File("HighScore.txt");
        Scanner s = null;

        try {
            s = new Scanner(fichero);

            name = s.nextLine(); 	// Guardamos la linea en un String


        } catch (Exception ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        } finally {
            // Cerramos el fichero tanto si la lectura ha sido correcta o no
            try {
                if (s != null)
                    s.close();
            } catch (Exception ex2) {
                System.out.println("Mensaje 2: " + ex2.getMessage());
            }
        }
        return name;
    }

    public String getBestScore_Score(){
        String name = "";
        // Fichero del que queremos leer
        File fichero = new File("HighScore.txt");
        Scanner s = null;

        try {
            s = new Scanner(fichero);

            name = s.nextLine();
            name = s.nextLine();// Guardamos la linea en un String


        } catch (Exception ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        } finally {
            // Cerramos el fichero tanto si la lectura ha sido correcta o no
            try {
                if (s != null)
                    s.close();
            } catch (Exception ex2) {
                System.out.println("Mensaje 2: " + ex2.getMessage());
            }
        }
        return name;
    }

    public void setBestScore_NameAndScore(String name, String score){
        String[] lineas = {name, score};

        /** FORMA 1 DE ESCRITURA **/
        FileWriter fichero = null;
        try {

            fichero = new FileWriter("HighScore.txt");

            // Escribimos linea a linea en el fichero
            for (String linea : lineas) {
                fichero.write(linea + "\n");
            }

            fichero.close();

        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }
    }
}

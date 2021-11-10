package org.example.model;

import org.example.model.constants.Roles;

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
}

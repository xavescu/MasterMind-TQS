package org.example.model;

import org.example.model.enums.Roles;

public class GameModel {
    private Board board = new Board();
    private Player player1 = new Player(Roles.CODE_BREAKER);
    private Player player2 = new Player(Roles.CODE_MAKER);

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}

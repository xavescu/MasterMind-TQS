package org.example.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.example.model.constants.Colors.*;
import static org.example.model.constants.Roles.CODE_BREAKER;
import static org.example.model.constants.Roles.CODE_MAKER;
import static org.junit.Assert.*;

public class GameModelTest {

    /**
     * UnitTest
     */
    @Test
    public void getBoard_addRow() {
        //given
        GameModel model = new GameModel();

        //when
        model.addRow(new Row());

        //then
        Assert.assertEquals(1, model.getBoard().getRowsCount());
    }

    /**
     * UnitTest
     */
    @Test
    public void getBoard_addRow_cleanUpRows() {
        //given
        GameModel model = new GameModel();
        model.addRow(new Row());

        //when
        model.cleanUpRows();

        //then
        Assert.assertEquals(0, model.getBoard().getRowsCount());

    }


    /**
     * UnitTest
     */
    @Test
    public void getSecretCode() {
        //given
        GameModel model = new GameModel();
        model.getBoard().defineManualSecretCode("WBYGR");

        //when
        Row secretCode = model.getSecretCode();

        //then
        Assert.assertEquals(WHITE,secretCode.getColor(0));
        Assert.assertEquals(BLUE,secretCode.getColor(1));
        Assert.assertEquals(YELLOW,secretCode.getColor(2));
        Assert.assertEquals(GREEN,secretCode.getColor(3));
        Assert.assertEquals(RED,secretCode.getColor(4));

    }

    /**
     * UnitTest
     */
    @Test
    public void getPlayer1() {
        //given
        GameModel model = new GameModel();
        //We know that the Player1 is initialized as CODE_BREAKER

        //when

        //then
        Assert.assertEquals(CODE_BREAKER,model.getPlayer1().getRole());

    }

    /**
     * UnitTest
     */
    @Test
    public void getPlayer2() {
        //given
        GameModel model = new GameModel();
        //We know that the Player1 is initialized as CODE_MAKER

        //when

        //then
        Assert.assertEquals(CODE_MAKER,model.getPlayer2().getRole());

    }
}
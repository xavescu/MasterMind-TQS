package org.example.controller;

import org.example.model.GameModel;
import org.example.view.GameView;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.example.controller.constants.Screens.*;

public class GameControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void getKeyBoardInput_givenScreen0And1InputThenScreenIs1() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();

        //then
        controller.processGame();
        Assert.assertEquals(CODE_BREAKER_SCREEN,controller.getScreen());
    }

    @Test
    public void getKeyBoardInput_givenScreen0AndInvalidInputThenScreenIs0() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "sadaf!";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();

        //then
        controller.processGame();
        Assert.assertEquals(WELCOME_SCREEN,controller.getScreen());
    }

    @Test
    public void getKeyBoardInput_givenScreen0And2InputThenScreenIs2() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();

        //then
        controller.processGame();
        Assert.assertEquals(CODE_MAKER_SCREEN,controller.getScreen());
    }

    @Test
    public void getKeyBoardInput_givenScreen0And3InputThenScreenIsNegative1() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();

        //then
        controller.processGame();
        Assert.assertEquals(BYEBYE_SCREEN,controller.getScreen());
    }
}
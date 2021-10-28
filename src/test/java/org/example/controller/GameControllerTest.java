package org.example.controller;

import org.example.model.GameModel;
import org.example.model.constants.Colors;
import org.example.view.GameView;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.example.controller.constants.GameModes.PLAYER_VS_CPU;
import static org.example.controller.constants.GameModes.PLAYER_VS_PLAYER;
import static org.example.controller.constants.Screens.*;

public class GameControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void getKeyBoardInput_Screen_givenScreen0And1InputThenScreenIsCodeBreaker() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertEquals(CODE_BREAKER_SCREEN,controller.getScreen());
    }

    @Test
    public void getKeyBoardInput_Screen_givenScreen0AndInvalidInputThenScreenIsWelcome() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "sadaf!";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertEquals(WELCOME_SCREEN,controller.getScreen());
    }

    @Test
    public void getKeyBoardInput_Screen_givenScreen0And2InputThenScreenIsCodeMaker() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertEquals(CODE_MAKER_SCREEN,controller.getScreen());
    }

    @Test
    public void getKeyBoardInput_Screen_givenScreen0And3InputThenScreenIsByeBye() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertEquals(BYEBYE_SCREEN,controller.getScreen());
    }

    @Test
    public void getKeyBoardInput_GameMode_givenScreen0And1InputThenGameModeIsPvCPU() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertEquals(PLAYER_VS_CPU,controller.getGameMode());
    }

    @Test
    public void getKeyBoardInput_GameMode_givenScreen0AndInvalidInputThenGameModeIs0() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "sadaf!";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertEquals(0,controller.getGameMode());
    }

    @Test
    public void getKeyBoardInput_GameMode_givenScreen0And2InputThenGameModeIsPvsP() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertEquals(PLAYER_VS_PLAYER,controller.getGameMode());
    }

    @Test
    public void CPUInitializeCode_SecretCodeIsNotNull() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertNotNull(controller.getSecretCode());
    }

    @Test
    public void CPUInitializeCode_SecretCodeContainsValidColors() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertNotSame(controller.getSecretCode().getColor(0), Colors.BLUE);
    }
}
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
    public void CPUInitializeCode_CPU_SecretCodeIsNotNull() {
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
    public void CPUInitializeCode_CPU_SecretCodeHasTheExpectedLength() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertEquals(controller.getSecretCode().getCodeLength(), 5);
    }

    //Todo: transform this in a parametrized test to do the loop of positions. Also best code quality
    @Test
    public void CPUInitializeCode_CPU_SecretCodeHasValidIntegersValues() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String secretCodeValues = "";

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        secretCodeValues += controller.getSecretCode().getColor(0);
        Assert.assertTrue("1".equals(secretCodeValues)
                || "2".equals(secretCodeValues)
                || "3".equals(secretCodeValues)
                || "4".equals(secretCodeValues)
                || "5".equals(secretCodeValues));
        secretCodeValues = ""+controller.getSecretCode().getColor(1);
        Assert.assertTrue("1".equals(secretCodeValues)
                || "2".equals(secretCodeValues)
                || "3".equals(secretCodeValues)
                || "4".equals(secretCodeValues)
                || "5".equals(secretCodeValues));
        secretCodeValues = ""+controller.getSecretCode().getColor(2);
        Assert.assertTrue("1".equals(secretCodeValues)
                || "2".equals(secretCodeValues)
                || "3".equals(secretCodeValues)
                || "4".equals(secretCodeValues)
                || "5".equals(secretCodeValues));
        secretCodeValues = ""+controller.getSecretCode().getColor(3);
        Assert.assertTrue("1".equals(secretCodeValues)
                || "2".equals(secretCodeValues)
                || "3".equals(secretCodeValues)
                || "4".equals(secretCodeValues)
                || "5".equals(secretCodeValues));
        secretCodeValues = ""+controller.getSecretCode().getColor(4);
        Assert.assertTrue("1".equals(secretCodeValues)
                || "2".equals(secretCodeValues)
                || "3".equals(secretCodeValues)
                || "4".equals(secretCodeValues)
                || "5".equals(secretCodeValues));
    }

    @Test
    public void isValidCode_ValidInput_ALL_WHITE() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        String codeProposalInput = "WWWWW";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertTrue(controller.isValidCodeProposal(codeProposalInput));
    }

    @Test
    public void isValidCode_ValidInput_ALL_RED() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        String codeProposalInput = "RRRRR";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertTrue(controller.isValidCodeProposal(codeProposalInput));
    }

    @Test
    public void isValidCode_ValidInput_EACH_COLOR() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        String codeProposalInput = "RBWGY";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertTrue(controller.isValidCodeProposal(codeProposalInput));
    }

    @Test
    public void isValidCode_ValidInput_LONG_ANSWER() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        String codeProposalInput = "RBWGWW";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertFalse(controller.isValidCodeProposal(codeProposalInput));
    }

    @Test
    public void isValidCode_ValidInput_SHORT_ANSWER() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        String codeProposalInput = "RBW";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertFalse(controller.isValidCodeProposal(codeProposalInput));
    }

    @Test
    public void isValidCode_ValidInput_WRONG_CHARACTERS() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        String codeProposalInput = "RfW!W6";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertFalse(controller.isValidCodeProposal(codeProposalInput));
    }
}
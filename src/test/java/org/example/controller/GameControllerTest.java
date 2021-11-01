package org.example.controller;

import org.example.model.GameModel;
import org.example.model.constants.Clues;
import org.example.model.constants.Colors;
import org.example.view.GameView;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

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
        Assert.assertEquals(5, controller.getSecretCode().getCodeLength());
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
    public void isValidCode_InvalidInput_EACH_COLOR_LOWERCASE() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        String codeProposalInput = "rbwgy";
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
    public void isValidCode_InvalidInput_LONG_ANSWER() {
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
    public void isValidCode_InvalidInput_SHORT_ANSWER() {
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
    public void isValidCode_InvalidInput_WRONG_CHARACTERS() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        String codeProposalInput = "RfW!W";
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
    public void processCodeProposal_ExpectedColorsAreSet() {
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
        Assert.assertEquals(Colors.RED, controller.getRow(0).getColor(0));
        Assert.assertEquals(Colors.BLUE, controller.getRow(0).getColor(1));
        Assert.assertEquals(Colors.WHITE, controller.getRow(0).getColor(2));
        Assert.assertEquals(Colors.GREEN, controller.getRow(0).getColor(3));
        Assert.assertEquals(Colors.YELLOW, controller.getRow(0).getColor(4));
    }

    @Test
    public void addCluesToRow_ExpectedCluesAreSet() {
        //given
        int rowNumber = 0;
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        String codeProposalInput = "RBWGY";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        controller.getBoard().defineManualSecretCode("RBBYG"); //Todo: refactor this code
        controller.addCluesToRow(rowNumber); //Secret Code = RBBYG

        //then
        Assert.assertEquals("Assert RedClues are fine:",2, controller.getRedCluesCount(0));
        Assert.assertEquals("Assert WhiteClues are fine:",2, controller.getWhiteCluesCount(0));
        Assert.assertEquals("Assert NoClues are fine:",1, controller.getVoidCluesCount(0));
    }

    /**
     * BUG!
     * When we enter a code proposal without any color that is in SecretCode the app explodes...
     */
    @Test
    public void bug1_GivenASecretCodeWhenEnterAProposalWithoutAnyColorMatchThenTheAppExplodes() {
        GameController controller = new GameController(new GameView(), new GameModel());
        //Go to 1P GameMode
        String select1PGameMode = "1";
        InputStream in = new ByteArrayInputStream(select1PGameMode.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();
        //Force the SecretCode
        controller.getBoard().defineManualSecretCode("RRRRR"); //Todo: refactor this code
        //Enter a CodeProposal that has no color match in any position
        String codeProposalInput = "BBBBB";
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();
        //All must be good
        Assert.assertEquals("Assert NoClues are fine:",5, controller.getVoidCluesCount(0));
    }

    @Test
    public void isCodeResolved_CodeIsNotResolved() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        //Go to 1P GameMode
        String select1PGameMode = "1";
        InputStream in = new ByteArrayInputStream(select1PGameMode.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();
        //Force the SecretCode
        controller.getBoard().defineManualSecretCode("RRRRR");
        //Enter a CodeProposal that has no color match in any position
        String codeProposalInput = "BBBBB";
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();

        //when
        controller.processGame();


        //then
        Assert.assertFalse(controller.isCodeResolved());

    }

    @Test
    public void isCodeResolved_CodeIsResolved() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        //Go to 1P GameMode
        String select1PGameMode = "1";
        InputStream in = new ByteArrayInputStream(select1PGameMode.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();
        //Force the SecretCode
        controller.getBoard().defineManualSecretCode("RRRRR");
        //Enter a CodeProposal that has no color match in any position
        String codeProposalInput = "RRRRR";
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();

        //when
        controller.processGame();

        //then
        Assert.assertTrue(controller.isCodeResolved());
    }
}
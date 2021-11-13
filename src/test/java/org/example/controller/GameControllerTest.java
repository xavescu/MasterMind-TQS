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
import static org.example.model.constants.Roles.CODE_BREAKER;
import static org.example.model.constants.Roles.CODE_MAKER;

public class GameControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void isPlaying_GameStartsProperly(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertTrue(controller.isPlaying());

    }

    //Todo: check the proper way to validate the end of the game via UnitTest
    @Test
    public void isPlaying_GameEndsProperly() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertFalse(controller.isPlaying());
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
     * BUG1
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

    @Test
    public void addPointsToPlayers_AddThe10PointsToTheCodeBreakerPlayer(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        //1P vs CPU mode
        //P1 wins -> 0+10 0 10
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
        controller.processGame();

        //when
        controller.addPointsToPlayers();

        //then
        Assert.assertEquals("Player points are add by 10 because he won",10,controller.getPlayerPoints(1));
    }

    //Todo: Find a way to test this properly
    @Test
    public void addPointsToPlayers_AddTheRowsAttemptsPointsToTheCodeMakerPlayer(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        //1P vs CPU mode
        //P1 wins -> 0+10 0 10
        //Go to 1P GameMode
        String select1PGameMode = "1";
        InputStream in = new ByteArrayInputStream(select1PGameMode.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();
        //Force the SecretCode
        controller.getBoard().defineManualSecretCode("WWWWW");
        //Enter a CodeProposal that has no color match in any position
        String codeProposalInput = "RRRRR";
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        for(int i=0; i<5;i++){
            System.setIn(in);
            controller.getKeyBoardInput();
            controller.processGame();
        }
        codeProposalInput = "WWWWW";
        in = new ByteArrayInputStream(codeProposalInput.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        controller.addPointsToPlayers();

        //then
        Assert.assertEquals("Player points are add by 6 because the code was discovered on the 6th attempt",6,controller.getPlayerPoints(1));
    }

    //Todo: Find a way to test this properly
    @Test
    public void addPointsToPlayers_AddThe11PointsToTheCodeMakerPlayer(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        //1P vs CPU mode
        //P1 wins -> 0+10 0 10
        //Go to 1P GameMode
        String select1PGameMode = "1";
        InputStream in = new ByteArrayInputStream(select1PGameMode.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();
        //Force the SecretCode
        controller.getBoard().defineManualSecretCode("WWWWW");
        //Enter a CodeProposal that has no color match in any position
        String codeProposalInput = "RRRRR";
        //Fail 10 times
        for(int i=0; i<10;i++){
            in = new ByteArrayInputStream(codeProposalInput.getBytes());
            System.setIn(in);
            controller.getKeyBoardInput();
            controller.processGame();
        }
        //when
        controller.addPointsToPlayers();

        //then
        Assert.assertEquals("Player(CODE_MAKER) points are increase by 11 because he won",11,controller.getPlayerPoints(2));
    }

    @Test
    public void changePlayersRoles(){
        //given
        GameModel model = new GameModel();
        GameController controller = new GameController(new GameView(), model);

        //when
        controller.changePlayersRoles();

        //then
        Assert.assertEquals(model.getPlayer1().getRole(), CODE_MAKER);
        Assert.assertEquals(model.getPlayer2().getRole(), CODE_BREAKER);
    }

    @Test
    public void isCodeBreakerCPU_ValidateIsHUM(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String select1PGameMode = "1";
        InputStream in = new ByteArrayInputStream(select1PGameMode.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();
        //PLayer 1 -> CODE_BREAKER - HUM
        //Player 2 -> CODE_MAKER - CPU

        //then
        Assert.assertFalse(controller.isCodeBreakerCPU());
    }

    @Test
    public void isCodeMakerCPU_ValidateIsCPU(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String select1PGameMode = "1";
        InputStream in = new ByteArrayInputStream(select1PGameMode.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();
        //PLayer 1 -> CODE_BREAKER - HUM
        //Player 2 -> CODE_MAKER - CPU

        //then
        Assert.assertTrue(controller.isCodeMakerCPU());
    }

    @Test
    public void isCodeMakerCPU_ValidateIsHUM(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String select1PGameMode = "2";
        InputStream in = new ByteArrayInputStream(select1PGameMode.getBytes());
        System.setIn(in);
        controller.getKeyBoardInput();
        controller.processGame();
        //PLayer 1 -> CODE_BREAKER - HUM
        //Player 2 -> CODE_MAKER - HUM

        //then
        Assert.assertFalse(controller.isCodeMakerCPU());
    }

    @Test
    public void getWinnerPlayerID_Player1Wins(){
        //given
        GameModel model = new GameModel();
        GameController controller = new GameController(new GameView(), model);

        //when
        model.getPlayer1().addPoints(10);

        //then
        Assert.assertEquals(1,controller.getWinnerPlayerID());

    }

    @Test
    public void getWinnerPlayerID_Player2Wins(){
        //given
        GameModel model = new GameModel();
        GameController controller = new GameController(new GameView(), model);

        //when
        model.getPlayer2().addPoints(10);

        //then
        Assert.assertEquals(2,controller.getWinnerPlayerID());
    }

    @Test
    public void getWinnerPlayerID_TieNobodyWins(){
        //given
        GameModel model = new GameModel();
        GameController controller = new GameController(new GameView(), model);

        //when
        model.getPlayer1().addPoints(10);
        model.getPlayer2().addPoints(10);

        //then
        Assert.assertEquals(0,controller.getWinnerPlayerID());
    }
}
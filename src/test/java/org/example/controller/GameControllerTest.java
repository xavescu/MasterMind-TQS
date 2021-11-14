package org.example.controller;

import org.example.model.GameModel;
import org.example.model.constants.Colors;
import org.example.view.GameView;
import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.example.controller.constants.GameModes.PLAYER_VS_CPU;
import static org.example.controller.constants.GameModes.PLAYER_VS_PLAYER;
import static org.example.controller.constants.Screens.*;
import static org.example.model.constants.Roles.CODE_BREAKER;
import static org.example.model.constants.Roles.CODE_MAKER;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class GameControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void isPlaying_GameStartsProperly(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("1");

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
        systemInMock.provideLines("1");

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
        systemInMock.provideLines("1");

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
        systemInMock.provideLines("sadaf!");

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
        systemInMock.provideLines("2");


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
        systemInMock.provideLines("3");

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
        systemInMock.provideLines("1");

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
        systemInMock.provideLines("sadaf!");

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
        systemInMock.provideLines("2");

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
        systemInMock.provideLines("1");

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
        systemInMock.provideLines("1");

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
        systemInMock.provideLines("1");
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

        //when
        String codeProposal = "RBWGY";

        //then
        Assert.assertTrue(controller.isValidCodeProposal(codeProposal));
    }

    @Test
    public void isValidCode_InvalidInput_EACH_COLOR_LOWERCASE() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String codeProposal = "rbwgy";

        //then
        Assert.assertFalse(controller.isValidCodeProposal(codeProposal));
    }

    @Test
    public void isValidCode_InvalidInput_LONG_ANSWER() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String codeProposal = "RBWGWW";

        //then
        Assert.assertFalse(controller.isValidCodeProposal(codeProposal));
    }

    @Test
    public void isValidCode_InvalidInput_SHORT_ANSWER() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String codeProposal = "RBW";

        //then
        Assert.assertFalse(controller.isValidCodeProposal(codeProposal));
    }

    @Test
    public void isValidCode_InvalidInput_WRONG_CHARACTERS() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String codeProposal = "RfW!W";

        //then
        Assert.assertFalse(controller.isValidCodeProposal(codeProposal));
    }

    @Test
    public void processCodeProposal_ExpectedColorsAreSet() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("1","RBWGY");


        //when
        controller.getKeyBoardInput();
        controller.processGame();
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
        systemInMock.provideLines("2","RBBYG","RBWGY");
        controller.getKeyBoardInput();
        controller.processGame();
        controller.getKeyBoardInput();
        controller.processGame();
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        controller.addCluesToRow(rowNumber);

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
        systemInMock.provideLines("2","RRRRR","BBBBB");
        //Go to 2P GameMode
        controller.getKeyBoardInput();
        controller.processGame();
        //Define the SecretCode
        controller.getKeyBoardInput();
        controller.processGame();
        //Enter a CodeProposal that has no color match in any position
        controller.getKeyBoardInput();
        controller.processGame();
        //All must be good
        Assert.assertEquals("Assert NoClues are fine:",5, controller.getVoidCluesCount(0));
    }

    @Test
    public void isCodeResolved_CodeIsNotResolved() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","RRRRR","BBBBB");
        //Go to 2P GameMode
        controller.getKeyBoardInput();
        controller.processGame();
        //Enter the SecretCode
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        controller.getKeyBoardInput();
        controller.processGame();


        //then
        Assert.assertFalse(controller.isCodeResolved());

    }

    @Test
    public void isCodeResolved_CodeIsResolved() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","RRRRR","RRRRR");
        //Go to 2P GameMode
        controller.getKeyBoardInput();
        controller.processGame();
        //Enter the SecretCode
        controller.getKeyBoardInput();
        controller.processGame();

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertTrue(controller.isCodeResolved());
    }

    @Test
    public void addPointsToPlayers_AddThe10PointsToTheCodeBreakerPlayer(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","RRRRR","RRRRR");
        for(int i=0; i<3;i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //when

        //then
        Assert.assertEquals("Player points are add by 10 because he won when it was CODE_BREAKER",10,controller.getPlayerPoints(controller.getCodeMakerID()));
    }

    //Todo: Find a way to test this properly
    @Test
    public void addPointsToPlayers_AddTheRowsAttemptsPointsToTheCodeMakerPlayer(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","WWWWW","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","WWWWW");

        //when
        for(int i=0; i<8;i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //then
        Assert.assertEquals("Player points are add by 6 because the code was discovered on the 6th attempt whe it was CODE_MAKER",6,controller.getPlayerPoints(controller.getCodeBreakerID()));
    }

    //Todo: Find a way to test this properly
    @Test
    public void addPointsToPlayers_AddThe11PointsToTheCodeMakerPlayer(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","WWWWW","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR");

        for(int i=0; i<12;i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }
        //when
        controller.addPointsToPlayers();

        //then
        Assert.assertEquals("Player(CODE_MAKER) points are increase by 11 because he won",11,controller.getPlayerPoints(controller.getCodeMakerID()));
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
        systemInMock.provideLines("1");

        //when
        controller.getKeyBoardInput();
        controller.processGame();
        //PLayer 1 -> CODE_BREAKER - HUM
        //Player 2 -> CODE_MAKER - CPU

        //then
        Assert.assertFalse(controller.isCodeBreakerCPU());
    }

    @Test
    public void isCodeMakerCPU_ValidatePlayer1Path_False(){
        //given
        GameModel model = new GameModel();
        GameController controller = new GameController(new GameView(), model);
        systemInMock.provideLines("1", "WWWWW");

        //when
        // select Mode 1 HUMvsCPU
        controller.getKeyBoardInput();
        controller.processGame();
        //PLayer 1 -> CODE_BREAKER - HUM
        //Player 2 -> CODE_MAKER - CPU
        // we force the secret code
        model.getBoard().defineManualSecretCode("WWWWW");
        //finish round
        controller.getKeyBoardInput();
        controller.processGame();
        //PLayer 1 -> CODE_MAKER - HUM
        //Player 2 -> CODE_BREAKER - CPU

        //then
        Assert.assertFalse(controller.isCodeMakerCPU());
    }

    @Test
    public void isCodeMakerCPU_ValidatePlayer2Path_True(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("1");

        //when
        controller.getKeyBoardInput();
        controller.processGame();
        //PLayer 1 -> CODE_BREAKER - HUM
        //Player 2 -> CODE_MAKER - CPU

        //then
        Assert.assertTrue(controller.isCodeMakerCPU());
    }

    @Test
    public void isCodeMakerCPU_ValidatePlayer2Path_False(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2");

        //when
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
package org.example.controller;

import org.example.model.GameModel;
import org.example.model.constants.Colors;
import org.example.view.GameView;
import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.example.controller.constants.GameModes.PLAYER_VS_CPU;
import static org.example.controller.constants.GameModes.PLAYER_VS_PLAYER;
import static org.example.controller.constants.Screens.*;
import static org.example.model.constants.Colors.*;
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
    public void isPlaying_theGameIsInitializeAsExpected() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("1");

        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertTrue(controller.isPlaying());
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
    public void screen_GameOverScreen_isCallAfterCodeResolved(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW");

        //when
        for(int i=0; i<9; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //then
        Assert.assertEquals(GAME_OVER_SCREEN,controller.getScreen());
    }

    @Test
    public void screen_GameOverScreen_isCallAfterAllAttemptsDone(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR");

        //when
        for(int i=0; i<18; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //then
        Assert.assertEquals(GAME_OVER_SCREEN,controller.getScreen());
    }

    @Test
    public void screen_GameOverScreen_isCallAfterAllAttemptsDone_HUMvsCPU(){
        //given
        GameModel model = new GameModel();
        GameController controller = new GameController(new GameView(), model);
        systemInMock.provideLines("1","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW");

        //when
        controller.getKeyBoardInput();
        controller.processGame();
        model.getBoard().defineManualSecretCode("RRRRR");
        for(int i=0; i<10; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //then
        Assert.assertEquals(GAME_OVER_SCREEN,controller.getScreen());
    }

    @Test
    public void validate_weStillCodeBreakerScreen_CodeResolved_HUMvsCPU(){
        //given
        GameModel model = new GameModel();
        GameController controller = new GameController(new GameView(), model);
        systemInMock.provideLines("1","RRRRR");

        //when
        controller.getKeyBoardInput();
        controller.processGame();
        model.getBoard().defineManualSecretCode("RRRRR");
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertEquals(CODE_BREAKER_SCREEN,controller.getScreen());
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
    public void processCodeProposal_AllColorsAreSetAsExpected(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        controller.processCodeProposal("WBYRG");

        //then
        Assert.assertEquals(WHITE,controller.getRow(0).getColor(0));
        Assert.assertEquals(BLUE,controller.getRow(0).getColor(1));
        Assert.assertEquals(YELLOW,controller.getRow(0).getColor(2));
        Assert.assertEquals(RED,controller.getRow(0).getColor(3));
        Assert.assertEquals(GREEN,controller.getRow(0).getColor(4));

    }

    @Test
    public void getRowsCount_NoRows(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("1");


        //when
        controller.getKeyBoardInput();
        controller.processGame();

        //then
        Assert.assertEquals(0, controller.getRowsCount());
    }

    @Test
    public void getRowsCount_SomeRows(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2", "WWWWW", "RRRRR");


        //when
        for(int i=0; i<3; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //then
        Assert.assertEquals(1, controller.getRowsCount());
    }

    @Test
    public void isValidCodeProposal_ValidInput_EACH_COLOR() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String codeProposal = "RBWGY";

        //then
        Assert.assertTrue(controller.isValidCodeProposal(codeProposal));
    }

    @Test
    public void isValidCodeProposal_InvalidInput_EACH_COLOR_LOWERCASE() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String codeProposal = "rbwgy";

        //then
        Assert.assertFalse(controller.isValidCodeProposal(codeProposal));
    }

    @Test
    public void isValidCodeProposal_InvalidInput_LONG_ANSWER() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String codeProposal = "RBWGWW";

        //then
        Assert.assertFalse(controller.isValidCodeProposal(codeProposal));
    }

    @Test
    public void isValidCodeProposal_InvalidInput_SHORT_ANSWER() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());

        //when
        String codeProposal = "RBW";

        //then
        Assert.assertFalse(controller.isValidCodeProposal(codeProposal));
    }

    @Test
    public void isValidCodeProposal_InvalidInput_WRONG_CHARACTERS() {
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
        for(int i=0; i<2; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //then
        Assert.assertEquals(Colors.RED, controller.getRow(0).getColor(0));
        Assert.assertEquals(Colors.BLUE, controller.getRow(0).getColor(1));
        Assert.assertEquals(WHITE, controller.getRow(0).getColor(2));
        Assert.assertEquals(Colors.GREEN, controller.getRow(0).getColor(3));
        Assert.assertEquals(Colors.YELLOW, controller.getRow(0).getColor(4));
    }

    @Test
    public void addCluesToRow_ExpectedCluesAreSet() {
        //given
        int rowNumber = 0;
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","RBBYG","RBWGY");

        //when
        for(int i=0; i<3; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //then
        Assert.assertEquals("Assert RedClues are fine:",2, controller.getRedCluesCount(0));
        Assert.assertEquals("Assert WhiteClues are fine:",2, controller.getWhiteCluesCount(0));
        Assert.assertEquals("Assert NoClues are fine:",1, controller.getVoidCluesCount(0));
    }

    @Test
    public void addCluesToRow_NoVoidCluesAreAdd() {
        //given
        int rowNumber = 0;
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","RBBYG","RBBGY");

        //when
        for(int i=0; i<3; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //then
        Assert.assertEquals("Assert NoClues are fine:",0, controller.getVoidCluesCount(0));
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
    public void isAllAttemptsDone_False_LessThan10Rows(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("1");

        //when
        controller.getKeyBoardInput();
        controller.processGame();


        //then
        Assert.assertFalse(controller.isAllAttemptsDone());
    }

    @Test
    public void isAllAttemptsDone_True_10Rows(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","RRRRR","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW","WWWWW");

        //when
        for(int i=0; i<12; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //then
        Assert.assertTrue(controller.isAllAttemptsDone());
    }

    @Test
    public void isCodeResolved_CodeIsNotResolved_NoRow() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("1");

        //when
        controller.getKeyBoardInput();
        controller.processGame();


        //then
        Assert.assertFalse(controller.isCodeResolved());

    }

    @Test
    public void isCodeResolved_CodeIsNotResolved_OneRow() {
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","RRRRR","BBBBB");

        //when
        for(int i=0; i<3; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

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
    public void addPointsToPlayers_AddThe10PointsToTheCodeBreakerPlayer1(){
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
        Assert.assertEquals("Player points are add by 1 because the code was discovered on the 6th attempt whe it was CODE_MAKER",1,controller.getPlayerPoints(controller.getCodeBreakerID()));
    }

    @Test
    public void addPointsToPlayers_AddThe10PointsToTheCodeBreakerPlayer2(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","RRRRR","RRRRR","WWWWW","WWWWW");
        for(int i=0; i<5;i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //when

        //then
        Assert.assertEquals("Player points are add by 10 because he won when it was CODE_BREAKER (+1 from the first round)",11,controller.getPlayerPoints(controller.getCodeMakerID()));
        Assert.assertEquals("Player points are add by 1 because the code was discovered on the 6th attempt whe it was CODE_MAKER (+10 from the first round)",11,controller.getPlayerPoints(controller.getCodeBreakerID()));
    }

    @Test
    public void addPointsToPlayers_AddThe11PointsToTheCodeMakerPlayer1(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","WWWWW","WWWWW","WWWWW","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR");

        for(int i=0; i<14;i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }

        //then
        Assert.assertEquals("Player(CODE_MAKER) points are increase by 21 (10 1st round + 11)",21,controller.getPlayerPoints(controller.getCodeBreakerID()));
    }

    @Test
    public void addPointsToPlayers_AddThe11PointsToTheCodeMakerPlayer2(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","WWWWW","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR","RRRRR");

        for(int i=0; i<12;i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }
        //when

        //then
        Assert.assertEquals("Player(CODE_MAKER) points are increase by 11 because he won",11,controller.getPlayerPoints(controller.getCodeBreakerID()));
    }

    @Test
    public void addPointsToPlayers_DoNothingBreakerPlayer1(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","WWWWW","RRRRR");

        for(int i=0; i<3;i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }
        //when

        //then
        Assert.assertEquals("Player(CODE_MAKER) points are increase by 0 because he still playing",0,controller.getPlayerPoints(controller.getCodeBreakerID()));
    }

    @Test
    public void addPointsToPlayers_DoNothingBreakerPlayer2(){
        //given
        GameController controller = new GameController(new GameView(), new GameModel());
        systemInMock.provideLines("2","WWWWW","WWWWW","RRRRR","BBBBB");

        for(int i=0; i<5;i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }
        //when

        //then
        Assert.assertEquals("Player(CODE_MAKER) points are increase by 0 because he still playing (stills with one from the 1st round)",1,controller.getPlayerPoints(controller.getCodeBreakerID()));
    }

    @Test
    public void changePlayersRoles(){
        //given
        GameModel model = new GameModel();
        GameController controller = new GameController(new GameView(), model);
        //The state when we initialize the controller is:
        //Player1 -> CODE_BREAKER
        //Player2 -> CODE_MAKER

        //when
        controller.changePlayersRoles();

        //then
        Assert.assertEquals(model.getPlayer1().getRole(), CODE_MAKER);
        Assert.assertEquals(model.getPlayer2().getRole(), CODE_BREAKER);
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

    @Test
    public void updateHighScore_UpdateNewScore(){
        //given
        GameModel model = new GameModel();
        String originalName = model.getBestScore_Name();
        String originalScore = model.getBestScore_Score();
        GameController controller = new GameController(new GameView(), model);
        //setejar el nou contingut del file (Dummy(0))
        model.setBestScore_NameAndScore("Dummy","1");
        systemInMock.provideLines("1","WWWWW","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","Saitama");
        //seleccionar 1
        controller.getKeyBoardInput();
        controller.processGame();
        //forzar manualment el codi secret ("WWWWW")
        controller.getBoard().defineManualSecretCode("WWWWW");
        //encertar
        controller.getKeyBoardInput();
        controller.processGame();
        //forçar codi secret
        controller.getBoard().defineManualSecretCode("RRRRR");

        //when
        //fallarx10
        for(int i=0; i<10; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }
        //entrar nom (Saitama)
        controller.getKeyBoardInput();
        controller.processGame();
        //then
        //validar file té els valors esperat (Saitama(10))
        Assert.assertEquals("Saitama", model.getBestScore_Name());
        Assert.assertEquals(10, Integer.parseInt(model.getBestScore_Score()));
        //tornar a setejar el valor al file
        model.setBestScore_NameAndScore(originalName,originalScore);
    }
    @Test
    public void updateHighScore_DontUpdateNewScore_Equal(){
        //given
        GameModel model = new GameModel();
        String originalName = model.getBestScore_Name();
        String originalScore = model.getBestScore_Score();
        GameController controller = new GameController(new GameView(), model);
        //setejar el nou contingut del file (Dummy(0))
        model.setBestScore_NameAndScore("Dummy","10");
        systemInMock.provideLines("1","WWWWW","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","Saitama");
        //seleccionar 1
        controller.getKeyBoardInput();
        controller.processGame();
        //forzar manualment el codi secret ("WWWWW")
        controller.getBoard().defineManualSecretCode("WWWWW");
        //encertar
        controller.getKeyBoardInput();
        controller.processGame();
        //forçar codi secret
        controller.getBoard().defineManualSecretCode("RRRRR");

        //when
        //fallarx10
        for(int i=0; i<10; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }
        //entrar nom (Saitama)
        controller.getKeyBoardInput();
        controller.processGame();
        //then
        //validar file té els valors esperat (Saitama(10))
        Assert.assertEquals("Dummy", model.getBestScore_Name());
        Assert.assertEquals(10, Integer.parseInt(model.getBestScore_Score()));
        //tornar a setejar el valor al file
        model.setBestScore_NameAndScore(originalName,originalScore);
    }
    @Test
    public void updateHighScore_DontUpdateNewScore_Less(){
        //given
        GameModel model = new GameModel();
        String originalName = model.getBestScore_Name();
        String originalScore = model.getBestScore_Score();
        GameController controller = new GameController(new GameView(), model);
        //setejar el nou contingut del file (Dummy(0))
        model.setBestScore_NameAndScore("Dummy","100");
        systemInMock.provideLines("1","WWWWW","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","BBBBB","Saitama");
        //seleccionar 1
        controller.getKeyBoardInput();
        controller.processGame();
        //forzar manualment el codi secret ("WWWWW")
        controller.getBoard().defineManualSecretCode("WWWWW");
        //encertar
        controller.getKeyBoardInput();
        controller.processGame();
        //forçar codi secret
        controller.getBoard().defineManualSecretCode("RRRRR");

        //when
        //fallarx10
        for(int i=0; i<10; i++){
            controller.getKeyBoardInput();
            controller.processGame();
        }
        //entrar nom (Saitama)
        controller.getKeyBoardInput();
        controller.processGame();
        //then
        //validar file té els valors esperat (Saitama(10))
        Assert.assertEquals("Dummy", model.getBestScore_Name());
        Assert.assertEquals(100, Integer.parseInt(model.getBestScore_Score()));
        //tornar a setejar el valor al file
        model.setBestScore_NameAndScore(originalName,originalScore);
    }
}
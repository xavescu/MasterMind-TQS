package org.example.model;

import org.junit.Assert;
import org.junit.Test;

import static org.example.model.constants.Roles.CODE_BREAKER;
import static org.example.model.constants.Roles.CODE_MAKER;

public class PlayerTest {
    /**
     * We validate the change from CODE_MAKER to CODE_BREAKER
     * UnitTest
     */
    @Test
    public void givenCodeMakerPlayerWhenChangeRoleThenRoleIsCodeBreaker(){
        //given
        Player player = new Player(CODE_MAKER);

        //when
        player.changeRole();

        //then
        Assert.assertEquals(CODE_BREAKER, player.getRole());
    }

    /**
     * We validate the change from CODE_BREAKER to CODE_MAKER
     * UnitTest
     */
    @Test
    public void givenCodeBreakerPlayerWhenChangeRoleThenRoleIsCodeMaker(){
        //given
        Player player = new Player(CODE_BREAKER);

        //when
        player.changeRole();

        //then
        Assert.assertEquals(CODE_MAKER, player.getRole());
    }

    /**
     * We validate that we add points correctly
     * UnitTest
     */
    @Test
    public void givenNewPlayerWhenAdding5PointsThenHave5TotalPoints(){
        //given
        Player player = new Player(CODE_BREAKER);

        //when
        player.addPoints(5);

        //then
        Assert.assertEquals(5, player.getPoints());
    }

    /**
     * We validate that we can add points to an existing points count
     * UnitTest
     */
    @Test
    public void givenPlayerWith5PointsWhenAdding3PointsThenHave8TotalPoints(){
        //given
        Player player = new Player(CODE_BREAKER);
        player.addPoints(5);

        //when
        player.addPoints(3);

        //then
        Assert.assertEquals(8, player.getPoints());
    }

    /**
     * We validate that nothing breaks when we have more than 9 points
     * UnitTest
     */
    @Test
    public void givenPlayerWith8PointsWhenAdding4PointsThenHave12TotalPoints(){
        //given
        Player player = new Player(CODE_BREAKER);
        player.addPoints(8);

        //when
        player.addPoints(4);

        //then
        Assert.assertEquals(12, player.getPoints());
    }
}
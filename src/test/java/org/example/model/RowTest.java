package org.example.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.example.model.constants.Clues.*;
import static org.example.model.constants.Colors.*;
import static org.junit.Assert.*;

public class RowTest {

    /**
     * UnitTest
     */
    @Test
    public void setClues_getClues() {
        //given
        Row row = new Row();
        ArrayList<Integer> clues = new ArrayList<>();
        clues.add(WRONG_POSITION);clues.add(NO_CLUE);clues.add(WRONG_POSITION);clues.add(EXACT_POSITION);clues.add(EXACT_POSITION);

        //when
        row.setClues(clues);

        //then
        Assert.assertEquals(clues,row.getClues());

    }

    /**
     * UnitTest
     */
    @Test
    public void getClue() {
        //given
        Row row = new Row();
        ArrayList<Integer> clues = new ArrayList<>();
        clues.add(WRONG_POSITION);clues.add(NO_CLUE);clues.add(WRONG_POSITION);clues.add(EXACT_POSITION);clues.add(EXACT_POSITION);

        //when
        row.setClues(clues);

        //then
        Assert.assertEquals(WRONG_POSITION,row.getClue(0));
        Assert.assertEquals(NO_CLUE,row.getClue(1));
        Assert.assertEquals(WRONG_POSITION,row.getClue(2));
        Assert.assertEquals(EXACT_POSITION,row.getClue(3));
        Assert.assertEquals(EXACT_POSITION,row.getClue(4));
    }

    /**
     * UnitTest
     */
    @Test
    public void setCode_getColor() {
        //given
        Row row = new Row();
        ArrayList<Integer> code = new ArrayList<>();
        code.add(WHITE);code.add(BLUE);code.add(YELLOW);code.add(GREEN);code.add(RED);

        //when
        row.setCode(code);

        //then
        Assert.assertEquals(WHITE,row.getColor(0));
        Assert.assertEquals(BLUE,row.getColor(1));
        Assert.assertEquals(YELLOW,row.getColor(2));
        Assert.assertEquals(GREEN,row.getColor(3));
        Assert.assertEquals(RED,row.getColor(4));

    }

    @Test
    public void getCodeLength() {
        //given
        Row row = new Row();
        ArrayList<Integer> code = new ArrayList<>();
        code.add(WHITE);code.add(BLUE);code.add(YELLOW);code.add(GREEN);code.add(RED);

        //when
        row.setCode(code);

        //then
        Assert.assertEquals(5, row.getCodeLength());

    }
}
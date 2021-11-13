package org.example.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.example.model.constants.Colors.*;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void getRow_addRow() {
        //given
        Board board = new Board();
        Row row = new Row();

        //when
        board.addRow(row);

        //then
        Assert.assertEquals(row, board.getRow(0));

    }

    @Test
    public void cleanUpRows() {
        //given
        Board board = new Board();
        Row row = new Row();
        board.addRow(row);

        //when
        board.cleanUpRows();

        //then
        Assert.assertEquals(0,board.getRowsCount());
    }

    @Test
    public void getRowsCount() {
        //given
        Board board = new Board();
        Row row = new Row();
        board.addRow(row);

        //when

        //then
        Assert.assertEquals(1,board.getRowsCount());
    }

    @Test
    public void defineManualSecretCode_getSecretCode() {
        //given
        Board board = new Board();
        String secretCode = "";
        secretCode += "W";
        secretCode += "R";
        secretCode += "B";
        secretCode += "Y";
        secretCode += "G";
        board.defineManualSecretCode(secretCode);

        //when

        //then
        Assert.assertEquals(WHITE,board.getSecretCode().getColor(0));
        Assert.assertEquals(RED,board.getSecretCode().getColor(1));
        Assert.assertEquals(BLUE,board.getSecretCode().getColor(2));
        Assert.assertEquals(YELLOW,board.getSecretCode().getColor(3));
        Assert.assertEquals(GREEN,board.getSecretCode().getColor(4));
    }

    @Test
    public void defineRandomSecretCode() {
        //given
        Board board = new Board();
        String secretCodeValues = "";

        //when
        board.defineRandomSecretCode();

        //then
        secretCodeValues += board.getSecretCode().getColor(0);
        Assert.assertTrue("1".equals(secretCodeValues)
                || "2".equals(secretCodeValues)
                || "3".equals(secretCodeValues)
                || "4".equals(secretCodeValues)
                || "5".equals(secretCodeValues));
        secretCodeValues = ""+board.getSecretCode().getColor(1);
        Assert.assertTrue("1".equals(secretCodeValues)
                || "2".equals(secretCodeValues)
                || "3".equals(secretCodeValues)
                || "4".equals(secretCodeValues)
                || "5".equals(secretCodeValues));
        secretCodeValues = ""+board.getSecretCode().getColor(2);
        Assert.assertTrue("1".equals(secretCodeValues)
                || "2".equals(secretCodeValues)
                || "3".equals(secretCodeValues)
                || "4".equals(secretCodeValues)
                || "5".equals(secretCodeValues));
        secretCodeValues = ""+board.getSecretCode().getColor(3);
        Assert.assertTrue("1".equals(secretCodeValues)
                || "2".equals(secretCodeValues)
                || "3".equals(secretCodeValues)
                || "4".equals(secretCodeValues)
                || "5".equals(secretCodeValues));
        secretCodeValues = ""+board.getSecretCode().getColor(4);
        Assert.assertTrue("1".equals(secretCodeValues)
                || "2".equals(secretCodeValues)
                || "3".equals(secretCodeValues)
                || "4".equals(secretCodeValues)
                || "5".equals(secretCodeValues));

    }
}
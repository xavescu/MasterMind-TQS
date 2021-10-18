package org.example.model;

import org.example.model.enums.Clues;
import org.example.model.enums.Colors;

import java.util.ArrayList;

public class Row {
    private ArrayList<Colors> code = new ArrayList();
    private ArrayList<Clues> clues = new ArrayList();

    public ArrayList<Clues> getClues() {
        return clues;
    }

    public void setClues(ArrayList<Clues> clues) {
        this.clues = clues;
    }

    public ArrayList<Colors> getCode() {
        return code;
    }

    public void setCode(ArrayList<Colors> code) {
        this.code = code;
    }
}

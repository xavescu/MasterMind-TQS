package org.example.model;

import org.example.model.constants.Clues;
import org.example.model.constants.Colors;

import java.util.ArrayList;

public class Row {
    private ArrayList<Colors> code = new ArrayList(4);
    private ArrayList<Clues> clues = new ArrayList(4);

    public ArrayList<Clues> getClues() {
        return clues;
    }

    public void setClues(ArrayList<Clues> clues) {
        this.clues = clues;
    }

    public Colors getColor(int i) {
        return code.get(i);
    }

    public void setCode(ArrayList<Colors> code) {
        this.code = code;
    }
}

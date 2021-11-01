package org.example.model;

import org.example.model.constants.Clues;
import org.example.model.constants.Colors;

import java.util.ArrayList;

public class Row {
    private ArrayList<Integer> code = new ArrayList();
    private ArrayList<Clues> clues = new ArrayList();

    public ArrayList<Clues> getClues() {
        return clues;
    }

    public void setClues(ArrayList<Clues> clues) {
        this.clues = clues;
    }

    public int getColor(int i) {
        return code.get(i);
    }

    public int getCodeLength(){
        return code.size();
    }

    public void setCode(ArrayList<Integer> code) {
        this.code = code;
    }
}

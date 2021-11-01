package org.example.model;


import java.util.ArrayList;

public class Row {
    private ArrayList<Integer> code = new ArrayList();
    private ArrayList<Integer> clues = new ArrayList();


    public ArrayList<Integer> getClues() {
        return clues;
    }

    public void setClues(ArrayList<Integer> clues) {
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

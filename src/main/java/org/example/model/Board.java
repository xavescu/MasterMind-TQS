package org.example.model;

import org.example.model.constants.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private List<Row> rows = new ArrayList<>();
    private Row secretCode = new Row();

    public Row getRow(int row) {
        return rows.get(row);
    }

    public void addRow(Row row){
        this.rows.add(row);
    }

    public Row getSecretCode(){
        return secretCode;
    }

    public void setUpSecretCode(ArrayList<Integer> code){
        secretCode.setCode(code);
    }

    public void defineNewSecretCode(){
        ArrayList<Integer> secretCode = new ArrayList<>();
        for(int i=0; i<5; i++){
            int rand = new Random().nextInt(5); //Generate int [0...4]
            secretCode.add(rand+1); //We add 1 because colors goes from 1 to 5
        }
        this.secretCode.setCode(secretCode);
    }
}

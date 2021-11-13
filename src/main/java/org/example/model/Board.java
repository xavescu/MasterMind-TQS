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

    public void cleanUpRows(){
        rows = new ArrayList<>();
    }

    public void addRow(Row row){
        this.rows.add(row);
    }

    public int getRowsCount(){
        return rows.size();
    }

    public Row getSecretCode(){
        return secretCode;
    }

    public void defineRandomSecretCode(){
        ArrayList<Integer> secretCode = new ArrayList<>();
        for(int i=0; i<5; i++){
            int rand = new Random().nextInt(5); //Generate int [0...4]
            secretCode.add(rand+1); //We add 1 because colors goes from 1 to 5
        }
        this.secretCode.setCode(secretCode);
    }

    public void defineManualSecretCode(String manuealSecretCode){
        ArrayList<Integer> newSecretCode = new ArrayList<>();

        for(int i=0; i<5; i++){
            if(manuealSecretCode.charAt(i) == ('W')){
                newSecretCode.add(Colors.WHITE);
            }
            if(manuealSecretCode.charAt(i) == ('G')){
                newSecretCode.add(Colors.GREEN);
            }
            if(manuealSecretCode.charAt(i) == ('B')){
                newSecretCode.add(Colors.BLUE);
            }
            if(manuealSecretCode.charAt(i) == ('R')){
                newSecretCode.add(Colors.RED);
            }
            if(manuealSecretCode.charAt(i) == ('Y')){
                newSecretCode.add(Colors.YELLOW);
            }
        }

        this.secretCode.setCode(newSecretCode);
    }
}

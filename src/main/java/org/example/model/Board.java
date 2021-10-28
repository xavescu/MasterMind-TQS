package org.example.model;

import org.example.model.constants.Colors;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Row> rows;
    private Row secretCode;

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
}

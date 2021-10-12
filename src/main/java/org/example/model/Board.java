package org.example.model;

import java.util.List;

public class Board {
    private List<Row> rows;

    public Row getRow(int row) {
        return rows.get(row);
    }

    public void addRow(Row row){
        this.rows.add(row);
    }
}

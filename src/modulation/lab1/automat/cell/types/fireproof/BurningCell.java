/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.cell.types.fireproof;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.cell.types.ActableCell;
import modulation.lab1.automat.cell.types.ActingCell;

/**
 *
 * @author Admin
 */
public class BurningCell extends FireproofCell implements ActableCell, ActingCell{

    public BurningCell(Cell origin) {
        this.color = Color.RED;
        this.price = 0.0;
        this.position = origin.getPosition();
        this.type = BurningCell.class.getSimpleName();
    }

    @Override
    public Cell changeState(Cell actor) {
        return new DeadCell(this);
    }

    @Override
    public List<Cell> act(List<Cell> suroundingCells) {
        List<Cell> result = new ArrayList<>();
        for (int i = 0; i < suroundingCells.size(); i++) {
            Cell processingCell = suroundingCells.get(i);
            if (processingCell instanceof ActableCell) {
                result.add(((ActableCell) processingCell).changeState(this));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "*";
    }
}

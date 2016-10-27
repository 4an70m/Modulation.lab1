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
import modulation.lab1.automat.cell.types.flammable.ExplosiveCell;
import modulation.lab1.automat.util.Position2D;

/**
 *
 * @author Admin
 */
public class ExplosedCell extends FireproofCell implements ActableCell, ActingCell{

    public ExplosedCell() {
    }
    
    public ExplosedCell(Integer x, Integer y) {
        this(new Position2D(x, y));
    }
    
    public ExplosedCell(Position2D position) {
        super();
        this.color = Color.WHITE;
        this.position = position;
        this.type = ExplosedCell.class.getSimpleName();
        this.price = 0.0;
    }
    
    @Override
    public Cell changeState(Cell actor) {
        return new BurningCell(this);
    }

    @Override
    public List<Cell> act(List<Cell> suroundingCells) {
        List<Cell> result = new ArrayList<>();
        for (Cell processingCell : suroundingCells) {
            if (processingCell instanceof ExplosiveCell
            ||  processingCell instanceof ExplosedCell) {
                Cell c = ((ActableCell) processingCell).changeState(this);
                result.add(c);
            }
            
        }
        return result;
    }
    
    
    @Override
    public String toString() {
        return "!";
    }
}

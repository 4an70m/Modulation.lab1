/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.cell.types.flammable;

import java.util.List;
import javafx.scene.paint.Color;
import modulation.lab1.automat.cell.types.ActableCell;
import modulation.lab1.automat.cell.types.fireproof.BurningCell;
import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.util.Position2D;

/**
 *
 * @author Admin
 */
public class TreeCell extends FlammableCell implements ActableCell{
    
    public TreeCell() {
    }
    
    public TreeCell(Integer x, Integer y) {
        this(new Position2D(x, y));
    }
    
    public TreeCell(Position2D position) {
        super();
        this.color = Color.GREEN;
        this.position = position;
        this.type = TreeCell.class.getSimpleName();
        this.price = 500.0;
    }
    
    @Override
    public Cell changeState(Cell actor) {
        if (actor.getType().equals(BurningCell.class.getSimpleName()) ) {
            return new BurningCell(this);
        }
        return this;
    }
    
    @Override
    public String toString() {
        return "T";
    }
}

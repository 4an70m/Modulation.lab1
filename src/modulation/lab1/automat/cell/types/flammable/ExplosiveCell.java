/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.cell.types.flammable;

import java.util.List;
import javafx.scene.paint.Color;
import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.cell.types.ActableCell;
import modulation.lab1.automat.cell.types.ActingCell;
import modulation.lab1.automat.cell.types.fireproof.BurningCell;
import modulation.lab1.automat.cell.types.fireproof.ExplosedCell;
import modulation.lab1.automat.util.Position2D;

/**
 *
 * @author Admin
 */
public class ExplosiveCell extends FlammableCell implements ActableCell{
    
    public ExplosiveCell() {
    }
    
    public ExplosiveCell(Integer x, Integer y) {
        this(new Position2D(x, y));
    }
    
    public ExplosiveCell(Position2D position) {
        super();
        this.color = Color.PINK;
        this.position = position;
        this.type = TreeCell.class.getSimpleName();
        this.price = 500.0;
    }

    @Override
    public Cell changeState(Cell actor) {
        if (actor.getType().equals(BurningCell.class.getSimpleName()) 
        ||  actor.getType().equals(ExplosedCell.class.getSimpleName()) ) {
            return new ExplosedCell(this.getPosition());
        }
        return this;
    }
    
    @Override
    public String toString() {
        return "E";
    }
}

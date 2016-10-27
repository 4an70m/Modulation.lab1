/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.cell.types.fireproof;

import javafx.scene.paint.Color;
import modulation.lab1.automat.cell.Cell;

/**
 *
 * @author Admin
 */
public class DeadCell extends FireproofCell {
    
    public DeadCell (Cell origin) {
        this.color = Color.BLACK;
        this.price = origin.getPrice();
        this.type = DeadCell.class.getSimpleName();
        this.position = origin.getPosition();
    }

    @Override
    public String toString() {
        return "D";
    }
    
}

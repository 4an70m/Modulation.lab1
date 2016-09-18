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
public class BurningCell extends Cell{

    public BurningCell(Cell origin) {
        this.color = Color.BLACK;
        this.price = 0.0;
        this.position = origin.getPosition();
        this.type = BurningCell.class.getSimpleName();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.cell.types.fireproof;

import javafx.scene.paint.Color;
import javax.swing.text.Position;
import modulation.lab1.automat.util.Position2D;

/**
 *
 * @author Admin
 */
public class LakeCell extends FireproofCell{
    
    public LakeCell (Position2D position) {
        this.color = Color.LIGHTBLUE;
        this.price = 0.0;
        this.type = LakeCell.class.getSimpleName();
        this.position = position;
    }
    
}

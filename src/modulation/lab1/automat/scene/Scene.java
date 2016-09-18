/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.scene;

import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.processor.CellGeneration;

/**
 *
 * @author Admin
 */
public class Scene extends javafx.scene.canvas.Canvas{
    
    public void drawCellGeneration (CellGeneration generation) {
        for (int i = 0; i < generation.getLenght(); i++) {
            for (int j = 0; j < generation.getWidth(); j++) {
                Cell drawingCell = generation.getGeneration()[i][j];
            }
        }
    }
    
}

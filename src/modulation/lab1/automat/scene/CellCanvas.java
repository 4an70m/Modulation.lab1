/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.generation.CellGeneration;

/**
 *
 * @author Admin
 */
public class CellCanvas {
    
    public static final int CELL_SIZE = 1;
    private final Canvas canvas;
    
    public CellCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    
    public void drawCellGeneration (CellGeneration generation) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        for (int i = 0; i < generation.getLenght(); i++) {
            for (int j = 0; j < generation.getWidth(); j++) {
                Cell drawingCell = generation.getGeneration()[i][j];
                context.setFill(drawingCell.getColor());
                context.fillRect(drawingCell.getPosition().getX(), 
                        drawingCell.getPosition().getY(), 
                        CELL_SIZE, 
                        CELL_SIZE);
            }
        }
    }
    
}

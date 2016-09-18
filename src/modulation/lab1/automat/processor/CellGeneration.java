/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.processor;

import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.cell.types.ActableCell;

/**
 *
 * @author Admin
 */
public class CellGeneration {
 
    private Cell[][] generation;
    private int lenght;
    private int width;
    private static final int SURROUNDING_CELLS_DEPTH = 11;
    
    public CellGeneration() {
        this.lenght = 0;
        this.width = 0;
        this.generation = new Cell[this.lenght][this.width];
    }

    public CellGeneration(Cell[][] generation) {
        this.generation = generation;
        this.lenght = generation.length;
        this.width = 0;
        if (generation[0] != null) {
            this.width = generation[0].length;
        }
    }
    
    public CellGeneration(CellGeneration generation) {
        this(generation.getGeneration());
    }
    
    public CellGeneration calculateGeneration() {
        CellGeneration result = new CellGeneration();
        Cell[][] newGeneration = new Cell[this.lenght][this.width];
        for (int i = 0; i < this.lenght; i++) {
            for (int j = 0; j < this.width; j++) {
                
                Cell curCell = this.generation[i][j];
                Cell[] newCellsState = this.processCell(curCell);
                
            }
        }
        return result;
    }
    
    protected Cell[] processCell(Cell curCell) {
        Cell[] result = new Cell[SURROUNDING_CELLS_DEPTH * SURROUNDING_CELLS_DEPTH];

        
        
        return result;
    }
    
    protected Cell[] getSurroundingCells (Cell curCell) {
        Cell[] result = new Cell[SURROUNDING_CELLS_DEPTH * SURROUNDING_CELLS_DEPTH];
        int x = curCell.getPosition().getX();
        int y = curCell.getPosition().getY();
        
        for (int i = x - SURROUNDING_CELLS_DEPTH / 2; i < x + SURROUNDING_CELLS_DEPTH / 2; i++) {
            for (int j = y - SURROUNDING_CELLS_DEPTH / 2; j < y + SURROUNDING_CELLS_DEPTH / 2; j++) {
                Cell cellToProcess = this.generation[i][j];
                if (cellToProcess instanceof ActableCell) {
                    ActableCell actableCell = (ActableCell) cellToProcess;
                    actableCell.act(result);
                } else {
                    continue;
                }
                if (i == x && j == y) {
                    
                }
            }
        }
    }
    
    public void setGeneration(Cell[][] generation) {
        this.generation = generation;
    }

    public Cell[][] getGeneration() {
        return generation;
    }
    
}

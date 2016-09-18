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
        Cell[] surroundingCells = this.getSurroundingCells(curCell);
        
        if (!(curCell instanceof ActableCell)) {
            return surroundingCells;
        }
        ActableCell actableCell = (ActableCell) curCell;
        result = actableCell.act(surroundingCells);
        return result;
    }

    protected Cell[] getSurroundingCells (Cell curCell) {
        Cell[] result = new Cell[SURROUNDING_CELLS_DEPTH * SURROUNDING_CELLS_DEPTH];
        int x = curCell.getPosition().getX();
        int y = curCell.getPosition().getY();
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int offsetX = i - SURROUNDING_CELLS_DEPTH / 2;
                int offsetY = j - SURROUNDING_CELLS_DEPTH / 2;
                int resultIndex = (i + 1) * j;
                result[resultIndex] = this.generation[offsetX][offsetY];
            }
        }
        return result;
    }
    
    public void setGeneration(Cell[][] generation) {
        this.generation = generation;
    }

    public Cell[][] getGeneration() {
        return generation;
    }

    public int getLenght() {
        return lenght;
    }

    public int getWidth() {
        return width;
    }
    
    
}

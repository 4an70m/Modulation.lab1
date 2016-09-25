/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.generation;

import java.util.ArrayList;
import java.util.List;
import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.cell.types.ActableCell;
import modulation.lab1.automat.cell.types.ActingCell;
import modulation.lab1.automat.cell.types.fireproof.BurningCell;

/**
 *
 * @author Admin
 */
public class CellGeneration {
 
    private Cell[][] generation;
    private int height;
    private int width;
    private static final int ACTUAL_CELLS_DEPTH = 1;
    private static final int SURROUNDING_CELLS_DEPTH = (ACTUAL_CELLS_DEPTH * 2 + 1) * (ACTUAL_CELLS_DEPTH * 2 + 1);
    
    public CellGeneration() {
        this.height = 0;
        this.width = 0;
        this.generation = new Cell[this.height][this.width];
    }

    public CellGeneration(Cell[][] generation) {
        this.generation = generation;
        this.height = generation.length;
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
        Cell[][] newGeneration = new Cell[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                
                Cell curCell = this.generation[i][j];
                List<Cell> newCellsState = this.processCell(curCell);
                if (newCellsState.isEmpty() && newGeneration[curCell.getPosition().getX()][curCell.getPosition().getY()] == null) {
                    newGeneration[curCell.getPosition().getX()][curCell.getPosition().getY()] = curCell;
                } else {
                    for(Cell cell : newCellsState) {
                        newGeneration[cell.getPosition().getX()][cell.getPosition().getY()] = cell;
                    }
                }
            }
        }
        result.setGeneration(newGeneration);
        return result;
    }
    
    protected List<Cell> processCell(Cell curCell) {
        List<Cell> result = new ArrayList<>();
        if (!(curCell instanceof ActingCell)) {
            return new ArrayList<>();
        }
        List<Cell> surroundingCells = this.getSurroundingCells(curCell);
        
        ActingCell actableCell = (ActingCell) curCell;
        result.addAll(actableCell.act(surroundingCells));
        return result;
    }

    protected List<Cell> getSurroundingCells (Cell curCell) {
        List<Cell> result = new ArrayList<>(); 
        int x = curCell.getPosition().getX();
        int y = curCell.getPosition().getY();
        int xMin = x - ACTUAL_CELLS_DEPTH < 0 ? x : x - ACTUAL_CELLS_DEPTH;
        int yMin = y - ACTUAL_CELLS_DEPTH < 0 ? y : y - ACTUAL_CELLS_DEPTH;
        int xMax = x + ACTUAL_CELLS_DEPTH > this.width - 1  ? this.width - 1   : x + ACTUAL_CELLS_DEPTH;
        int yMax = y + ACTUAL_CELLS_DEPTH > this.height - 1 ? this.height - 1  : y + ACTUAL_CELLS_DEPTH;
        
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin; j <= yMax; j++) {
                //if (i != x || j != y) {                    
                    result.add(this.generation[i][j]);
                //}
            }
        }
        return result;
    }
    
    public void setGeneration(Cell[][] generation) {
        this.height = generation.length;
        this.width = 0;
        if (generation[0] != null) {
            this.width = generation[0].length;
        }
        this.generation = generation;
    }

    public Cell[][] getGeneration() {
        return generation;
    }

    public int getLenght() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                result.append(this.generation[i][j] + ", ");
            }
            result.append("\n");
        }   
        return result.toString();
    }
}

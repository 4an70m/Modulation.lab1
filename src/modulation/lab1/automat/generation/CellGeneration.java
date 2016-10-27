/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.generation;

import java.util.ArrayList;
import java.util.List;
import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.cell.types.ActingCell;
import modulation.lab1.automat.cell.types.fireproof.BurningCell;
import modulation.lab1.automat.cell.types.fireproof.DeadCell;
import modulation.lab1.automat.cell.types.fireproof.ExplosedCell;
import modulation.lab1.settings.WindVariant;

/**
 *
 * @author Admin
 */
public class CellGeneration {
    
    private int width;
    private int height;
    private Cell[][] generation;
    private double generationDamagePrice;
    private static final int WIND_COEFICIENT = 10;
    private static final int ACTUAL_CELLS_DEPTH = 1;
    
    public CellGeneration() {
        this.height = 0;
        this.width = 0;
        this.generation = new Cell[this.height][this.width];
        this.generationDamagePrice = 0;
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
    
    public CellGeneration calculateGeneration(WindVariant variant) {
        this.generationDamagePrice = this.getGenerationDamagePrice();
        CellGeneration result = new CellGeneration();
        Cell[][] newGeneration = new Cell[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                
                Cell curCell = this.generation[i][j];
                List<Cell> newCellsState = this.processCell(curCell, variant);
                if (newCellsState.isEmpty() && newGeneration[curCell.getPosition().getX()][curCell.getPosition().getY()] == null) {
                    newGeneration[curCell.getPosition().getX()][curCell.getPosition().getY()] = curCell;
                } else {
                    for(Cell cell : newCellsState) {
                        newGeneration[cell.getPosition().getX()][cell.getPosition().getY()] = cell;
                    }
                }
            }
        }
        newGeneration = this.adjustNewGeneration(newGeneration, variant);
        result.setGeneration(newGeneration);
        return result;
    }
    
    protected List<Cell> processCell(Cell curCell, WindVariant variant) {
        return processCell(curCell, this.generation, variant);
    }
    
    protected List<Cell> processCell(Cell curCell, Cell[][] generation, WindVariant variant) {
        List<Cell> result = new ArrayList<>();
        if (!(curCell instanceof ActingCell)) {
            return new ArrayList<>();
        }
        List<Cell> surroundingCells = this.getSurroundingCells(curCell, variant, generation);
        
        ActingCell actableCell = (ActingCell) curCell;
        result.addAll(actableCell.act(surroundingCells));
        return result;
    }
    
    protected List<Cell> getSurroundingCells (Cell curCell, WindVariant variant, Cell[][] generation) {
        List<Cell> result = new ArrayList<>();
        SoroundCellsExpansion expansion = new SoroundCellsExpansion(curCell, variant, this.width, this.height);
        for (int i = expansion.xMin; i <= expansion.xMax; i++) {
            for (int j = expansion.yMin; j <= expansion.yMax; j++) {
                result.add(generation[i][j]);
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
                result.append(this.generation[i][j]).append(", ");
            }
            result.append("\n");
        }   
        return result.toString();
    }

    private Cell[][] adjustNewGeneration(Cell[][] newGeneration, WindVariant variant) {
        Boolean needRecalculate = false;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Cell curCell = newGeneration[i][j];
                if (curCell instanceof ExplosedCell) {
                    List<Cell> newCellsState = this.processCell(curCell, newGeneration, variant);
                    for (Cell cell : newCellsState) {
                        if (cell instanceof ExplosedCell){
                            needRecalculate = true;
                        }
                        newGeneration[cell.getPosition().getX()][cell.getPosition().getY()] = cell;
                    }
                }
                        
            }
        }
        if (needRecalculate) {
            return adjustNewGeneration(newGeneration, variant);
        }
        return newGeneration;
    }
    
    public double getGenerationDamagePrice() {
        return getGenerationDamagePrice(this.generation);
    }
    
    private double getGenerationDamagePrice(Cell[][] generation) {
        double result = 0;
         for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Cell curCell = generation[i][j];
                if (curCell instanceof BurningCell 
                ||  curCell instanceof DeadCell) {
                    result += curCell.getPrice();
                }
            }
        }
        return result;
    }
    
    private class SoroundCellsExpansion {
        public int xMin;
        public int yMin;
        public int xMax;
        public int yMax;
        public WindVariant variant;
        
        public SoroundCellsExpansion(Cell curCell, WindVariant variant, int width, int height) {
            this.variant= variant;
            int x = curCell.getPosition().getX();
            int y = curCell.getPosition().getY();
            this.calculateExpansion(x, y);
        }

        private void calculateExpansion(int x, int y) {
            this.xMin = this.calcMinX(x);
            this.yMin = this.calcMinY(y);// y - ACTUAL_CELLS_DEPTH < 0 ? y : y - ACTUAL_CELLS_DEPTH;
            this.xMax = this.calcMaxX(x);// x + ACTUAL_CELLS_DEPTH > width - 1  ? width - 1   : x + ACTUAL_CELLS_DEPTH;
            this.yMax = this.calcMaxY(y);// y + ACTUAL_CELLS_DEPTH > height - 1 ? height - 1  : y + ACTUAL_CELLS_DEPTH;
        }
        
        private int calcMinX(int x) {
            if(this.variant.direction.equals(WindVariant.Directions.W)
            || this.variant.direction.equals(WindVariant.Directions.NW)
            || this.variant.direction.equals(WindVariant.Directions.SW)){
                return (int) ((x - ACTUAL_CELLS_DEPTH) - (WIND_COEFICIENT * variant.speed) < 0 ? 0 : (x - ACTUAL_CELLS_DEPTH) - (WIND_COEFICIENT * variant.speed));   
            }
            return x - ACTUAL_CELLS_DEPTH < 0 ? x : x - ACTUAL_CELLS_DEPTH;
        }
        
        private int calcMinY(int y) {
            if (this.variant.direction.equals(WindVariant.Directions.S)
                    || this.variant.direction.equals(WindVariant.Directions.SW)
                    || this.variant.direction.equals(WindVariant.Directions.SE)) {
                return (int) ((y - ACTUAL_CELLS_DEPTH) - (WIND_COEFICIENT * variant.speed) < 0 ? 0 : (y - ACTUAL_CELLS_DEPTH) - (WIND_COEFICIENT * variant.speed));
            }
            return y - ACTUAL_CELLS_DEPTH < 0 ? y : y - ACTUAL_CELLS_DEPTH;
        }
        
        private int calcMaxX(int x) {
            if(this.variant.direction.equals(WindVariant.Directions.E)
            || this.variant.direction.equals(WindVariant.Directions.NE)
            || this.variant.direction.equals(WindVariant.Directions.SE)){
                return (int) ((x + ACTUAL_CELLS_DEPTH) + (WIND_COEFICIENT * variant.speed) > width - 1 ? width - 1 : (x + ACTUAL_CELLS_DEPTH) + (WIND_COEFICIENT * variant.speed));   
            }
            return x + ACTUAL_CELLS_DEPTH > width - 1  ? width - 1   : x + ACTUAL_CELLS_DEPTH;
        }
        
        private int calcMaxY(int y) {
            if(this.variant.direction.equals(WindVariant.Directions.N)
            || this.variant.direction.equals(WindVariant.Directions.NE)
            || this.variant.direction.equals(WindVariant.Directions.NW)){
                return (int) ((y + ACTUAL_CELLS_DEPTH) + (WIND_COEFICIENT * variant.speed) > height - 1 ? height - 1 : (y + ACTUAL_CELLS_DEPTH) + (WIND_COEFICIENT * variant.speed));   
            }
            return y + ACTUAL_CELLS_DEPTH > height - 1 ? height - 1  : y + ACTUAL_CELLS_DEPTH;
        }
    }
}

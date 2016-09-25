/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.generation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CellGenerationTimeline {
    
    public static final Integer NUM_OF_ITERATIONS = 100;
    private final List<CellGeneration> calculatedGenerations;
    
    public CellGenerationTimeline(CellGeneration initialGeneration) {
       this.calculatedGenerations = new ArrayList<>();
       this.calculatedGenerations.add(initialGeneration);
    }
    
    public void calculateTimelines() {
        calculateTimelines(NUM_OF_ITERATIONS);
    }
    
    public void calculateTimelines(Integer numberOfIterations) {
        for (int i = 0; i < numberOfIterations; i++) {
            CellGeneration currentCellGeneration = this.calculatedGenerations.get(i);
            CellGeneration nextGeneration = currentCellGeneration.calculateGeneration();
            this.calculatedGenerations.add(nextGeneration);
        }
    }
    
    public CellGeneration get(Integer i) {
        return this.calculatedGenerations.get(i);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (CellGeneration cg : calculatedGenerations) {
            result.append("Cell Generation (").append(i++).append(")\n");
            result.append(cg.toString());
            result.append("\n");
        }
        return result.toString();
    }
    
    
}

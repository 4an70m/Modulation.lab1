/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.processor;

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
        for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
            CellGeneration currentCellGeneration = this.calculatedGenerations.get(i);
            CellGeneration nextGeneration = currentCellGeneration.calculateGeneration();
            this.calculatedGenerations.add(nextGeneration);
        }
    }
    
    public CellGeneration get(Integer i) {
        return this.calculatedGenerations.get(i);
    }
    
}

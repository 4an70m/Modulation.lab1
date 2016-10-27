/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import modulation.lab1.settings.WindGenerator;
import modulation.lab1.settings.WindVariant;

/** 
 *
 * @author Admin
 */
public class CellGenerationTimeline {
    
    public static final Integer NUM_OF_ITERATIONS = 100;
    private final List<CellGeneration> calculatedGenerations;
    private Map<Integer,Double> costsList;
    
    public CellGenerationTimeline(CellGeneration initialGeneration) {
       this.calculatedGenerations = new ArrayList<>();
       this.calculatedGenerations.add(initialGeneration);
    }
    
    public void calculateTimelines() {
        calculateTimelines(NUM_OF_ITERATIONS);
    }
    
    public void calculateTimelines(Integer numberOfIterations) {
        numberOfIterations = (int) WindGenerator.getInstance().totalTime / 10;
        Map<Integer, Double> costsList = new LinkedHashMap<>();
        Double totalCost = 0.0;
        Double totalTime = 0.0;
        WindVariant prevVariant = null;
        for (int i = 0; i < numberOfIterations; i++) {
            System.out.println("Number of iteration: " + i + "\n");
            
            CellGeneration currentCellGeneration = this.calculatedGenerations.get(i);
            int time = (int) ((WindGenerator.getInstance().totalTime / numberOfIterations ) * i);
            WindVariant variant = WindGenerator.getInstance().getWindVariantByTime(time);
                
            CellGeneration nextGeneration = currentCellGeneration.calculateGeneration(variant);
            this.calculatedGenerations.add(nextGeneration);
            
            totalCost += nextGeneration.getGenerationDamagePrice();
            if (prevVariant == null || variant.variant != prevVariant.variant) {
                totalTime += (variant.time * 60.0);
                System.out.println(totalTime.intValue());
                System.out.println(totalCost);
                costsList.put(totalTime.intValue(), totalCost);
            }
            prevVariant = variant;
        }
        this.costsList = costsList;
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
    
    public Map<Integer, Double> getTimelinesDamagePrice() {
        return this.costsList;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1;

import java.net.URL;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.cell.types.fireproof.BurningCell;
import modulation.lab1.automat.cell.types.flammable.BuildingCell;
import modulation.lab1.automat.cell.types.flammable.ExplosiveCell;
import modulation.lab1.automat.cell.types.flammable.TreeCell;
import modulation.lab1.automat.generation.CellGeneration;
import modulation.lab1.automat.generation.CellGenerationTimeline;
import modulation.lab1.automat.scene.CellCanvas;
import modulation.lab1.settings.WindGenerator;

/**
 *
 * @author Admin
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Slider slider;
    
    @FXML
    private Canvas canvas;
    
    @FXML
    private LineChart chart; 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int sizex = (int)canvas.getWidth();
        final CellCanvas cc = new CellCanvas(canvas);
        final Cell[][] gen = new Cell[sizex][sizex];
        for (int i = 0; i < sizex; i++) {
            for (int j = 0; j < sizex; j++) {
                gen[i][j] = new TreeCell(i, j);
            }
        }
        gen[sizex / 2][sizex / 2] = new BurningCell(gen[sizex / 2][sizex / 2]);
        for (int i = sizex / 2 - 8; i < sizex / 2 - 3; i++) {
            for (int j = sizex / 2 - 8; j < sizex / 2 - 3; j++) {
                gen[i][j] = new ExplosiveCell(i, j);
            }
        }
        
        for (int i = sizex / 2 - 50; i < sizex / 2 - 30; i++) {
            for (int j = sizex / 2 - 50; j < sizex / 2 - 30; j++) {
                gen[i][j] = new BuildingCell(i, j);
            }
        }
        
        final CellGenerationTimeline cgt = new CellGenerationTimeline(new CellGeneration(gen));
        cgt.calculateTimelines(72);
        System.out.println("done!");
        
        
        XYChart.Series series = new XYChart.Series();
        for (Entry<Integer, Double> entry : cgt.getTimelinesDamagePrice().entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }    
        chart.getData().add(series);
        
        
        slider.valueProperty().addListener(new ChangeListener<Number> () {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int oldValueD = oldValue.intValue();
                int newValueD = newValue.intValue();
                if (oldValueD != newValueD) {
                    cc.drawCellGeneration(cgt.get(newValue.intValue()));
                    System.out.println("Piu " + oldValue.intValue());
                    System.out.println("Kek " + newValue.intValue());
                }
            }
        }) ;
        
        
    }    
    
}

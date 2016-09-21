/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.cell.types.flammable.TreeCell;
import modulation.lab1.automat.processor.CellGeneration;
import modulation.lab1.automat.scene.CellCanvas;

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
        slider.valueProperty().addListener(new ChangeListener<Number> () {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Piu " + oldValue.intValue());
                System.out.println("Kek " + newValue.intValue());
            }
        }) ;
        
        CellCanvas cc = new CellCanvas(canvas);
        
        Cell[][] gen = new Cell[200][200];
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                gen[i][j] = new TreeCell(i, j);
            }
        }
        cc.drawCellGeneration(new CellGeneration(gen));
    }    
    
}

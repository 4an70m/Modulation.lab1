/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1;

import java.util.Arrays;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modulation.lab1.automat.cell.Cell;
import modulation.lab1.automat.cell.types.fireproof.BurningCell;
import modulation.lab1.automat.cell.types.flammable.TreeCell;
import modulation.lab1.automat.generation.CellGeneration;
import modulation.lab1.automat.generation.CellGenerationTimeline;
import modulation.lab1.automat.scene.CellCanvas;

/**
 *
 * @author Admin
 */
public class ModulationLab1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        int sizex = 500;
        final Cell[][] gen = new Cell[sizex][sizex];
        for (int i = 0; i < sizex; i++) {
            for (int j = 0; j < sizex; j++) {
                gen[i][j] = new TreeCell(i, j);
            }
        }
        gen[sizex / 2][sizex / 2] = new BurningCell(gen[sizex / 2][sizex / 2]);
        final CellGenerationTimeline cgt = new CellGenerationTimeline(new CellGeneration(gen));
        cgt.calculateTimelines(100);
        //System.out.println(cgt);
        System.out.println("done!");
    }
    
}

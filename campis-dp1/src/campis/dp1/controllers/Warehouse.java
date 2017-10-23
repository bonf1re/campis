/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author sergio
 */
public class Warehouse implements Initializable {
    private Main main;
    private Canvas warehouseCanvas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void goWarehouseMainView() throws IOException{
        main.showWarehouseMainVew();
    }
    
    @FXML
    private void goWarehouseCreateView() throws IOException{
        main.showWarehouseCreateView();
    }

    @FXML
    private void goSaveWarehouse() throws IOException{
        
    }
    
    @FXML
    private void goWarehouseVisualizeView() throws IOException{
        /* Canvas initialization */ 
        GraphicsContext gc = 
          warehouseCanvas.getGraphicsContext2D();
        draw( gc );
        /* End canvas initialization */
        main.showWarehouseVisualizeView();
    }

    private void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillOval(50, 100, 200, 200);
        gc.setFill(Color.RED);
        gc.fillRect(300, 100, 200, 200);
    }
    
}

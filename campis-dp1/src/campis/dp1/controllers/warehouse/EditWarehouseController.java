/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.Main;
import campis.dp1.models.Rack;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

/**
 * FXML Controller class
 *
 * @author Gina Bustamante
 */
public class EditWarehouseController implements Initializable {
    private Main main;
    Integer id;
    private ArrayList<Rack> rackList = new ArrayList<>();
    
        
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField lengthField;
    @FXML
    private JFXTextField widthField;
    @FXML
    private JFXComboBox statusCb;
    
    @FXML
    private Canvas mapCanvas;
    
    @FXML
    private void goListWarehouse() throws IOException{
        main.showListWarehouse();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

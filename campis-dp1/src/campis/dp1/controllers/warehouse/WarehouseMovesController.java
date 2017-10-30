/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author sergio
 */
public class WarehouseMovesController implements Initializable {
    private Main main;
    private int warehouse_id;
    
    @FXML
    private void goListWarehouse() throws IOException{
        main.showListWarehouse();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.warehouse_id=ContextFX.getInstance().getId();
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import javafx.fxml.FXML;
import campis.dp1.Main;
import java.io.IOException;

/**
 *
 * @author Gina Bustamante
 */
public class WarehouseSidebarController {
    private Main main;
    
    @FXML
     private void goWarehouseMainView() throws IOException{
        main.showWarehouseMainView();
    }
    
    @FXML
    private void goListProduct() throws IOException {
        main.showListProduct();
    }
     
    @FXML
    private void goListRacks() throws IOException {
        main.showListRacks();
    }
}

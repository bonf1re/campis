/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.ContextFX;
import javafx.fxml.FXML;
import campis.dp1.Main;
import campis.dp1.models.Permission;
import campis.dp1.models.View;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author Gina Bustamante
 */
public class WarehouseSidebarController implements Initializable {
    private Main main;
    private int id_role;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            id_role = (ContextFX.getInstance().getUser().getId_role());
        } catch(NullPointerException e) {
        }
    }
    
    @FXML
    private void goWhList() throws IOException{
        main.showWhList();
    }
    
    @FXML
    private void goListProduct() throws IOException {
        View view = View.getView("products");
        if (Permission.canVisualize(id_role, view.getId_view()))
            main.showListProduct();
    }
    
    @FXML
    private void goListProductTypes() throws IOException {
        main.showListProductType();
    }
     
    @FXML
    private void goListRacks() throws IOException {
        main.showListRacks();
    }
}

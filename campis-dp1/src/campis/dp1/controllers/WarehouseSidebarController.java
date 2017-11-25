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
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;

/**
 *
 * @author Gina Bustamante
 */
public class WarehouseSidebarController implements Initializable {
    private Main main;
    private int id_role;
    @FXML
    private JFXButton whButton;

    @FXML
    private JFXButton productButton;

    @FXML
    private JFXButton tipoProductButton;
    
    @FXML
    private JFXButton entriesButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            id_role = (ContextFX.getInstance().getUser().getId_role());
            View whView = View.getView("warehouse");
            View productView = View.getView("products");
            View ptView = View.getView("product_types");
            View entryView = View.getView("entries_warehouse");
            if (!Permission.canVisualize(id_role, whView.getId_view()))
                whButton.setVisible(false);
            if (!Permission.canVisualize(id_role, productView.getId_view()))
                productButton.setVisible(false);
            if (!Permission.canVisualize(id_role, ptView.getId_view()))
                tipoProductButton.setVisible(false);
            if (!Permission.canVisualize(id_role, entryView.getId_view()))
                entriesButton.setVisible(false);
        } catch(NullPointerException e) {
        }
    }
    
    @FXML
    private void goWhList() throws IOException {
        main.showWhList();
    }
    
    @FXML
    private void goListProduct() throws IOException {
        main.showListProduct();
    }
    
    @FXML
    private void goListProductTypes() throws IOException {
        main.showListProductType();
    }
    
    @FXML
    private void goEntryMoveList() throws IOException {
        main.showEntryMoveList();
    }
}

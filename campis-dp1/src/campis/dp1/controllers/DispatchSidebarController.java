/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import campis.dp1.models.Permission;
import campis.dp1.models.View;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;

/**
 * FXML Controller class
 *
 * @author david
 */
public class DispatchSidebarController implements Initializable {
    private Main main;
    private int id_role;
    
    @FXML
    private JFXButton depButton;
    
    @FXML
    private JFXButton delButton;
    
    @FXML
    private JFXButton batchxdispButton;
    
    @FXML
    private void goDepartureMoveList() throws IOException {
        main.showWhDepartureMoveList();
    }
    
    @FXML
    private void goDeliveryList() throws IOException {
        //TODO
        //main.showDeliveryList();

    }
    
    @FXML
    private void goListDispatchBatch() throws IOException {
        main.showListDispatchBatch();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            id_role = (ContextFX.getInstance().getUser().getId_role());
            View entView = View.getView("entries_dispatch");
            View depView = View.getView("departures_dispatch");
            if (!Permission.canVisualize(id_role, entView.getId_view()))
                delButton.setVisible(false);
            if (!Permission.canVisualize(id_role, depView.getId_view()))
                depButton.setVisible(false);
        } catch(NullPointerException e) {
        }
    }   
}

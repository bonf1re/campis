/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Permission;
import campis.dp1.models.View;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;


/**
 *
 * @author Gina Bustamante
 */
public class SecuritySidebarController implements Initializable {
    private Main main;
    private int id_role;
    @FXML
    private JFXButton userButton;

    @FXML
    private JFXButton roleButton;

    @FXML
    private JFXButton logButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            id_role = (ContextFX.getInstance().getUser().getId_role());
            View userView = View.getView("users");
            View roleView = View.getView("roles");
            View logView = View.getView("log");
            if (!Permission.canVisualize(id_role, userView.getId_view()))
                userButton.setVisible(false);
            if (!Permission.canVisualize(id_role, roleView.getId_view()))
                roleButton.setVisible(false);
            if (!Permission.canVisualize(id_role, logView.getId_view()))
                logButton.setVisible(false);
        } catch(NullPointerException e) {
        }
    } 

    @FXML
    private void goListUser() throws IOException {
        main.showListUser();
    }
    
    @FXML
    private void goListRoles() throws IOException {
        main.showListRoles();
    }
}

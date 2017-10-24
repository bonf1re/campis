/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.Main;
import campis.dp1.models.RoleDAO;
import campis.dp1.models.View;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Marco
 */
public class RolesController implements Initializable {
    private Main main;
    @FXML
    private JFXTextField description;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private void goListRoles() throws IOException {
        main.showListRoles();
    }

    @FXML
    private void goCreateRoles() throws IOException {
        main.showCreateRoles();
    }
    
    @FXML
    private void insertRole (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        try {
                RoleDAO.insertRole(description.getText());
                this.goListRoles();
            //resultArea.setText("Role inserted! \n");
        } catch (SQLException e) {
            //resultArea.setText("Problem occurred while inserting employee " + e);
            throw e;
        }
    }   
}

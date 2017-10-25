/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.Main;
import campis.dp1.models.UserDAO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Marco
 */
public class UsersController implements Initializable {
    private Main main;
    
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField lastNameField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXComboBox roleField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    private void goListUser() throws IOException {
        main.showListUser();
    }

    @FXML
    private void goCreateUser() throws IOException {
        main.showCreateUser();
    }

    @FXML
    private void insertUser (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        //String role = roleField.getEditor().getText();

        try {
            UserDAO.insertRole(nameField.getText(), lastNameField.getText(), usernameField.getText(), emailField.getText(), passwordField.getText(), 2);
            this.goListUser();
            //resultArea.setText("Role inserted! \n");
        } catch (SQLException e) {
            //resultArea.setText("Problem occurred while inserting employee " + e);
            throw e;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.Main;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Marco
 */
public class LoginController {
    Main main;
    @FXML
    private AnchorPane fondo_login;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button btnLogin;
    
    public void Authenticate() throws IOException{
        //algo
        main.showTopMenu();
    }
}

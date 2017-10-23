/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Marco
 */
public class Users implements Initializable {
    private Main main;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void goListUser() throws IOException {
        main.showListUser();
    }
    @FXML

    private void goCreateUser() throws IOException {
        main.showCreateUser();
    }
}

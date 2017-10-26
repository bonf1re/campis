/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.roles;

import campis.dp1.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Gina Bustamante
 */
public class ListRoleController implements Initializable{
    private Main main;
    
    @FXML
    private void goCreateRole() throws IOException {
        main.showNewRole();
    } 
    
    @FXML
    private void goEditRole() throws IOException {
        main.showEditRole();
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
}

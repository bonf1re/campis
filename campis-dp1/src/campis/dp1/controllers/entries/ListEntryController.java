/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.entries;

import campis.dp1.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ListEntryController implements Initializable {
    
    private Main main;
    
    @FXML
    private void goVisualizeEntry() throws IOException {
        main.showVisualizeEntry();
    }
    
    @FXML
    private void goNewEntry() throws IOException {
        main.showNewEntry();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

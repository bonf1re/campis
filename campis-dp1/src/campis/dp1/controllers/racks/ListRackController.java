/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.racks;

import campis.dp1.Main;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author Gina Bustamante
 */
public class ListRackController {
    private Main main;
    
    @FXML
    private void goNewRack() throws IOException {
        main.showNewRack();
    }
    
    @FXML
    private void goEditRack() throws IOException {
        main.showEditRack();
    }
}

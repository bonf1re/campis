/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.requestOrder;

import campis.dp1.Main;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author Eddy
 */
public class ListController {
    
    Main main;
    
    @FXML
    private void goCreateRequestOrder() throws IOException {
        main.showCreateRequestOrder();
    }
}

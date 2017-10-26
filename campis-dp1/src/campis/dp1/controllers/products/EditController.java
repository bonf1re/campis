/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.products;

import campis.dp1.Main;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author Eddy
 */
public class EditController {
    private Main main;
    
    @FXML
    private void goListProduct() throws IOException {
        main.showListProduct();
    }
}

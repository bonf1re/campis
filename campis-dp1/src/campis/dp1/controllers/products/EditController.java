/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.products;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Eddy
 */
public class EditController implements Initializable{
    
    @FXML
    private void goListProduct() throws IOException {
        main.showListProduct();
    }
    private Main main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(ContextFX.getInstance().getId());
    }
}

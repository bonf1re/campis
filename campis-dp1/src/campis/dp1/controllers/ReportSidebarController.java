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
 * @author david
 */
public class ReportSidebarController implements Initializable {

    private Main main;
    
    @FXML
    private void goKardexReport() throws IOException {
        main.showKardexReportConf();
    }
    
    @FXML
    private void goExpirationReport() throws IOException {
        main.showExpirationReportConf();
    }
    
    @FXML
    private void goStocksReport() throws IOException {
        main.showStocksReportConf();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

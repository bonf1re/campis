/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Permission;
import campis.dp1.models.View;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ReportSidebarController implements Initializable {
    private Main main;
    private int id_role;
    @FXML
    private JFXButton karButton;

    @FXML
    private JFXButton expButton;

    @FXML
    private JFXButton stockButton;
    
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

    @FXML
    private void goBills() throws IOException {
        main.showBillList();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            id_role = (ContextFX.getInstance().getUser().getId_role());
            View karView = View.getView("users");
            View expView = View.getView("roles");
            View stockView = View.getView("log");
            if (!Permission.canVisualize(id_role, karView.getId_view()))
                karButton.setVisible(false);
            if (!Permission.canVisualize(id_role, expView.getId_view()))
                expButton.setVisible(false);
            if (!Permission.canVisualize(id_role, stockView.getId_view()))
                stockButton.setVisible(false);
        } catch(NullPointerException e) {
        }
    }
}

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
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

/**
 *
 * @author Marco
 */
public class CommerceSidebarController implements Initializable {
    private Main main;
    private int id_role;
     @FXML
    private JFXButton roButton;

    @FXML
    private JFXButton scButton;

    @FXML
    private JFXButton rsButton;

    @FXML
    private JFXButton cliButton;

    @FXML
    private JFXButton freButton;

    @FXML
    private JFXButton comButton;

    @FXML
    private JFXButton refButton;
    
    @FXML
    private JFXButton supButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            id_role = (ContextFX.getInstance().getUser().getId_role());
            View roView = View.getView("request_orders");
            View scView = View.getView("sale_conditions");
            View rsView = View.getView("request_statuses");
            View cliView = View.getView("clients");
            //View supView = View.getView("suppliers");
            View freView = View.getView("districts");
            View comView = View.getView("complaints");
            View refView = View.getView("refunds");
            if (!Permission.canVisualize(id_role, roView.getId_view()))
                roButton.setVisible(false);
            if (!Permission.canVisualize(id_role, scView.getId_view()))
                scButton.setVisible(false);
            if (!Permission.canVisualize(id_role, rsView.getId_view()))
                rsButton.setVisible(false);
            if (!Permission.canVisualize(id_role, cliView.getId_view()))
                cliButton.setVisible(false);
            /*if (!Permission.canVisualize(id_role, supView.getId_view()))
                supButton.setVisible(false);*/
            if (!Permission.canVisualize(id_role, comView.getId_view()))
                comButton.setVisible(false);
            if (!Permission.canVisualize(id_role, refView.getId_view()))
                refButton.setVisible(false);
            if (!Permission.canVisualize(id_role, freView.getId_view()))
                freButton.setVisible(false);
        } catch(NullPointerException e) {
        }
    }

    @FXML
    private void goListRequestOrder() throws IOException{
        main.showListRequestOrder();
    }

    @FXML
    private void goListClient() throws IOException {
        main.showListClient();
    }

    @FXML
    private void goListSaleConditions() throws IOException{
        main.showListSaleConditions();
    }
    
    @FXML
    private void goListRequestStatuses() throws IOException {
        main.showListRequestStatuses();
    }
    
    @FXML
    private void goListFreights() throws IOException {
        main.showListFreights();
    }


    @FXML
    private void goListComplaint() throws IOException {
        main.showListComplaint();
    }

    @FXML
    private void goListRefund() throws IOException {
        main.showListRefund();
    }

    @FXML
    private void goListSupplier(ActionEvent event) throws IOException {
        main.showListSupplier();
    }

}

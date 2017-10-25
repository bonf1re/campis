/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.Main;
import campis.dp1.models.Vehicle;
import campis.dp1.models.VehicleDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author david
 */
public class CreateVehicleController implements Initializable {

    
    private Main main;
    private ActionEvent actionEvent;
    
    @FXML
    private void goListVehicle() throws IOException, SQLException, ClassNotFoundException {
        //fillTableProd(actionEvent);
        main.showListVehicle();
    }
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField txtFieldMxWeight;

    @FXML
    private JFXTextField txtFieldMxSpeed;

    @FXML
    private RadioButton rdButtonY;

    @FXML
    private RadioButton rdButtonN;

    @FXML
    private JFXComboBox<String> cmBoxWarhs;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmBoxWarhs.getItems().addAll("A","B","C","D","E");
    }    
    
    @FXML
    private void insertVehicle (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        try{
            
            VehicleDAO.insertVehicle(txtFieldMxWeight.getText(), txtFieldMxSpeed.getText(), "true", "1");
            System.out.println("Vehicle inserted! \n");
            this.goListVehicle();
        }catch (SQLException e){
            System.out.println("Error in insert : " + e);
        }
    }
}

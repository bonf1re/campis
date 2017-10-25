/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import campis.dp1.models.Vehicle;
import campis.dp1.models.VehicleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ListVehicleController implements Initializable {

    @FXML
    private TableView tableVehicle;
    @FXML
    private TableColumn<Vehicle, Integer>  idVehicleColumn;
    @FXML
    private TableColumn<Vehicle, Float>  maxWeightColumn;
    @FXML
    private TableColumn<Vehicle, Boolean> activeColumn;
    @FXML
    private TableColumn<Vehicle, Integer> maxSpeedColumn;
    @FXML
    private TableColumn<Vehicle, String> warehouseColumn;
    @FXML
    private Button addVehicleButton;
    @FXML
    private Button okButton;
    
    private ObservableList<Vehicle> data;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idVehicleColumn.setCellValueFactory(new PropertyValueFactory<>("idVehicle"));
        maxWeightColumn.setCellValueFactory(new PropertyValueFactory<>("maxWeight"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("maxSpeed"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("warehouse"));
        loadTable();
    }    
    
    public void loadTable(){
        try{
            data = VehicleDAO.searchVehicles();
            
        }
        catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
        
        
    }
}

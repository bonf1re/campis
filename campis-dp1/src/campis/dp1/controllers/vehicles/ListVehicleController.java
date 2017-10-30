/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.vehicles;

import campis.dp1.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import campis.dp1.models.Vehicle;
import campis.dp1.models.VehicleDisplay;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ListVehicleController implements Initializable {
    private Main main;
    private int selected_id;
    private ObservableList<Vehicle> vehiculos;
    private ObservableList<VehicleDisplay> vehiculosView;
    
        @FXML
    private TableView<VehicleDisplay> tableVehicle;

    @FXML
    private TableColumn<VehicleDisplay, Integer> idVehicleColumn;

    @FXML
    private TableColumn<VehicleDisplay, Double> maxWeightColumn;

    @FXML
    private TableColumn<VehicleDisplay, Integer> maxSpeedColumn;

    @FXML
    private TableColumn<VehicleDisplay, String> activeColumn;

    @FXML
    private TableColumn<VehicleDisplay, String> warehouseColumn;
    
    
    @FXML
    private TableColumn<VehicleDisplay, String> plateColumn;

    @FXML
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;
    
    @FXML
    private void goCreateVehicle() throws IOException {
        main.showNewVehicle();
    } 
    
    @FXML
    private void goEditVehicle() throws IOException {
        main.showEditVehicle();
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableVehicle.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.idProperty().getValue().intValue();
            }
        );
        try {
            idVehicleColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
            maxWeightColumn.setCellValueFactory(cellData -> cellData.getValue().maxWeightProperty().asObject());
            maxSpeedColumn.setCellValueFactory(cellData -> cellData.getValue().speedProperty().asObject());
            activeColumn.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
            warehouseColumn.setCellValueFactory(cellData -> cellData.getValue().warehouseProperty());
            plateColumn.setCellValueFactory(cellData -> cellData.getValue().plateProperty());
            /**/
            cargarData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ObservableList<Vehicle> getVehicles() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Vehicle.class);
        List lista = criteria.list();
        ObservableList<Vehicle> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Vehicle) lista.get(i));
        }
        sessionFactory.close();
        return returnable;
    }
    
    
    private void cargarData() throws SQLException, ClassNotFoundException {
        vehiculos = FXCollections.observableArrayList();
        vehiculosView = FXCollections.observableArrayList();
        vehiculos = getVehicles();
        for (int i = 0; i < vehiculos.size(); i++) {
            VehicleDisplay veh = new VehicleDisplay(vehiculos.get(i).getId_vehicle(), vehiculos.get(i).getMax_weight(),
                    vehiculos.get(i).getSpeed(), vehiculos.get(i).isActive(), String.valueOf(vehiculos.get(i).getId_warehouse()),
                    vehiculos.get(i).getPlate());
            vehiculosView.add(veh);
        }
        tableVehicle.setItems(null);
        tableVehicle.setItems(vehiculosView);
    }
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.vehicles;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Role;
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
import campis.dp1.models.Warehouse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ListVehicleController implements Initializable {
    private Main main;
    private int selected_id;
    private int warehouse_id;
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
    private TableColumn<VehicleDisplay, String> plateColumn;
  
    @FXML
    private void goCreateVehicle() throws IOException {
        ContextFX.getInstance().setId(this.warehouse_id);
        main.showNewVehicle();
    } 
    
    @FXML
    private void goEditVehicle() throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showEditVehicle();
        }
    }
    
    @FXML
    private void goWhList() throws IOException{
        main.showWhList();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        this.warehouse_id = ContextFX.getInstance().getId();
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
        criteria.add(Restrictions.eq("id_warehouse",this.warehouse_id));
        List lista = criteria.list();
        ObservableList<Vehicle> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Vehicle) lista.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }
    
    private void cargarData() throws SQLException, ClassNotFoundException {
        vehiculos = FXCollections.observableArrayList();
        vehiculosView = FXCollections.observableArrayList();
        vehiculos = getVehicles();

        for (int i = 0; i < vehiculos.size(); i++) {
 
            VehicleDisplay veh = new VehicleDisplay(vehiculos.get(i).getId_vehicle(), vehiculos.get(i).getMax_weight(),
                    vehiculos.get(i).getSpeed(), vehiculos.get(i).isActive(),vehiculos.get(i).getPlate());
            vehiculosView.add(veh);
        }
        tableVehicle.setItems(null);
        tableVehicle.setItems(vehiculosView);
    }
 
    @FXML
    private void deleteVehicle(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            Integer id_vehicle = ContextFX.getInstance().getId();
            deleteSelectedVehicle(selected_id);
            for (int i = 0; i < vehiculos.size(); i++) {
                if(vehiculos.get(i).getId_vehicle().compareTo(id_vehicle) == 0){
                    vehiculos.remove(i);
                }
            }
            cargarData();
        }
    }
    
    private void deleteSelectedVehicle(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Vehicle.class);
        Vehicle veh = new Vehicle();
        veh.setId_vehicle(cod);
        session.delete(veh);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}

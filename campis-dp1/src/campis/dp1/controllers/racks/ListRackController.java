/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.racks;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.controllers.products.ListController;
import campis.dp1.models.Rack;
import campis.dp1.models.RackDisplay;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import campis.dp1.models.Permission;
import campis.dp1.models.View;
import campis.dp1.models.WarehouseZone;
import javafx.scene.control.Alert;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gina Bustamante
 */
public class ListRackController implements Initializable{
    private Main main;
    private ObservableList<Rack> racks;
    private ObservableList<RackDisplay> racksView;
    private int selected_id;
    private int id_role;
    private int warehouse_id;
    private ObservableList<WarehouseZone> zones;

    @FXML
    private void goNewRack() throws IOException {
        ContextFX.getInstance().setId(warehouse_id);
        main.showNewRack();
    }

    @FXML
    private void goEditRack(ActionEvent event) throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showEditRack();
        }
    }
    
    @FXML
    private void goWhList() throws IOException{
        main.showWhList();
    }

    @FXML
    private TableView<RackDisplay> tablaRacks;
    @FXML
    private TableColumn<RackDisplay, Integer> idCol;
    @FXML
    private TableColumn<RackDisplay, Integer> numColumnsCol;
    @FXML
    private TableColumn<RackDisplay, Integer> numFloorsCol;
    @FXML
    private TableColumn<RackDisplay, Integer> orientationCol;
    @FXML
    private Button createButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;
    
    private ObservableList<Rack> getRacks() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rack.class);
        criteria.add(Restrictions.eq("id_warehouse",this.warehouse_id));
        List lista = criteria.list();
        ObservableList<Rack> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Rack)lista.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }
          
    private void loadData() throws SQLException, ClassNotFoundException  {
        racks = FXCollections.observableArrayList();
        racksView = FXCollections.observableArrayList();
        racks = getRacks();
        
        for (int i = 0; i < racks.size(); i++) {
            
            RackDisplay r = new RackDisplay(racks.get(i).getId_rack(),                        
                                            racks.get(i).getPos_x(),
                                            racks.get(i).getPos_y(),
                                            racks.get(i).getN_columns(),
                                            racks.get(i).getN_floors(),
                                            racks.get(i).getOrientation());
            racksView.add(r);
        }
        
        tablaRacks.setItems(null);
        tablaRacks.setItems(racksView);  
    }
    
    private void deleteRack(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rack.class);
        Rack r = new Rack();
        r.setId_rack(cod);
        session.delete(r);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
    
    private boolean verifyZones() {
        //Debes buscar en la tabla zones, si es que las zonas para ese racks... todas etsan vacias 
        zones = FXCollections.observableArrayList();
        zones = getZones();
        
        for (int i = 0; i < zones.size(); i++) {
            System.out.println(zones.get(i).isFree());
            if (!zones.get(i).isFree()) return false;
        }
        
        return true;    
    }
    
     private ObservableList<WarehouseZone> getZones() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(WarehouseZone.class);
        criteria.add(Restrictions.eq("id_rack",this.selected_id));
        List lista = criteria.list();
        //ArrayList<WarehouseZone> zonesList = new ArrayList<>();
        ObservableList<WarehouseZone> zonesList;
        zonesList = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            zonesList.add((WarehouseZone)lista.get(i));
        }
        session.close();
        sessionFactory.close();
        return zonesList;   
     }
     
    @FXML
    private void deleteRack(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            Integer id_rack = ContextFX.getInstance().getId();
            
            //verificamos que todas las zonas esten vacias 
            boolean flagZones = verifyZones();
            
            if (flagZones){
                deleteRack(selected_id);
                for (int i = 0; i < racks.size(); i++) {
                    if(racks.get(i).getId_rack().compareTo(id_rack) == 0){
                        racks.remove(i);
                    }
                }
                loadData();
            } else {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se pudo eliminar el rack porque no todas las zonas estan vacías");
                alert.showAndWait();
            }            
        }
    }
    
     
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        ContextFX.getInstance().modifyValidation(createButton, editButton, deleteButton, id_role, "racks");
        this.warehouse_id = ContextFX.getInstance().getId();
        tablaRacks.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue == null) return;
                this.selected_id = newValue.id_rackProperty().getValue();
            }
        );
        
        try {
            idCol.setCellValueFactory(cellData -> cellData.getValue().id_rackProperty().asObject());
            numColumnsCol.setCellValueFactory(cellData -> cellData.getValue().n_columnsProperty().asObject());
            numFloorsCol.setCellValueFactory(cellData -> cellData.getValue().n_floorsProperty().asObject());
            orientationCol.setCellValueFactory(cellData -> cellData.getValue().orientationProperty().asObject());
            
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}

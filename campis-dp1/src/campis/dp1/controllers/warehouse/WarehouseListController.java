/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Warehouse;
import campis.dp1.models.WarehouseDisplay;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.reflection.ClassLoadingException;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Gina Bustamante
 */
public class WarehouseListController implements Initializable {
    private Main main;
    private ObservableList<Warehouse> warehouses;
    private ObservableList<WarehouseDisplay> warehousesView;
    private int selected_id;
    
    @FXML
    private TableView<WarehouseDisplay> warehouseTable;
    @FXML
    private TableColumn<WarehouseDisplay, Integer> idCol;
    @FXML
    private TableColumn<WarehouseDisplay, String> nameCol;
    @FXML
    private TableColumn<WarehouseDisplay, Integer> lengthCol;
    @FXML
    private TableColumn<WarehouseDisplay, Integer> widthCol;
    @FXML
    private TableColumn<WarehouseDisplay, Integer> areaCol;
    @FXML
    private TableColumn<WarehouseDisplay, String> statusCol;
    
    
    
    @FXML
    private void goWhCreate() throws IOException {
        main.showWhCreate();
    }
    
    @FXML
    private void goWhEdit() throws IOException {
        ContextFX.getInstance().setId(selected_id);
        
        main.showWhEdit();
    }
    
    @FXML
    private void goWhEntryMoveList() throws IOException{
        ContextFX.getInstance().setId(selected_id);
        main.showWhEntryMoveList();
    }
    
    @FXML 
    private void goWhVisualize() throws IOException{
        System.out.println(selected_id);
        ContextFX.getInstance().setId(selected_id);
        main.showWhVisualize();
    }
    
    @FXML 
    private void goAreaList() throws IOException{
        System.out.println(selected_id);
        ContextFX.getInstance().setId(selected_id);
        main.showAreaList();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        warehouseTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.id_warehouseProperty().getValue().intValue();
            }
        );
        try{
            idCol.setCellValueFactory(cellData -> cellData.getValue().id_warehouseProperty().asObject());
            nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            lengthCol.setCellValueFactory(cellData -> cellData.getValue().lengthProperty().asObject());
            widthCol.setCellValueFactory(cellData -> cellData.getValue().widthProperty().asObject());
            areaCol.setCellValueFactory(cellData -> cellData.getValue().areaProperty().asObject());
            statusCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
            warehouseLoadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(WarehouseListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void warehouseLoadData() throws SQLException, ClassNotFoundException{
        warehouses = FXCollections.observableArrayList();
        warehousesView = FXCollections.observableArrayList();
        warehouses = getWarehouses();
        for (int i = 0; i < warehouses.size(); i++) {
            Warehouse wh=(Warehouse) warehouses.get(i);
            WarehouseDisplay wh_display = new WarehouseDisplay(wh.getId(), wh.getName(),
                                                wh.getLength(), wh.getWidth(), wh.isStatus());
            warehousesView.add(wh_display);
        }
        warehouseTable.setItems(null);
        warehouseTable.setItems(warehousesView);
    }

    private ObservableList<Warehouse> getWarehouses() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Warehouse.class);
        List whList = criteria.list();
        ObservableList<Warehouse> returnable=FXCollections.observableArrayList();
        for (int i = 0; i < whList.size(); i++) {
            returnable.add((Warehouse) whList.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }
    
}

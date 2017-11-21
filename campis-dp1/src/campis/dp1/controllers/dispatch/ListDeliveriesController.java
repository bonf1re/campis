/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.controllers.suppliers.ListController;
import campis.dp1.models.DeliveryDisplay;
import campis.dp1.models.Delivery;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author Gina Bustamante
 */
public class ListDeliveriesController implements Initializable {
    private Main main;
    private int selected_id;
    private ObservableList<Delivery> delievries;
    private ObservableList<DeliveryDisplay> deliveriesView;
    
    @FXML
    private TableView<DeliveryDisplay> tableDelivery;
    @FXML
    private TableColumn<DeliveryDisplay, Integer> idCol;
    @FXML
    private TableColumn<DeliveryDisplay, String> addressCol;
    @FXML
    private TableColumn<DeliveryDisplay, String> vehicleCol;
    @FXML
    private TableColumn<DeliveryDisplay, Integer> id_doCol;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        
        tableDelivery.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    this.selected_id = newValue.getId_delivery().getValue().intValue();
                }
        );
        
        try {
            idCol.setCellValueFactory(cellData -> cellData.getValue().getId_delivery().asObject());
            addressCol.setCellValueFactory(cellData -> cellData.getValue().getLocation());
            vehicleCol.setCellValueFactory(cellData -> cellData.getValue().getVh_plate());
            id_doCol.setCellValueFactory(cellData -> cellData.getValue().getId_dispatch_order().asObject());
 
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        delievries = FXCollections.observableArrayList();
        deliveriesView = FXCollections.observableArrayList();
        
        delievries = getDeliveries();
        
        for (int i = 0; i < delievries.size(); i++) {
            DeliveryDisplay supplier = new DeliveryDisplay(delievries.get(i).getId_delivery(), 
                                                           delievries.get(i).getLocation(),
                                                           delievries.get(i).getVh_plate(), delievries.get(i).getId_dispatch_order());
            deliveriesView.add(supplier);
        }
        
        tableDelivery.setItems(null);
        tableDelivery.setItems(deliveriesView);
    }    
    
    private ObservableList<Delivery> getDeliveries() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT * FROM campis.delivery;";
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        ObservableList<Delivery> returnable = FXCollections.observableArrayList();
        
        for (Object[] row : rows) {
            Delivery sup = new Delivery(Integer.parseInt(row[0].toString()),
                                        row[1].toString(),
                                        row[2].toString(),
                                        Integer.parseInt(row[3].toString()));
            returnable.add(sup);
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }
    
    @FXML
    private void goEditDelivery(ActionEvent event) throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showEditDelivery();
        }
    }
    
    @FXML
    private void goCreateDelivery() throws IOException {
        main.showCreateDelivery();
    }
    
    
    @FXML
    private void deleteDelivery(int id) throws IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "DELETE FROM campis.delivery WHERE id_delivery=" + id;
        SQLQuery qry = session.createSQLQuery(qryStr);
        qry.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
    
    @FXML
    private void goDeleteDelivery(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (selected_id > 0) {
            deleteDelivery(this.selected_id);       
            loadData();
        }
    }
}

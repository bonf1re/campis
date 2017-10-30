/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.entries;

import campis.dp1.Main;
import campis.dp1.controllers.products.ListController;
import campis.dp1.models.DispatchMoveDisplay;
import campis.dp1.models.DispatchMove;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ListEntryController implements Initializable {
    
    private Main main;
    private ObservableList<DispatchMove> entries;
    private ObservableList<DispatchMoveDisplay> entriesView;
    private int selected_id;
    
    @FXML
    private void goVisualizeEntry() throws IOException {
        main.showVisualizeEntry();
    }
    
    @FXML
    private void goNewEntry() throws IOException {
        main.showNewEntry();
    }
    
    @FXML
    private TableView<DispatchMoveDisplay> tablaEntries;
    @FXML
    private TableColumn<DispatchMoveDisplay, Integer> idIngresCol;
    @FXML
    private TableColumn<DispatchMoveDisplay,  Integer> prov_AlmCol;
    @FXML
    private TableColumn<DispatchMoveDisplay, String> dateCol;
    @FXML
    private TableColumn<DispatchMoveDisplay, Integer> reasonsCol;
    
    private ObservableList<DispatchMove> getEntries() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchMove.class);
        List lista = criteria.list();
        ObservableList<DispatchMove> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((DispatchMove)lista.get(i));
        }
        sessionFactory.close();
        return returnable;        
    }
    
    private void loadData() throws SQLException, ClassNotFoundException  {
        entries = FXCollections.observableArrayList();
        entriesView = FXCollections.observableArrayList();
        entries = getEntries();
        
        for (int i = 0; i < entries.size(); i++) {
            
            DispatchMoveDisplay e = new DispatchMoveDisplay(entries.get(i).getId_group_batch(), 
                                              entries.get(i).getTyoe_owner(),
                                              entries.get(i).getId_owner(),
                                              entries.get(i).getArrival_date().toString(),
                                              entries.get(i).getReason());
            
            System.out.println("campis.dp1.controllers.entries.ListEntryController.loadData()");
            System.out.println(entries.get(i).getId_group_batch());
            entriesView.add(e);
        }
        
        tablaEntries.setItems(null);  
        tablaEntries.setItems(entriesView);  
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            idIngresCol.setCellValueFactory(cellData -> cellData.getValue().id_group_batchProperty().asObject());
            prov_AlmCol.setCellValueFactory(cellData -> cellData.getValue().id_ownerProperty().asObject());
            dateCol.setCellValueFactory(cellData -> cellData.getValue().arrival_dateProperty());
            reasonsCol.setCellValueFactory(cellData -> cellData.getValue().reasonProperty().asObject());
             
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

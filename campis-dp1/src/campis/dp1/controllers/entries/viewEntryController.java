/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package campis.dp1.controllers.entries;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.controllers.products.ListController;
import campis.dp1.models.DispatchMove;
import campis.dp1.models.Batch;
import campis.dp1.models.Rack;
import campis.dp1.models.RackDisplay;
import com.jfoenix.controls.JFXTextField;
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
import org.hibernate.criterion.Restrictions;
*/
/**
 * FXML Controller class
 *
 * @author david
 */
/*public class viewEntryController implements Initializable {
    Integer id;
    private Main main;
    private ObservableList<BatchEntry> batchEntries;
    private ObservableList<BatchEntryDisplay> batchEntriesView;
    
    @FXML
    private void goListEntries() throws IOException {
        main.showListEntries();
    }
    
    private ObservableList<BatchEntry> getBatchEntries() {
        
        System.out.println("ANTES ENTRANDO A BATCH ENTRIES");
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Criteria criteria = session.createCriteria(BatchEntry.class);
        criteria.add(Restrictions.eq("id_group_batch",this.id));
        
        List lista = criteria.list();
        
        ObservableList<BatchEntry> returnable = FXCollections.observableArrayList();
        
        System.out.println("TAMAñO DE LA LSITA");
        System.out.println(lista.size());
        
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((BatchEntry)lista.get(i));
            System.out.println("BATCH ENTRY 1");
        }
        sessionFactory.close();
        return returnable;
        
    }
    
    private void loadData() throws SQLException, ClassNotFoundException  {
        batchEntries = FXCollections.observableArrayList();
        batchEntriesView = FXCollections.observableArrayList();
        System.out.println("ANTES DE BATCH ENTRIES");
        batchEntries = getBatchEntries();
        
        for (int i = 0; i < batchEntries.size(); i++) {
            
            BatchEntryDisplay be = new BatchEntryDisplay(batchEntries.get(i).getId_batch(), 
                                            batchEntries.get(i).getQuantity(),
                                            batchEntries.get(i).getBatch_cost(),
                                            batchEntries.get(i).getArrival_date().toString(),
                                            batchEntries.get(i).getExpiration_date().toString(),
                                            batchEntries.get(i).getId_product(),
                                            batchEntries.get(i).getType_batch(),
                                            batchEntries.get(i).getId_group_batch(),
                                            batchEntries.get(i).getLocation(),
                                            batchEntries.get(i).getState(),
                                            batchEntries.get(i).getHeritage(),
                                            batchEntries.get(i).getId_unit());
            
            System.out.println("campis.dp1.controllers.entries.viewEntryController.loadData()");
            System.out.println(batchEntries.get(i).getId_batch());
            batchEntriesView.add(be);
        }
        
        tablaBatchEntries.setItems(null);
        tablaBatchEntries.setItems(batchEntriesView); 
       
    }
    
    @FXML
    private JFXTextField ownerFiled;
    @FXML
    private JFXTextField arrivalDateField;
    @FXML
    private TableView<BatchEntryDisplay> tablaBatchEntries;
    @FXML
    private TableColumn<BatchEntryDisplay, Integer> idCol;
    @FXML
    private TableColumn<BatchEntryDisplay,  Integer> productCol;
    @FXML
    private TableColumn<BatchEntryDisplay, Integer> quantityCol;
    @FXML
    private TableColumn<BatchEntryDisplay, Integer> unitCol;
    @FXML
    private TableColumn<BatchEntryDisplay, String> expiration_dateCol;
    
    /**
     * Initializes the controller class.
     */
/*    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Obtemos el id del group entry*/
/*        id = ContextFX.getInstance().getId();
        
        System.out.println("campis.dp1.controllers.racks.EditRackController.initialize()");
        System.out.println(id);
        
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction(); 
         // Obetenemos el group entry
        Criteria criteria = session.createCriteria(DispatchMove.class);
        criteria.add(Restrictions.eq("id_group_batch",id));
        List rsType = criteria.list();
        DispatchMove result = (DispatchMove)rsType.get(0);     
        sessionFactory.close();
        
        this.ownerFiled.setText(result.getId_owner().toString());
        this.arrivalDateField.setText(result.getArrival_date().toString());
        
        //Cargamos la data de los batch entries
        try {
            idCol.setCellValueFactory(cellData -> cellData.getValue().id_batchProperty().asObject());
            productCol.setCellValueFactory(cellData -> cellData.getValue().id_productProperty().asObject());
            quantityCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
            unitCol.setCellValueFactory(cellData -> cellData.getValue().id_unitProperty().asObject());
            expiration_dateCol.setCellValueFactory(cellData -> cellData.getValue().expirantion_dateProperty());
            
            System.out.println("ANTES DE LOAD DATA");
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}*/

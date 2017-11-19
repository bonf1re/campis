/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;
import campis.dp1.models.Batch;
import campis.dp1.models.BatchDisplay;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import campis.dp1.models.Product;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gina Bustamante
 */
public class ListDispatchBatchController implements Initializable {
    
    @FXML
    private TableView<BatchDisplay> batchTable;
    @FXML
    private TableColumn<BatchDisplay, Integer> id_batchCol;
    @FXML
    private TableColumn<BatchDisplay, Integer> quantityCol;
    @FXML
    private TableColumn<BatchDisplay, String> arrival_dateCol;
    @FXML
    private TableColumn<BatchDisplay, String> exp_dateCol;
    @FXML
    private TableColumn<BatchDisplay, String> id_pordCol;
    @FXML
    private TableColumn<BatchDisplay, Integer> type_batchCol;
    @FXML
    private TableColumn<BatchDisplay, String> herCol;
    
    private ObservableList<Batch> batches;
    private ObservableList<BatchDisplay> batchView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        this.batches = FXCollections.observableArrayList();
        this.batches = getListBatch();
        
        id_batchCol.setCellValueFactory(cellData -> cellData.getValue().getId_batch().asObject());
        
        quantityCol.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        arrival_dateCol.setCellValueFactory(cellData -> cellData.getValue().getArrival_date());
        exp_dateCol.setCellValueFactory(cellData -> cellData.getValue().getExpiration_date());
        
        id_pordCol.setCellValueFactory(cellData -> cellData.getValue().getProd_name());
        type_batchCol.setCellValueFactory(cellData -> cellData.getValue().getType_batch().asObject());
        herCol.setCellValueFactory(cellData -> cellData.getValue().getHeritage());   
        
        loadData(batches); 
    }
    
    
    private void loadData(ObservableList<Batch> batch) {
        batchView = FXCollections.observableArrayList();
        
        for (int i = 0; i < batch.size(); i++) {
            String nameProd = getNameProd(batch.get(i).getId_product());

            BatchDisplay batchDisp = new BatchDisplay(batch.get(i).getId_batch(), 
                                                      batch.get(i).getQuantity(),
                                                      batch.get(i).getBatch_cost(), 
                                                      batch.get(i).getArrival_date().toString(),
                                                      batch.get(i).getExpiration_date().toString(), 
                                                      batch.get(i).getId_product(),
                                                      batch.get(i).getType_batch(), 
                                                      batch.get(i).getLocation(),
                                                      Boolean.toString(batch.get(i).isState()),
                                                      batch.get(i).getHeritage(),
                                                      0, 0, nameProd);
            batchView.add(batchDisp);
        }
        
        batchTable.setItems(null);
        batchTable.setItems(batchView);
    }
    
      private String getNameProd(int id_product) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product", id_product));
        List list = criteria.list();
        Product prod = (Product) list.get(0);
        String returnable = prod.getName();
        session.close();
        sessionFactory.close();
        return returnable;
    }
      
     private Integer getIntMeasure(int id_product) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product", id_product));
        List<Product> list = criteria.list();
        Integer meas = list.get(0).getId_unit_of_measure();
        session.close();
        sessionFactory.close();
        return meas;
    }
    
    
    private ObservableList<Batch> getListBatch() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Batch.class);
        criteria.add(Restrictions.eq("type_batch", 1));
        ObservableList<Batch> returnable = FXCollections.observableArrayList();
        List list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            returnable.add((Batch) list.get(i));
        }
        session.close();
        sessionFactory.close();

        return returnable;
    }
    
}

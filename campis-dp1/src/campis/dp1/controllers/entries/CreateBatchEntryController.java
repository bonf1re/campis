/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.entries;

import campis.dp1.Main;
import campis.dp1.models.Batch;
import campis.dp1.models.Product;
import campis.dp1.models.Rack;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class CreateBatchEntryController implements Initializable {
    
    private Main main;
    ObservableList<Product> products;
    Batch batch;
    
    @FXML
    private JFXComboBox<String> prodField;
    @FXML
    private JFXTextField quantityField;
    @FXML
    private JFXDatePicker arrivalDateField;
    @FXML
    private JFXDatePicker expDateField;
    
    @FXML
    private void goNewEntry() throws IOException {
        main.showNewEntry();
    }
    
    @FXML
    private void insertBatchEntry() throws IOException {
       String prod = prodField.getValue();
        
        /*
        Rack r = new Rack(1, Integer.parseInt(x_Field.getText()), Integer.parseInt(y_Field.getText()), 
                            Integer.parseInt(columnsField.getText()), Integer.parseInt(floorsField.getText()),
                            0);
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(r);
        session.getTransaction().commit();

        sessionFactory.close();
        */
        
        this.goNewEntry();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        products = getProducts();
        for (int i = 0; i < products.size(); i++) {
            prodField.getItems().add(products.get(i).getName());
        }
    }    

    private ObservableList<Product> getProducts() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> list = criteria.list();
        ObservableList<Product> returnable = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            returnable.add(list.get(i));
        }
        return returnable;
    }
    
}

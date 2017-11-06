/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.productTypes;

import campis.dp1.Main;
import campis.dp1.models.ProductType;
import campis.dp1.models.User;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Gina Bustamante
 */
public class CreateProductTypeController implements Initializable{
    private Main main;
    
    @FXML
    private JFXTextField descriptionField;

    @FXML
    private void goListProductTypes() throws IOException {
        main.showListProductType();
    }
    
    @FXML
    private void insertProductType() throws IOException {
        ProductType prod_type = new ProductType(descriptionField.getText());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(prod_type);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        main.showListProductType();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
}

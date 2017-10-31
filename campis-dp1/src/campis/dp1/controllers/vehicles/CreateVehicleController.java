/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.vehicles;

import campis.dp1.Main;
import campis.dp1.models.Vehicle;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class CreateVehicleController implements Initializable {
    private Main main;
    
    
    @FXML
    private JFXTextField lblWeight;

    @FXML
    private JFXTextField lblSpeed;

    @FXML
    private JFXComboBox<String> cmWarehouse;

    @FXML
    private JFXTextField lblPlate;
   
    @FXML
    private void goListVehicles() throws IOException {
        main.showListVehicle();
    }
    
    @FXML
    private void insertVehicle() throws IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Vehicle v = new Vehicle(Double.parseDouble(lblWeight.getText()), Integer.parseInt(lblSpeed.getText()),true,
                                            Integer.parseInt(cmWarehouse.getValue()), lblPlate.getText());
        session.save(v);
        session.getTransaction().commit();

        sessionFactory.close();
        this.goListVehicles();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmWarehouse.setItems(FXCollections.observableArrayList("1","2","3"));
    } 
}

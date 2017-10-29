/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.racks;

import campis.dp1.Main;
import campis.dp1.models.Rack;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
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
public class CreateRackController implements Initializable{
    private Main main;
    
    @FXML
    private JFXTextField columnsField;
    @FXML
    private JFXTextField floorsField;
    @FXML
    private JFXTextField x_Field;
    @FXML
    private JFXTextField y_Field;
    @FXML
    private JFXComboBox orientationCb;
   
    @FXML
    private void goListRacks() throws IOException {
        main.showListRacks();
    }
    
    @FXML
    private void insertRack() throws IOException {
        
        //int orientation = orientationCb.getSelectionModel().getSelectedIndex();
        
        Rack r = new Rack(1, Integer.parseInt(x_Field.getText()), Integer.parseInt(y_Field.getText()), 
                            Integer.parseInt(columnsField.getText()), Integer.parseInt(floorsField.getText()),
                            0);
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(r);
        session.getTransaction().commit();

        sessionFactory.close();
        this.goListRacks();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
}

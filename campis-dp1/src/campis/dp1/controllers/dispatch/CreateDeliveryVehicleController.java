/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Vehicle;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author Gina Bustamante
 */
public class CreateDeliveryVehicleController implements Initializable {
    private Main main;
    private int warehouse_id;
    
    @FXML
    private JFXTextField lblWeight;

    @FXML
    private JFXTextField lblSpeed;

    @FXML
    private JFXTextField lblPlate;

    @FXML
    private Label lblWeightMessage;

    @FXML
    private Label lblSpeedMessage;

    @FXML
    private Label lblPlateMessage;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //todos los vehiculos de delivery pertenecen al almacen principal
        this.warehouse_id = 1;
        
        lblWeight.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    lblWeight.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        lblSpeed.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    lblSpeed.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }   
    
    public boolean validation() {
        boolean lblWeightValid = lblWeight.getText().length() == 0;
        boolean lblSpeedValid = lblSpeed.getText().length() == 0;
        boolean lblPlateValid = lblPlate.getText().length() == 0;
        
        lblPlateMessage.setText("");
        lblSpeedMessage.setText("");
        lblWeightMessage.setText("");

        if (lblPlateValid)
            lblPlateMessage.setText("Campo obligatorio");
        if (lblSpeedValid)
            lblSpeedMessage.setText("Campo obligatorio");
        if(lblWeightValid)
            lblWeightMessage.setText("Campo obligatorio");

        return (!lblWeightValid && !lblSpeedValid && !lblPlateValid);
    }
    
    @FXML
    private void insertDeliveryVehicle() throws IOException {
        if (validation()) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            //Vehiculo tipo1 porque es de delivery
            
            Vehicle v = new Vehicle(Double.parseDouble(lblWeight.getText()), Integer.parseInt(lblSpeed.getText()),true,
                                                this.warehouse_id, lblPlate.getText(), 1);
            session.save(v);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            this.goListDeliveryVehicles();
        }
    }
    
    @FXML
    private void goListDeliveryVehicles() throws IOException {
        main.showListDeliveryVehicles();
    }
    
}

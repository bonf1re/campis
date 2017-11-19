/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Vehicle;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Gina Bustamante
 */
public class EditDeliveryVehicleController implements Initializable {
    private Main main;
    private int vehicle_id;
    
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
    
    @FXML
    private JFXComboBox<String> statusCb;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.vehicle_id = ContextFX.getInstance().getId();       
        
        statusCb.getItems().addAll("Habilitado","Deshabilitado");
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Vehicle.class);
        criteria.add(Restrictions.eq("id_vehicle",this.vehicle_id));
        Vehicle v = (Vehicle) criteria.list().get(0);
        this.lblWeight.setText(Double.toString(v.getMax_weight()));
        this.lblSpeed.setText(Integer.toString(v.getSpeed()));
        this.lblPlate.setText(v.getPlate());
        
        if (v.isActive()){
            this.statusCb.getSelectionModel().select(0);
        }else{
            this.statusCb.getSelectionModel().select(1);
        }
        
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

        session.close();
        sessionFactory.close();
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
    private void updateDeliveryVehicle() throws IOException{
        if (validation()) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("update Vehicle set max_weight=:newMweight,"+
                                                "speed=:newSpeed,"+
                                                "active=:newActive,"+
                                                "plate=:newPlate"+
                                                " where id_vehicle=:oldId");
            query.setParameter("newMweight", Double.parseDouble(lblWeight.getText()));
            query.setParameter("newSpeed", Integer.parseInt(lblSpeed.getText()));
            query.setParameter("newPlate", lblPlate.getText());
            if (statusCb.getSelectionModel().getSelectedIndex()==0){
                query.setParameter("newActive", true);
            }else{
                query.setParameter("newActive", false);
            }
            query.setParameter("oldId", this.vehicle_id);
            int result = query.executeUpdate();
            
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

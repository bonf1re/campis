/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.vehicles;


import campis.dp1.models.Vehicle;
import campis.dp1.ContextFX;
import campis.dp1.Main;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gina Bustamante
 */
public class EditVehicleController implements Initializable {
    private Main main;
    private int vehicle_id;
    
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
    private void updateVehicle() throws IOException{

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Vehicle set max_weight=:newMweight,"+
                                            "speed=:newSpeed,"+
                                          //  "active=:newActive,"+
                                            "id_warehouse=:newidW,"+
                                            "plate=:newPlate"+
                                            " where id_vehicle=:oldId");
        query.setParameter("newMweight", Double.parseDouble(lblWeight.getText()));
        query.setParameter("newSpeed", Integer.parseInt(lblSpeed.getText()));
        query.setParameter("newidW", Integer.parseInt(cmWarehouse.getValue()));
        query.setParameter("newPlate", lblPlate.getText());
        query.setParameter("oldId", this.vehicle_id);
        int result = query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListVehicles();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.vehicle_id = ContextFX.getInstance().getId();
        cmWarehouse.getItems().addAll("1","2","3");
        
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
        
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.vehicles;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Vehicle;
import campis.dp1.models.Warehouse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * FXML Controller class
 *
 * @author david
 */
public class CreateVehicleController implements Initializable {
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
   
    @FXML
    private void goListVehicles() throws IOException {
        ContextFX.getInstance().setId(this.warehouse_id);
        main.showListVehicle();
    }
    
    public static Integer searchWarehouse(String wr) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Warehouse.class);
        criteria.add(Restrictions.eq("name",wr));
        Integer codWr;
        List rsWarehouse = criteria.list();
        Warehouse result = (Warehouse)rsWarehouse.get(0);
        codWr = result.getId();
        return codWr;
    }
        
    public static List<Warehouse> getWarehouses() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Warehouse.class)
                .setProjection(Projections.projectionList()
                .add(Projections.property("name"),"name"))
                .setResultTransformer(Transformers.aliasToBean(Warehouse.class));
        List<Warehouse> measures = criteria.list();
        return measures;
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
    private void insertVehicle() throws IOException {
        if (validation()) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
               
            Vehicle v = new Vehicle(Double.parseDouble(lblWeight.getText()), Integer.parseInt(lblSpeed.getText()),true,
                                                this.warehouse_id, lblPlate.getText());
            session.save(v);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            this.goListVehicles();
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.warehouse_id = ContextFX.getInstance().getId();
    } 
}

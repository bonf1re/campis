/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.racks;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Rack;
import campis.dp1.models.Vehicle;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class EditRackController implements Initializable{
    Integer id;
    private Main main;
    
    @FXML
    private JFXTextField numColumnsField;
    @FXML
    private JFXTextField numFloorsField;
    @FXML
    private JFXTextField pos_xField;
    @FXML
    private JFXTextField pos_yField;
    @FXML
    private JFXTextField orientacionField;
    
    @FXML
    private void goListRacks() throws IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rack.class);
        criteria.add(Restrictions.eq("id_rack",this.id));
        List rsWarehouse = criteria.list();
        Rack result = (Rack)rsWarehouse.get(0);
        session.close();
        sessionFactory.close();
        ContextFX.getInstance().setId(result.getId_warehouse());
        main.showListRacks();
    }
    
     @FXML
    private void editRack (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("update Rack set" + 
                                          " pos_x = :newPos_x, pos_y = :newPos_y, n_columns = :newN_columns," +
                                          " n_floors = :newN_floors, orientation=:neworientation" +  
                                          " where id_rack = :id_rack");
        
        query.setParameter("newPos_x", Integer.parseInt(pos_xField.getText()));
        query.setParameter("newPos_y",Integer.parseInt(pos_yField.getText()));
        query.setParameter("newN_floors", Integer.parseInt(numFloorsField.getText()));
        query.setParameter("newN_columns", Integer.parseInt(numColumnsField.getText()));
        query.setParameter("neworientation", Integer.parseInt(orientacionField.getText()));
        query.setParameter("id_rack", id);
        int result = query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListRacks();    
    } 
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id = ContextFX.getInstance().getId();
        
        System.out.println("campis.dp1.controllers.racks.EditRackController.initialize()");
        System.out.println(id);
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rack.class);
        criteria.add(Restrictions.eq("id_rack",id));
        List rsType = criteria.list();
        Rack result = (Rack)rsType.get(0);
        
        System.out.println(result.getId_rack());
        
        this.numColumnsField.setText(result.getN_columns().toString());
        this.numFloorsField.setText(result.getN_floors().toString());
        this.pos_xField.setText(result.getPos_x().toString());
        this.pos_yField.setText(result.getPos_y().toString());
        this.orientacionField.setText(result.getOrientation().toString());
    }  
}

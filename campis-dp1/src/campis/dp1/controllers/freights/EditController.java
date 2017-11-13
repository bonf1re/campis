/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.freights;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.District;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @author Eddy
 */
public class EditController implements Initializable{
    
    Main main;
    Integer id_district;
    
    @FXML
    private JFXTextField districtField;
    @FXML
    private JFXTextField freightField;
    

    @FXML
    private void editFreight(ActionEvent event) throws IOException {
        Float freight = Float.parseFloat(freightField.getText());
        updateFreight(id_district,freight);
        this.goListFreight();
    }

    @FXML
    private void goListFreight() throws IOException {
        main.showListFreights();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_district = ContextFX.getInstance().getId();
        District dist = getDistrict(id_district);
        districtField.setText(dist.getName());
        freightField.setText(Float.toString(dist.getFreight()));
    }

    private District getDistrict(int id_district) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(District.class);
        criteria.add(Restrictions.eq("id_district", id_district));
        List lista = criteria.list();
        District returnable = (District)lista.get(0);
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private void updateFreight(Integer id_district, Float freight) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int newType = 1;
        Query query = session.createQuery("update District set freight = :newFreight where id_district = :oldIdDistrict");
        query.setParameter("newFreight", freight);
        query.setParameter("oldIdDistrict", id_district);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
    
    
}

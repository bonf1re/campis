/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Product;

import campis.dp1.models.Area;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Eddy
 */
public class AreaCreateController implements Initializable {
    private Main main;
    
    private int warehouse_id;
    
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField lengthField;
    @FXML
    private JFXTextField widthField;
    @FXML
    private JFXTextField posXField;
    @FXML
    private JFXTextField posYField;
    
    
    @FXML
    private void goListArea() throws IOException{
        ContextFX.getInstance().setId(this.warehouse_id);
        main.showAreaList();
    }
    
    @FXML
    private void insertArea (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        // Aqui deberias agregar un comboBox que agarre los tipos de producto existentes, y selecciones uno, reemplazando el 0 hardcodeado
        Area area = new Area(nameField.getText(), this.warehouse_id, Integer.parseInt(lengthField.getText()), Integer.parseInt(widthField.getText()),
                                     Integer.parseInt(posXField.getText()), Integer.parseInt(posYField.getText()),0);
        
        session.save(area);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListArea();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.warehouse_id=ContextFX.getInstance().getId();
    }
}

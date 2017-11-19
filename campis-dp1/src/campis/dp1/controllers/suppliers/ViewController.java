/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.suppliers;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Supplier;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Eddy
 */
public class ViewController implements Initializable{
    
    Integer id;
    Main main;
    
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField rucField;
    @FXML
    private JFXTextField phoneField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXTextField emailField;

    @FXML
    private void goEditSupplier(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(id);
        main.showSupplierEdit();
    }

    @FXML
    private void goListSupplier(ActionEvent event) throws IOException {
        main.showListSupplier();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = ContextFX.getInstance().getId();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT * FROM campis.supplier WHERE id_supplier = " + id;
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        Supplier result = new Supplier();
        for (Object[] row : rows) {
             result = new Supplier(Integer.parseInt(row[0].toString()),row[1].toString(),
                                row[2].toString(),row[3].toString(),row[4].toString(),row[5].toString());
        }
        this.nameField.setText(result.getName());
        this.rucField.setText(result.getRuc());
        this.phoneField.setText(result.getPhone());
        this.emailField.setText(result.getEmail());
        this.addressField.setText(result.getAddress());
        session.close();
        sessionFactory.close();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.Main;
import campis.dp1.models.Warehouse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
public class WarehouseCreateController implements Initializable{
    private Main main;
    
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField lengthField;
    @FXML
    private JFXTextField widthField;
    @FXML
    private JFXComboBox statusCb;
    
    @FXML
    private void goWhList() throws IOException{
        main.showWhList();
    }
    
    @FXML
    private void whInsert() throws IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean insert_status;
        if (statusCb.getSelectionModel().getSelectedIndex() == 0){
            insert_status = true;
        }else{
            insert_status = false;
        }
        Warehouse warehouse = new Warehouse(nameField.getText(), Integer.parseInt(lengthField.getText()),
                                            Integer.parseInt(widthField.getText()), insert_status);
        session.save(warehouse);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goWhList();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        statusCb.getItems().addAll("Habilitado","Deshabilitado");
    } 
    
}

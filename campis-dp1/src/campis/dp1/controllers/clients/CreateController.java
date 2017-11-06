/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.clients;

import campis.dp1.Main;
import campis.dp1.models.Client;
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
 * @author Marco
 */
public class CreateController implements Initializable {
    private Main main;
    
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField dniField;
    @FXML
    private JFXTextField rucField;
    @FXML
    private JFXTextField phoneField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField addressField;

    @FXML
    private void goListClient() throws IOException {
        main.showListClient();
    }
    
    @FXML
    private void insertClient() throws IOException {
        Client client = new Client(nameField.getText(), dniField.getText(), rucField.getText(), phoneField.getText(), emailField.getText(), addressField.getText());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        main.showListClient();
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

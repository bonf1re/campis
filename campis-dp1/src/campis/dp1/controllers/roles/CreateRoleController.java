/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.roles;

import campis.dp1.Main;
import campis.dp1.models.Role;
import campis.dp1.models.User;
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
public class CreateRoleController implements Initializable{
    private Main main;
    
    @FXML
    private JFXTextField descriptionField;

    @FXML
    private void goListRoles() throws IOException {
        main.showListRoles();
    }
    
    @FXML
    private void insertRole() throws IOException {
        Role role = new Role(descriptionField.getText());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();

        sessionFactory.close();
        main.showListRoles();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
}

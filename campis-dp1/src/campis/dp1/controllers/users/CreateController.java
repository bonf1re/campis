/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.users;

import campis.dp1.Main;
import campis.dp1.models.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Calendar;
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
    private JFXTextField lastnameField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXComboBox roleField;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void goListUser() throws IOException {
        main.showListUser();
    }

    @FXML
    private void hola() throws IOException {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        
        User user = new User(nameField.getText(), lastnameField.getText(), passwordField.getText(), emailField.getText(),currentTimestamp,true,1,currentTimestamp, usernameField.getText());
        
        System.out.print(user.getLastname());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        sessionFactory.close();
        main.showListUser();    
    }
 
    @FXML
    public void insertUser() throws IOException {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        
        User user = new User(nameField.getText(), lastnameField.getText(), passwordField.getText(), emailField.getText(),currentTimestamp,true,1,currentTimestamp, usernameField.getText());
          
        System.out.print(user.getLastname());
        System.out.println(user.getCreated_at());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        sessionFactory.close();
        main.showListUser();      
    }

}

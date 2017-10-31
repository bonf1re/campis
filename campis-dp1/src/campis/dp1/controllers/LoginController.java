/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.Main;
import campis.dp1.models.User;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marco
 */
public class LoginController {
    Main main;
    @FXML
    private AnchorPane fondo_login;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button btnLogin;
    
    public void Authenticate() throws IOException{
        if (VerifyUser()) {
            main.showTopMenu();
        }
    }
    
    public boolean VerifyUser() {
        ObservableList<User> returnable;
        returnable = FXCollections.observableArrayList();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username",usernameField.getText()));
        criteria.add(Restrictions.eq("password",passwordField.getText()));
        List<User> users = criteria.list();
        
        sessionFactory.close();
        return users.size() > 0;
    }
}

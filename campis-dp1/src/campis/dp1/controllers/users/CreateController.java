/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.users;

import campis.dp1.Main;
import campis.dp1.models.Role;
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
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

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
        List<Role> roleList = campis.dp1.controllers.users.CreateController.getRoles(); 
        for (int i = 0; i < roleList.size(); i++) {
            roleField.getItems().addAll(roleList.get(i).getDescription());
        }
    }
    
    @FXML
    private void goListUser() throws IOException {
        main.showListUser();
    }

    public static List<Role> getRoles() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class)
                .setProjection(Projections.projectionList()
                .add(Projections.property("description"),"description"))
                .setResultTransformer(Transformers.aliasToBean(Role.class));
        List<Role> types = criteria.list();
        return types;
    }
 
    @FXML
    public void insertUser() throws IOException {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        User user = new User(nameField.getText(), lastnameField.getText(), passwordField.getText(), emailField.getText(),currentTimestamp,true,1,currentTimestamp, usernameField.getText());
          
        System.out.print(user.getLastname());
        System.out.println(user.getCreated_at());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        sessionFactory.close();
        main.showListUser();      
    }

}

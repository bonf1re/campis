/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.roles;

import campis.dp1.Main;
import campis.dp1.models.Permission;
import campis.dp1.models.Role;
import campis.dp1.models.User;
import campis.dp1.models.View;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javafx.scene.control.Label;
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
    private Label desMessage;

    @FXML
    private void goListRoles() throws IOException {
        main.showListRoles();
    }
    
    public boolean validation() {
        boolean desValid = descriptionField.getText().length() == 0;

        desMessage.setText("");

        if(desValid)
            desMessage.setText("Campo obligatorio");

        return (!desValid);
    }

    @FXML
    private void insertRole() throws IOException {
        if (validation()) {
            Role role = new Role(descriptionField.getText());
            List<View> views = View.getViews();
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(role);
            for (int i = 0; i < views.size(); i++) {
                Permission permission = new Permission(views.get(i).getId_view(), role.getId_role());
                session.save(permission);
            }
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();

            main.showListRoles();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
}

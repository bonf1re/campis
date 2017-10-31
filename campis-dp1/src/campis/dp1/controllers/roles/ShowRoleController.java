/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.roles;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import static campis.dp1.controllers.products.EditController.getType;
import campis.dp1.models.Product;
import campis.dp1.models.Role;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marco
 */
public class ShowRoleController implements Initializable {
    Integer id;
    Main main;
    @FXML
    private JFXTextField descriptionField;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = ContextFX.getInstance().getId();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("id_role",id));
        List rsType = criteria.list();
        Role result = (Role)rsType.get(0);
        this.descriptionField.setText(result.getDescription());
    }

    @FXML
    private void goEditRole(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(id);
        main.showEditRole();
    }

    @FXML
    private void goListRole(ActionEvent event) throws IOException {
        main.showListRoles();
    }
}

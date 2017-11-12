/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.roles;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Role;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

/** 
 *
 * @author Gina Bustamante
 */
public class EditRoleController implements Initializable{
    Integer id;
    Main main;
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
    private void editRole (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (validation()) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("update Role set description = :newDescription"
                    + " where id_role = :oldIdRole");
            query.setParameter("newDescription", descriptionField.getText());
            query.setParameter("oldIdRole", id);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            this.goListRoles();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        session.close();
        sessionFactory.close();
    } 
}
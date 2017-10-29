/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.users;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import static campis.dp1.controllers.products.EditController.getMeasure;
import static campis.dp1.controllers.products.EditController.getType;
import campis.dp1.models.User;
import campis.dp1.models.ProductType;
import campis.dp1.models.Role;
import campis.dp1.models.UnitOfMeasure;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import org.hibernate.query.Query;

/**
 *
 * @author Marco
 */
public class EditController implements Initializable {
    Integer id;
    Main main;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField lastnameField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXComboBox roleField;
    
    @FXML
    private void goListUser() throws IOException {
        main.showListUser();
    }

    public static Integer searchIdRole(String type) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("description",type));
        Integer codType;
        List rsType = criteria.list();
        Role result = (Role) rsType.get(0);
        codType = result.getId_role();
        sessionFactory.close();

        return codType;
    }

    public static String getRole(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("id_role",cod));
        String descripType;
        List rsType = criteria.list();
        Role result = (Role) rsType.get(0);
        descripType = result.getDescription();
        sessionFactory.close();

        return descripType;
    }

    @FXML
    private void editUser (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int id_role = searchIdRole(roleField.getEditor().getText());
        Query query = session.createQuery("update User set firstname = :newFirstname,lastname = :newLastname,"
                + "email=:newEmail,username=:newUsername,id_role=:newId_role where id_user = :oldIdProd");
        query.setParameter("newFirstname", nameField.getText());
        query.setParameter("newLastname", lastnameField.getText());
        query.setParameter("newEmail", emailField.getText());
        query.setParameter("newUsername", usernameField.getText());
        query.setParameter("newId_role", id_role);
        query.setParameter("oldIdProd", id);
        int result = query.executeUpdate();
        
        session.getTransaction().commit();

        sessionFactory.close();
        this.goListUser();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = ContextFX.getInstance().getId();
        List<Role> roleList = campis.dp1.controllers.users.CreateController.getRoles(); 
        for (int i = 0; i < roleList.size(); i++) {
            roleField.getItems().addAll(roleList.get(i).getDescription());
        }
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id_user",id));
        List rsType = criteria.list();
        User result = (User)rsType.get(0);
        this.nameField.setText(result.getFirstname());
        this.lastnameField.setText(result.getLastname());
        this.usernameField.setText(result.getUsername());
        this.emailField.setText(result.getEmail());
        String role = getRole(result.getId_role());
        this.roleField.setValue(role);
    }
}
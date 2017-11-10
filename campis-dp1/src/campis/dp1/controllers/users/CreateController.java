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
import javafx.scene.control.Label;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
    @FXML
    private Label passMessage;
    @FXML
    private Label emailMessage;
    @FXML
    private Label roleMessage;
    @FXML
    private Label userMessage;
    @FXML
    private Label nameMessage;
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

    public static Integer searchCodRole(String type) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("description",type));
        Integer codType;
        List rsType = criteria.list();
        Role result = (Role) rsType.get(0);
        codType = result.getId_role();
        session.close();
        sessionFactory.close();
        return codType;
    }

    public boolean validation() {
        boolean nameValid = nameField.getText().length() == 0;
        boolean passValid = passwordField.getText().length() < 6;
        boolean userValid = usernameField.getText().length() == 0;
        boolean emailValid = emailField.getText().length() == 0;
        boolean roleValid = roleField.getEditor().getText().length() == 0;
        
        passMessage.setText("");
        nameMessage.setText("");
        userMessage.setText("");
        emailMessage.setText("");
        roleMessage.setText("");

        if (passValid)
            passMessage.setText("Contraseña debe tener mínimo 6 caracteres");
        if (nameValid)
            nameMessage.setText("Campo obligatorio");
        if (userValid)
            userMessage.setText("Campo obligatorio");
        if(emailValid)
            emailMessage.setText("Campo obligatorio");
        if(roleValid)
            roleMessage.setText("Campo obligatorio");

        return (!nameValid && !passValid && !userValid && !emailValid && !roleValid);
    }
 
    @FXML
    public void insertUser() throws IOException, SQLException, ClassNotFoundException {
        if (validation()) {
            Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            int id_role = searchCodRole(roleField.getEditor().getText());
            User user = new User(nameField.getText(), lastnameField.getText(), passwordField.getText(), emailField.getText(), currentTimestamp, true, id_role, currentTimestamp, usernameField.getText());

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            main.showListUser();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.clients;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Client;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import javafx.scene.control.Label;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

/** 
 *
 * @author Gina Bustamante
 */
public class EditController implements Initializable{
    Integer id;
    Main main;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField rucField;
    @FXML
    private JFXTextField phoneField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private Label nameMessage;
    @FXML
    private Label emailMessage;
    @FXML
    private Label rucMessage;

    @FXML
    private void goListClient() throws IOException {
        main.showListClient();
    }

    public boolean validation() {
        boolean nameValid = nameField.getText().length() == 0;
        boolean docValid = rucField.getText().length() == 0;
        boolean emailValid = emailField.getText().length() == 0;
                
        nameMessage.setText("");
        rucMessage.setText("");
        emailMessage.setText("");

        if (nameValid)
            nameMessage.setText("Campo obligatorio");

        if (docValid) {
            rucMessage.setText("DNI o RUC obligatorio");
        }

        if(emailValid)
            emailMessage.setText("Campo obligatorio");

        return (!nameValid && !docValid && !emailValid);
    }

    @FXML
    private void updateClient (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (validation()) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("update Client set phone = :newPhone, email = :newEmail, address = :newAddress"
                    + " where id_client = :oldIdClient");
            query.setParameter("newPhone", phoneField.getText());
            query.setParameter("newEmail", emailField.getText());
            query.setParameter("newAddress", addressField.getText());
            query.setParameter("oldIdClient", id);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            this.goListClient();
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
        Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("id_client",id));
        List rsType = criteria.list();
        Client result = (Client)rsType.get(0);
        this.nameField.setText(result.getName());
        
        if (!result.getRuc().equals("--")) {
            this.rucField.setText(result.getRuc());
        } else {
            this.rucField.setText(result.getDni());
        }
        
        this.phoneField.setText(result.getPhone());
        this.emailField.setText(result.getEmail());
        this.addressField.setText(result.getAddress());
        session.close();
        sessionFactory.close();
        
        phoneField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    phoneField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    } 
}

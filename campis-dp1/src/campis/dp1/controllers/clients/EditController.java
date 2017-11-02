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
 * @author Gina Bustamante
 */
public class EditController implements Initializable{
    Integer id;
    Main main;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField dniField;
    @FXML
    private JFXTextField rucField;
    @FXML
    private JFXTextField phoneField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField addressField;

    @FXML
    private void goListClient() throws IOException {
        main.showListClient();
    }

    @FXML
    private void updateClient (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Client set name = :newDescription, dni = :newDni, ruc = :newRuc"
                + " , phone = :newPhone, email = :newEmail, address = :newAddress where id_client = :oldIdClient");
        query.setParameter("newDescription", nameField.getText());
        query.setParameter("newDni", dniField.getText());
        query.setParameter("newRuc", rucField.getText());
        query.setParameter("newPhone", phoneField.getText());
        query.setParameter("newEmail", emailField.getText());
        query.setParameter("newAddress", addressField.getText());
        query.setParameter("oldIdClient", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        sessionFactory.close();
        this.goListClient();
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
        this.dniField.setText(result.getDni());
        this.rucField.setText(result.getRuc());
        this.phoneField.setText(result.getPhone());
        this.emailField.setText(result.getEmail());
        this.addressField.setText(result.getAddress());
        sessionFactory.close();
    } 
}

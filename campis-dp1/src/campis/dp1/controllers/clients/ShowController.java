/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.clients;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import static campis.dp1.controllers.products.EditController.getType;
import campis.dp1.models.Product;
import campis.dp1.models.Client;
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
public class ShowController implements Initializable {
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        session.close();
        sessionFactory.close();
    }

    @FXML
    private void goEditClient(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(id);
        main.showEditClient();
    }

    @FXML
    private void goListClient(ActionEvent event) throws IOException {
        main.showListClient();
    }
}

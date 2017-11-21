/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.suppliers;

import campis.dp1.Main;
import campis.dp1.models.Client;
import campis.dp1.models.Supplier;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Eddy
 */
public class CreateController implements Initializable{
    
    Main main;
    
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField rucField;
    @FXML
    private JFXTextField phoneField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private Label nameMessage;
    @FXML
    private Label emailMessage;
    @FXML
    private Label rucMessage;
    @FXML
    private Label addressMessage;
    @FXML
    private Label phoneMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
     public boolean validation() {
        boolean nameValid = nameField.getText().length() == 0;
        boolean docValid = (rucField.getText().length() == 0);
        boolean emailValid = emailField.getText().length() == 0;
        boolean addressValid = addressField.getText().length() == 0;
        boolean phoneValid = phoneField.getText().length() == 0;

        rucMessage.setText("");
        nameMessage.setText("");
        emailMessage.setText("");
        addressMessage.setText("");
        phoneMessage.setText("");

        if (nameValid) {
            nameMessage.setText("Campo obligatorio");
        }

        if (docValid) {
            rucMessage.setText("RUC obligatorio");
        }

        if (emailValid) {
            emailMessage.setText("Campo obligatorio");
        }

        if (addressValid) {
            addressMessage.setText("Campo obligatorio");
        }

        if (phoneValid) {
            phoneMessage.setText("Campo obligatorio");
        }

        return (!nameValid && !docValid && !emailValid && !addressValid && !phoneValid);
    }

    @FXML
    private void insertSupplier(ActionEvent event) throws IOException {
        if (validation()) {
            Supplier supplier = new Supplier(nameField.getText(),rucField.getText(), addressField.getText(), emailField.getText(), phoneField.getText());
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String qryStr = "INSERT INTO campis.supplier VALUES(DEFAULT,'" + supplier.getName() + "',"
                    + "'"+supplier.getRuc()+"',"
                    + "'"+supplier.getAddress() +"',"
                    + "'"+supplier.getEmail()+"',"
                    + "'"+supplier.getPhone()+"')";
            SQLQuery qry = session.createSQLQuery(qryStr);
            qry.executeUpdate();
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            main.showListSupplier();
        }
    }

    @FXML
    private void goListSupplier(ActionEvent event) throws IOException {
        main.showListSupplier();
    }
    
}

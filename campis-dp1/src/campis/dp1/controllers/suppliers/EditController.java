/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.suppliers;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Supplier;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

/**
 *
 * @author Eddy
 */
public class EditController implements Initializable {

    Integer id;
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
        id = ContextFX.getInstance().getId();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT * FROM campis.supplier WHERE id_supplier = " + id;
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        Supplier result = new Supplier();
        for (Object[] row : rows) {
             result = new Supplier(Integer.parseInt(row[0].toString()),row[1].toString(),
                                row[2].toString(),row[3].toString(),row[4].toString(),row[5].toString());
        }
        this.nameField.setText(result.getName());
        this.rucField.setText(result.getRuc());
        this.phoneField.setText(result.getPhone());
        this.emailField.setText(result.getEmail());
        this.addressField.setText(result.getAddress());
        session.close();
        sessionFactory.close();
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
    private void editSupplier(ActionEvent event) throws IOException{
        if (validation()) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("update campis.supplier set name = :newDescription, ruc = :newRuc"
                    + " , phone = :newPhone, email = :newEmail, address = :newAddress where id_supplier = :oldIdSupplier");
            query.setParameter("newDescription", nameField.getText());
            query.setParameter("newRuc", rucField.getText());
            query.setParameter("newPhone", phoneField.getText());
            query.setParameter("newEmail", emailField.getText());
            query.setParameter("newAddress", addressField.getText());
            query.setParameter("oldIdSupplier", id);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            this.goListSupplier();
        }
    }

    @FXML
    private void goListSupplier() throws IOException {
        main.showListSupplier();
    }

}

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
import javafx.scene.control.Label;
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
public class ChangePasswordController implements Initializable {
    Integer id;
    Main main;
    @FXML
    private JFXPasswordField oldPasswordField;
    @FXML
    private JFXPasswordField newPasswordField;
    @FXML
    private JFXPasswordField repeatPasswordField;
    @FXML
    private Label npMessage;
    @FXML
    private Label np2Message;
    @FXML
    private Label opMessage;

    public boolean validation() {
        boolean opValid = oldPasswordField.getText().length() == 0;
        boolean npValid = newPasswordField.getText().length() < 6;
        boolean np2Valid = repeatPasswordField.getText().length() == 0;
        boolean sameValid = newPasswordField.getText().equals(repeatPasswordField.getText());
        boolean passValid = oldPasswordField.getText().equals(ContextFX.getInstance().getUser().getPassword());

        npMessage.setText("");
        np2Message.setText("");
        opMessage.setText("");

        if (opValid)
            opMessage.setText("Campo obligatorio");
        if (npValid)
            npMessage.setText("Contraseña debe tener mínimo 6 caracteres");
        if(np2Valid)
            np2Message.setText("Campo obligatorio");
        if (!opValid && !npValid && !np2Valid) {
            if (!passValid)
                opMessage.setText("Contraseña incorrecta");
            if (!sameValid)
                np2Message.setText("No es igual");
        }

        return (!opValid && !npValid && !np2Valid && sameValid && passValid);
    }

    @FXML
    private void goShowUser() throws IOException {
        main.showLoggedUser();
    }

    @FXML
    private void editUser (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (validation()) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("update User set password = :newPass where id_user = :oldIdProd");
            query.setParameter("newPass", newPasswordField.getText());
            query.setParameter("oldIdProd", ContextFX.getInstance().getUser().getId_user());

            int result = query.executeUpdate();            
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();

            this.goShowUser();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

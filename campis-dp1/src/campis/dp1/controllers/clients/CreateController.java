package campis.dp1.controllers.clients;

import campis.dp1.Main;
import campis.dp1.models.Client;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Session;
import javafx.scene.control.Label;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Marco
 */
public class CreateController implements Initializable {
    private Main main;
    
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
    private Label nameMessage;
    @FXML
    private Label emailMessage;
    @FXML
    private Label dniMessage;
    @FXML
    private Label rucMessage;

    @FXML
    private void goListClient() throws IOException {
        main.showListClient();
    }
    
    public boolean validation() {
        boolean nameValid = nameField.getText().length() == 0;
        boolean docValid = (dniField.getText().length() == 0) && (rucField.getText().length() == 0);
        boolean emailValid = emailField.getText().length() == 0;
        
        dniMessage.setText("");
        nameMessage.setText("");
        rucMessage.setText("");
        emailMessage.setText("");

        if (nameValid)
            nameMessage.setText("Campo obligatorio");

        if (docValid) {
            dniMessage.setText("DNI o RUC obligatorio");
            rucMessage.setText("DNI o RUC obligatorio");
        }

        if(emailValid)
            emailMessage.setText("Campo obligatorio");

        return (!nameValid && !docValid && !emailValid);
    }

    @FXML
    private void insertClient() throws IOException {
        if (validation()) {
            Client client = new Client(nameField.getText(), dniField.getText(), rucField.getText(), addressField.getText(), phoneField.getText(), emailField.getText());
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            main.showListClient();
        }
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

package campis.dp1.controllers.clients;

import campis.dp1.Main;
import campis.dp1.models.Client;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private JFXTextField rucField;
    @FXML
    private JFXTextField phoneField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXComboBox identifier;
    @FXML
    private Label nameMessage;
    @FXML
    private Label emailMessage;
    @FXML
    private Label rucLabel;
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
    private void insertClient() throws IOException {
        if (validation()) {
            String dni;
            String ruc;
            
            if (identifier.getValue().equals("Razon Social")) {
                dni = "--";
                ruc = rucField.getText();
            } else {
                dni = rucField.getText();
                ruc = "--";
            }
            
            Client client = new Client(nameField.getText(), dni, ruc, addressField.getText(), phoneField.getText(), emailField.getText());
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
        identifier.getItems().addAll("Razon Social","Nombre");
        
        identifier.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (newValue.matches("Razon Social")) {
                    rucLabel.setText("RUC");
                } else {
                    rucLabel.setText("DNI");
                }
            }
        });
        
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

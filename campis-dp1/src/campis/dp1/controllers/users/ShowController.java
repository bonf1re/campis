package campis.dp1.controllers.users;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import static campis.dp1.controllers.products.EditController.getType;
import campis.dp1.models.Role;
import campis.dp1.models.User;
import com.jfoenix.controls.JFXComboBox;
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
    private JFXTextField lastnameField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXComboBox roleField;
     @FXML
    private void goListUsers() throws IOException {
        main.showListUser();
    }

   @FXML
    private void goEditUser(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(id);
        main.showEditUser();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id = ContextFX.getInstance().getId();
        List<Role> roleList = campis.dp1.controllers.users.CreateController.getRoles(); 
        for (int i = 0; i < roleList.size(); i++) {
            roleField.getItems().addAll(roleList.get(i).getDescription());
        }
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
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
        String role = campis.dp1.controllers.users.EditController.getRole(result.getId_role());
        this.roleField.setValue(role);
    } 

}
package campis.dp1.controllers.users;

import campis.dp1.Main;
import campis.dp1.controllers.users.ListController;
import campis.dp1.models.User;
import campis.dp1.models.UserDisplay;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Gina Bustamante
 */
public class ListController implements Initializable{
    private Main main;
    private ObservableList<User> users;
    private ObservableList<UserDisplay> usersView;

    @FXML
    private TableView<UserDisplay> tableUser;
    @FXML
    private TableColumn<UserDisplay,String> namesColumn;
    @FXML
    private TableColumn<UserDisplay,String> usernameColumn;
    @FXML
    private TableColumn<UserDisplay,String> emailColumn;

    @FXML
    private void goCreateUser() throws IOException {
        main.showCreateUser();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            namesColumn.setCellValueFactory(cellData -> cellData.getValue().namesProperty());
            usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
            emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
            /**/
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<User> getUsers() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        List lista = criteria.list();
        ObservableList<User> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((User)lista.get(i));
        }
        sessionFactory.close();
        return returnable;
    }
    
    private void loadData() throws SQLException, ClassNotFoundException {
        users = FXCollections.observableArrayList();
        usersView = FXCollections.observableArrayList();
        users = getUsers();
        for (int i = 0; i < users.size(); i++) {
            UserDisplay user = new UserDisplay(users.get(i).getFirstname(), users.get(i).getLastname(), users.get(i).getEmail(), users.get(i).getUsername());
            usersView.add(user);
        }
        tableUser.setItems(null);
        tableUser.setItems(usersView);
    }
}

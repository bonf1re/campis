package campis.dp1.controllers.users;

import campis.dp1.Main;
import campis.dp1.ContextFX;
import campis.dp1.controllers.users.ListController;
import campis.dp1.models.User;
import campis.dp1.models.UserDisplay;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private int selected_id;
    
    @FXML
    private Button searchButton;
    @FXML
    private JFXTextField searchField;
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

    @FXML
    private void goEditUser(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(selected_id);
        main.showEditUser();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableUser.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.idUserProperty().getValue().intValue();
            }
        );
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
            UserDisplay user = new UserDisplay(users.get(i).getId_user(), users.get(i).getFirstname(), users.get(i).getLastname(), users.get(i).getEmail(), users.get(i).getUsername());
            usersView.add(user);
        }
        tableUser.setItems(null);
        tableUser.setItems(usersView);
    }

    private ObservableList<User> getSearchList(String text) {
        ObservableList<User> returnable;
        returnable = FXCollections.observableArrayList();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        List<User> list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFirstname().contains(text) == TRUE || list.get(i).getLastname().contains(text) == TRUE) {
                returnable.add(list.get(i));
            }
        }
        sessionFactory.close();
        return returnable;
    }

    @FXML
    private void searchButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String text = this.searchField.getText();
        if (text.compareTo("") == 0) {
            loadData();
        } else {
            users = FXCollections.observableArrayList();
            usersView = FXCollections.observableArrayList();
            users = getSearchList(text);
            if (users == null) {
                tableUser.setItems(null);
            } else {
                for (int i = 0; i < users.size(); i++) {
                    UserDisplay user = new UserDisplay(users.get(i).getId_user(), users.get(i).getFirstname(), users.get(i).getLastname(), users.get(i).getEmail(), users.get(i).getUsername());
                    usersView.add(user);
                }
            }
            tableUser.setItems(null);
            tableUser.setItems(usersView);
        }
    }

    private void deleteUser(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        User user = new User();
        user.setId_user(cod);
        session.delete(user);
        session.getTransaction().commit();

        sessionFactory.close();
    }
    
    @FXML
    private void deleteUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        ContextFX.getInstance().setId(selected_id);
        Integer id_user = ContextFX.getInstance().getId();
        deleteUser(selected_id);
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId_user() == selected_id) {
                users.remove(i);
            }
        }
        loadData();
    }
}

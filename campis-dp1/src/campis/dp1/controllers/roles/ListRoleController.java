/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.roles;

import campis.dp1.Main;
import campis.dp1.controllers.products.ListController;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.Role;
import campis.dp1.models.RoleDisplay;
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
public class ListRoleController implements Initializable{
    private Main main;
    private ObservableList<Role> roles;
    private ObservableList<RoleDisplay> rolesView;

    @FXML
    private TableView<RoleDisplay> tableRole;
    @FXML
    private TableColumn<RoleDisplay,String> descriptionColumn;
    
    @FXML
    private void goCreateRole() throws IOException {
        main.showNewRole();
    } 
    
    @FXML
    private void goEditRole() throws IOException {
        main.showEditRole();
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
            /**/
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<Role> getRoles() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);
        List lista = criteria.list();
        ObservableList<Role> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Role)lista.get(i));
        }
        sessionFactory.close();
        return returnable;
    }
    
    private void loadData() throws SQLException, ClassNotFoundException {
        roles = FXCollections.observableArrayList();
        rolesView = FXCollections.observableArrayList();
        roles = getRoles();
        for (int i = 0; i < roles.size(); i++) {
            RoleDisplay prod = new RoleDisplay(roles.get(i).getDescription());
            rolesView.add(prod);
        }
        tableRole.setItems(null);
        tableRole.setItems(rolesView);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.roles;

import campis.dp1.Main;
import javafx.event.ActionEvent;
import campis.dp1.controllers.products.ListController;
import campis.dp1.models.RoleDisplay;
import campis.dp1.models.Role;
import campis.dp1.ContextFX;
import campis.dp1.models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.scene.control.Button;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gina Bustamante
 */
public class ListRoleController implements Initializable{
    private Main main;
    private ObservableList<Role> roles;
    private ObservableList<RoleDisplay> rolesView;
    private int selected_id;
    private int id_role;

    @FXML
    private TableView<RoleDisplay> tableRole;
    @FXML
    private TableColumn<RoleDisplay,String> descriptionColumn;
    @FXML
    private Button createButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label lbldelete;

    @FXML
    private void goCreateRole() throws IOException {
        main.showNewRole();
    } 

    @FXML
    private void goEditRole() throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showEditRole();
        }
    }

    @FXML
    private void goShowRole() throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showViewRole();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        this.lbldelete.setText("");
        ContextFX.getInstance().modifyValidation(createButton, editButton, deleteButton, id_role, "roles");
        tableRole.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.idRoleProperty().getValue().intValue();
            }
        );
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
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
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
        session.close();
        sessionFactory.close();
        return returnable;
    } 

    private void loadData() throws SQLException, ClassNotFoundException {
        roles = FXCollections.observableArrayList();
        rolesView = FXCollections.observableArrayList();
        roles = getRoles();
        for (int i = 0; i < roles.size(); i++) {
            RoleDisplay prod = new RoleDisplay(roles.get(i).getId_role(), roles.get(i).getDescription());
            rolesView.add(prod);
        }
        tableRole.setItems(null);
        tableRole.setItems(rolesView);
    }

    @FXML
    private void goPermission(ActionEvent event) throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showPermission();
        }
    }

    private void deleteRole(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);
        Role role = new Role();
        role.setId_role(cod);
        session.delete(role);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @FXML
    private void deleteRole(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            if (anyUsers(selected_id)){
                deleteRole(selected_id);
                for (int i = 0; i < roles.size(); i++) {
                    if(roles.get(i).getId_role() == selected_id) {
                        roles.remove(i);
                    }
                }
                loadData();
                this.lbldelete.setText("");
            }  else {
            this.lbldelete.setText("No es posible eliminar. Se han creado usuarios asignados "
                                    + "a este rol.");
            }
        }
        
    }
    
    private boolean anyUsers(int type_id){
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id_role",type_id));
        List lista = criteria.list();

        session.close();
        sessionFactory.close();
        return (lista.isEmpty());
    }
    
}
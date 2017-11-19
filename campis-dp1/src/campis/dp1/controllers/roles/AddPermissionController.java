package campis.dp1.controllers.roles;

import campis.dp1.ContextFX;
import campis.dp1.controllers.products.ListController;
import campis.dp1.models.Permission;
import campis.dp1.models.PermissionDisplay;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import campis.dp1.Main;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import javafx.scene.control.cell.CheckBoxTableCell;

public class AddPermissionController implements Initializable {
    private Main main;
    Integer id;
    private int selected_id;
    private ObservableList<Permission> permissions;
    private ObservableList<PermissionDisplay> permissionsView;
    @FXML
    private TableView<PermissionDisplay> tablePerm;
    @FXML
    private TableColumn<PermissionDisplay, String> viewColumn;
    @FXML
    private TableColumn<PermissionDisplay, Boolean> editColumn;
    @FXML
    private TableColumn<PermissionDisplay, Boolean> visualizeColumn;

	private ObservableList<Permission> getPermissions() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Permission.class);
        criteria.add(Restrictions.eq("id_role",id));
        List<Permission> list = criteria.list();
        ObservableList<Permission> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
                returnable.add((Permission)list.get(i));
        }
        session.close();
        sessionFactory.close();

        return returnable;
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        permissions = FXCollections.observableArrayList();
        permissionsView = FXCollections.observableArrayList();
        permissions = getPermissions();
        for (int i = 0; i < permissions.size(); i++) {
            PermissionDisplay prod = new PermissionDisplay(permissions.get(i).getId_permission() ,permissions.get(i).getId_role(), permissions.get(i).getId_view(), permissions.get(i).getModify(), permissions.get(i).getVisualize());
            permissionsView.add(prod);
        }
        tablePerm.setItems(null);
        tablePerm.setItems(permissionsView);
    }

    private void loadLocalData() throws SQLException, ClassNotFoundException {
    	permissionsView = FXCollections.observableArrayList();
        for (int i = 0; i < permissions.size(); i++) {
            PermissionDisplay prod = new PermissionDisplay(permissions.get(i).getId_permission() ,permissions.get(i).getId_role(), permissions.get(i).getId_view(), permissions.get(i).getModify(), permissions.get(i).getVisualize());
            permissionsView.add(prod);
        }
        tablePerm.setItems(null);
        tablePerm.setItems(permissionsView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	id = ContextFX.getInstance().getId();
    	tablePerm.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.idPermissionProperty().getValue().intValue();
        });
        visualizeColumn.setCellFactory(
                CheckBoxTableCell.forTableColumn(visualizeColumn)
            );
        visualizeColumn.setCellValueFactory(
                cellData->cellData.getValue().getVisualize()
        );
        editColumn.setCellFactory(
                CheckBoxTableCell.forTableColumn(editColumn)
            );
        editColumn.setCellValueFactory(
                cellData->cellData.getValue().getModify()
        );
        try {
            viewColumn.setCellValueFactory(cellData -> cellData.getValue().getView());
            editColumn.setCellValueFactory(cellData -> cellData.getValue().getModify());
            visualizeColumn.setCellValueFactory(cellData -> cellData.getValue().getVisualize());
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablePerm.setEditable(true);
    }

    @FXML
    private void updatePermission (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
    	Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
    	for (int i = 0; i < permissionsView.size(); i++) {
	        Query query = session.createQuery("update Permission set visualize = :newVisualize, modify = :newModify "
	                + "where id_permission = :oldIdPerm");
	        query.setParameter("newVisualize", permissionsView.get(i).getVisualize().get());
	        query.setParameter("newModify", permissionsView.get(i).getModify().get());
	        query.setParameter("oldIdPerm", permissionsView.get(i).idPermissionProperty().getValue().intValue());
	        int result = query.executeUpdate();
    	}
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        main.showListRoles();
    }
}
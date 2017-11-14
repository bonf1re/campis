/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.requestStatuses;

import campis.dp1.Main;
import javafx.event.ActionEvent;
import campis.dp1.controllers.products.ListController;
import campis.dp1.models.RequestStatusDisplay;
import campis.dp1.models.RequestStatus;
import campis.dp1.ContextFX;
import campis.dp1.models.View;
import campis.dp1.models.Permission;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author joseamz
 */
public class ListRequestStatusController implements Initializable {
    private Main main;
    private ObservableList<RequestStatus> requestStatuses;
    private ObservableList<RequestStatusDisplay> requestStatusesView;
    private int selected_id;
    private int id_role;

    @FXML
    private Button editButton;
    @FXML
    private TableView<RequestStatusDisplay> tableRequestStatus;
    @FXML
    private TableColumn<RequestStatusDisplay,String> descriptionColumn;

    @FXML
    private void goEditRequestStatus() throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showEditRequestStatuses();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        id_role = (ContextFX.getInstance().getUser().getId_role());
        View whView = View.getView("request_statuses");

        if (!Permission.canModify(id_role, whView.getId_view())) {
            editButton.setVisible(false);
        }
        tableRequestStatus.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.idRequestStatusProperty().getValue().intValue();
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

    private ObservableList<RequestStatus> getRequestStatuses() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RequestStatus.class);
        List lista = criteria.list();
        ObservableList<RequestStatus> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((RequestStatus)lista.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        requestStatuses = FXCollections.observableArrayList();
        requestStatusesView = FXCollections.observableArrayList();
        requestStatuses = getRequestStatuses();
        for (int i = 0; i < requestStatuses.size(); i++) {
            RequestStatusDisplay status = new RequestStatusDisplay(requestStatuses.get(i).getId_request_status(), requestStatuses.get(i).getDescription());
            requestStatusesView.add(status);
        }
        tableRequestStatus.setItems(null);
        tableRequestStatus.setItems(requestStatusesView);
    }
}

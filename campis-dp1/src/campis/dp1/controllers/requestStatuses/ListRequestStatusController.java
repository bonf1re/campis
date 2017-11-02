/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.requestStatuses;

import campis.dp1.Main;
import javafx.event.ActionEvent;
import campis.dp1.models.RequestStatusDisplay;
import campis.dp1.models.RequestStatus;
import campis.dp1.ContextFX;
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
 * @author joseamz
 */
public class ListRequestStatusController implements Initializable {
    private Main main;
    private ObservableList<RequestStatus> requestStatuses;
    private ObservableList<RequestStatusDisplay> requestStatusesView;
    private int selected_id;
    
    @FXML
    private TableView<RequestStatusDisplay> tableRequestStatus;
    @FXML
    private TableColumn<RequestStatusDisplay,String> nameColumn;

    @FXML
    private void goEditRequestStatus() throws IOException {
        ContextFX.getInstance().setId(selected_id);
        main.showEditRequestStatus();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableRequestStatus.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.idRequestStatusProperty().getValue().intValue();
            }
        );
        try {
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            /**/
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<Role> getRequestStatuses() {
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
        sessionFactory.close();
        return returnable;
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        requestStatuses = FXCollections.observableArrayList();
        requestStatusesView = FXCollections.observableArrayList();
        requestStatuses = getRequestStatuses();
        for (int i = 0; i < requestStatuses.size(); i++) {
            RequestStatusDisplay status = new RequestStatusDisplay(requestStatuses.get(i).getId_request_status(), requestStatuses.get(i).getName());
            requestStatusesView.add(status);
        }
        tableRequestStatus.setItems(null);
        tableRequestStatus.setItems(requestStatusesView);
    }
}

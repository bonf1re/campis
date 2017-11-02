/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.departures;

import campis.dp1.Main;
import campis.dp1.models.DispatchMove;
import campis.dp1.models.DispatchMoveDisplay;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
 * FXML Controller class
 *
 * @author david
 */
public class ListDepartureController implements Initializable {

    private Main main;
    private ObservableList<DispatchMove> dispatch;
    private ObservableList<DispatchMoveDisplay> dispatchView = FXCollections.observableArrayList();
    
    @FXML
    private TableView<DispatchMoveDisplay> departureTable;
    @FXML
    private TableColumn<DispatchMoveDisplay, Integer> idDepartureColumn;
    @FXML
    private TableColumn<DispatchMoveDisplay, Integer> typeColumn;
    @FXML
    private TableColumn<DispatchMoveDisplay, String> departureDateColumn;
    @FXML
    private TableColumn<DispatchMoveDisplay, Integer> reasonColumn;
    
    @FXML
    private void goVisualizeDeparture() throws IOException {
        main.showVisualizeDeparture();
    }
    
    @FXML
    private void goNewDeparture() throws IOException {
        main.showNewDeparture();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idDepartureColumn.setCellValueFactory(cellData -> cellData.getValue().id_group_batchProperty().asObject());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().type_ownerProperty().asObject());
        departureDateColumn.setCellValueFactory(cellData -> cellData.getValue().arrival_dateProperty());
        reasonColumn.setCellValueFactory(cellData -> cellData.getValue().reasonProperty().asObject());
        loadData();
    }    

    private void loadData() {
        dispatch = FXCollections.observableArrayList();
        ObservableList<DispatchMove> dispatch = getDispatch();
        for (int i = 0; i < dispatch.size(); i++) {
            DispatchMoveDisplay request = new DispatchMoveDisplay(dispatch.get(i).getId_group_batch(), 
                                        dispatch.get(i).getTyoe_owner(),dispatch.get(i).getId_owner(),
                                        dispatch.get(i).getArrival_date().toString(), dispatch.get(i).getReason());
            dispatchView.add(request);
        }
        departureTable.setItems(null);
        departureTable.setItems(dispatchView);
    }

    private ObservableList<DispatchMove> getDispatch() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchMove.class);
        List<DispatchMove> list = criteria.list();
        ObservableList<DispatchMove> returnable = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            returnable.add(list.get(i));
        }
        return returnable;
    }
    
}

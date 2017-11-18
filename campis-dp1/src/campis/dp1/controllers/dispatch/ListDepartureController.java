/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.DispatchMove;
import campis.dp1.models.DispatchMoveDisplay;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ListDepartureController implements Initializable {

    private Main main;
    private int selected_id;
    private int id_role;
    private ObservableList<DispatchMove> dispatch;
    private ObservableList<DispatchMoveDisplay> dispatchView = FXCollections.observableArrayList();

    @FXML
    private TableView<DispatchMoveDisplay> departureTable;
    @FXML
    private TableColumn<DispatchMoveDisplay, Integer> idDepartureColumn;
    @FXML
    private TableColumn<DispatchMoveDisplay, String> typeColumn;
    @FXML
    private TableColumn<DispatchMoveDisplay, String> departureDateColumn;
    @FXML
    private TableColumn<DispatchMoveDisplay, String> reasonColumn;
    @FXML
    private Button spButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button depButton;

    @FXML
    private void goVisualizeDeparture() throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showVisualizeDeparture();
        }
    }

    @FXML
    private void goNewDeparture() throws IOException {
        main.showNewDeparture();
    }
    
    @FXML
    private void goNormalDispatch() throws IOException {
        main.showNewNormalDeparture();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        ContextFX.getInstance().modifyValidation(spButton, depButton, deleteButton, id_role, "departures_dispatch");
        try {
            departureTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        if (newValue == null) {
                            return;
                        }
                        this.selected_id = newValue.id_dispatch_moveProperty().getValue().intValue();
                    }
            );
            idDepartureColumn.setCellValueFactory(cellData -> cellData.getValue().id_dispatch_moveProperty().asObject());
            typeColumn.setCellValueFactory(cellData -> cellData.getValue().type_owner_textProperty());
            departureDateColumn.setCellValueFactory(cellData -> cellData.getValue().arrival_dateProperty());
            reasonColumn.setCellValueFactory(cellData -> cellData.getValue().reason_textProperty());
            loadData();
        } catch (NullPointerException e) {
            idDepartureColumn.setCellValueFactory(cellData -> cellData.getValue().id_dispatch_moveProperty().asObject());
            typeColumn.setCellValueFactory(cellData -> cellData.getValue().type_owner_textProperty());
            departureDateColumn.setCellValueFactory(cellData -> cellData.getValue().arrival_dateProperty());
            reasonColumn.setCellValueFactory(cellData -> cellData.getValue().reason_textProperty());
        }
    }

    private void loadData() {
        dispatch = FXCollections.observableArrayList();
        ObservableList<DispatchMove> dispatch = getDispatch();
        for (int i = 0; i < dispatch.size(); i++) {
            String reason = getReason(dispatch.get(i).getReason());
            String type_owner = getTypeOwner(dispatch.get(i).getType_owner());
            
            DispatchMoveDisplay request = new DispatchMoveDisplay(dispatch.get(i).getId_dispatch_move(),
                    dispatch.get(i).getType_owner(), type_owner, dispatch.get(i).getId_owner(),
                    dispatch.get(i).getMov_date().toString(), dispatch.get(i).getReason(), reason);
            
            dispatchView.add(request);
        }
        departureTable.setItems(null);
        departureTable.setItems(dispatchView);
    }
    
    private String getReason(Integer r) {
        String reason = " ";
        
        if (null != r) switch (r) {
            case 1:
                reason = "Para la venta";
                break;
            case 2:
                reason = "Traslado hacia almacen";
                break;
            case 5:
                reason = "Producto dañado";
                break;
            case 6:
                reason = "Producto vencido";
                break;
            default:
                break;
        }
        
        return reason;
    }
    
    private String getTypeOwner(Integer t_owner) {
        String reason = " ";
        
        if (null != t_owner) switch (t_owner) {
            case 0:
                reason = "Desechado";
                break;
            case 1:
                reason = "Almacen";
                break;
            case 2:
                reason = "Cliente";
                break;
            default:
                break;
        }
        
        return reason;
    }

    private ObservableList<DispatchMove> getDispatch() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchMove.class);
        Integer rs1 = 1;
        Integer rs2 = 2;
        Integer rs3 = 5;
        Integer rs4 = 6;
        criteria.add(Restrictions.disjunction().add(Restrictions.eq("reason",rs1))
                .add(Restrictions.eq("reason",rs2)).add(Restrictions.eq("reason",rs3)).add(Restrictions.eq("reason",rs4)));
        List<DispatchMove> list = criteria.list();
        ObservableList<DispatchMove> returnable = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            returnable.add(list.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private void eliminateDispatch(int selected_id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DispatchMove.class);
        DispatchMove dispatch = new DispatchMove();
        dispatch.setId_dispatch_move(selected_id);
        session.delete(dispatch);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @FXML
    private void deleteDispatch(ActionEvent event) {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            Integer id_dispatch = ContextFX.getInstance().getId();
            eliminateDispatch(selected_id);
            dispatch = getDispatch();
            for (int i = 0; i < dispatch.size(); i++) {
                if (dispatch.get(i).getId_dispatch_move().compareTo(id_dispatch) == 0) {
                    dispatch.remove(i);
                }
            }
            for (int i = 0; i < dispatch.size(); i++) {
                DispatchMoveDisplay request = new DispatchMoveDisplay(dispatch.get(i).getId_dispatch_move(),
                        dispatch.get(i).getType_owner(), dispatch.get(i).getId_owner(),
                        dispatch.get(i).getMov_date().toString(), dispatch.get(i).getReason());
                dispatchView.add(request);
            }
            departureTable.setItems(null);
            departureTable.setItems(dispatchView);
        }
    }
}

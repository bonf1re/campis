/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Warehouse;
import campis.dp1.models.WarehouseMove;
import campis.dp1.models.WarehouseMoveDisplay;
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
import jdk.nashorn.internal.runtime.Context;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sergio
 */
public class EntryMoveListController implements Initializable {
    private Main main;
    private ObservableList<WarehouseMove> whMoves;
    private ObservableList<WarehouseMoveDisplay> whMovesView;
    private int selected_id;
    private int warehouse_id;
    @FXML
    private TableView<WarehouseMoveDisplay> whEntryMoveTable;
    @FXML
    private TableColumn<WarehouseMoveDisplay, Integer> idCol;
    @FXML
    private TableColumn<WarehouseMoveDisplay, String> dateCol;
    @FXML
    private TableColumn<WarehouseMoveDisplay, Integer> userCol;
    @FXML
    private TableColumn<WarehouseMoveDisplay, Integer> qtCol;
    @FXML
    private TableColumn<WarehouseMoveDisplay, Integer> zoneCol;
    @FXML
    private TableColumn<WarehouseMoveDisplay, Integer> vehiCol;
    @FXML
    private TableColumn<WarehouseMoveDisplay, Integer> movCol;
    
    @FXML
    private void goWhList() throws IOException{
        main.showWhList();
    }
    
    @FXML
    private void goDepartureMoveList() throws IOException{
        ContextFX.getInstance().setId(this.warehouse_id);
        main.showWhDepartureMoveList();
    }
    
    @FXML
    private void goWhEntryMoveNormalCreate() throws IOException{
        ContextFX.getInstance().setId(warehouse_id);
        main.showWhEntryMoveNormalCreate();
    }
    
    @FXML
    private void goWhEntryMoveSpecialCreate() throws IOException{
        ContextFX.getInstance().setId(this.warehouse_id);
        main.showWhEntryMoveSpecialCreate();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.warehouse_id=ContextFX.getInstance().getId();
        
        whEntryMoveTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.getId_movement().getValue().intValue();
            }
        );
        try{
            idCol.setCellValueFactory(cellData -> cellData.getValue().getId_movement().asObject());
            dateCol.setCellValueFactory(cellData -> cellData.getValue().getMov_date());
            movCol.setCellValueFactory(cellData -> cellData.getValue().getMov_type().asObject());
            this.qtCol.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
            this.userCol.setCellValueFactory(cellData -> cellData.getValue().getId_user().asObject());
            this.vehiCol.setCellValueFactory(cellData -> cellData.getValue().getId_vehicle().asObject());
            this.zoneCol.setCellValueFactory(cellData -> cellData.getValue().getId_zone().asObject());
            warehouseMovesLoadData();
        } catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(EntryMoveListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void warehouseMovesLoadData() throws SQLException, ClassNotFoundException{
        whMoves = FXCollections.observableArrayList();
        whMovesView = FXCollections.observableArrayList();
        whMoves = getWhMoves();
        for (int i = 0; i < whMoves.size(); i++) {
            WarehouseMove whMov = (WarehouseMove) whMoves.get(i);
            WarehouseMoveDisplay whMov_display = new WarehouseMoveDisplay(whMov);
            whMovesView.add(whMov_display);
        }
        whEntryMoveTable.setItems(null);
        whEntryMoveTable.setItems(whMovesView);
    }

    private ObservableList<WarehouseMove> getWhMoves() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(WarehouseMove.class);
        System.out.println(this.warehouse_id);
        
        criteria.add(Restrictions.eq("id_warehouse",this.warehouse_id));
        criteria.add(Restrictions.eq("mov_type", 1));
        List whList = criteria.list();
        ObservableList<WarehouseMove> returnable=FXCollections.observableArrayList();
        for (int i = 0; i < whList.size(); i++) {
            returnable.add((WarehouseMove) whList.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }
}

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
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.hibernate.query.Query;

/**
 *
 * @author sergio
 */
public class EntryMoveListController implements Initializable {
    private Main main;
    private ObservableList<WarehouseMove> whMoves;
    private ObservableList<WarehouseMoveDisplay> whMovesView;
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
    JFXComboBox<String> cbWarehouses;
    ArrayList<Warehouse> listWarehouses=new ArrayList<>();
    

    
    
    @FXML
    private void goWhEntryMoveNormalCreate() throws IOException{
        ContextFX.getInstance().setId(warehouse_id);
        main.showWhEntryMoveNormalCreate();
    }
    
    @FXML
    private void goWhEntryMoveSpecialCreate() throws IOException{
        ContextFX.getInstance().setId(this.warehouse_id);
        ContextFX.getInstance().setMode(0);
        main.showWhEntryMoveSpecialCreate();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        try{
            this.warehouse_id=ContextFX.getInstance().getId();
        }catch(Exception e){
            Query q = session.createSQLQuery("SELECT id_warehouse FROM campis.wh_config WHERE wh_type=0");
            if (q.list().size()==0) return;
            this.warehouse_id = (int) q.list().get(0);
        }
        
        setupCbWarehouse();
        loadCbWarehouse(session);
        
        
        try{
            idCol.setCellValueFactory(cellData -> cellData.getValue().getId_movement().asObject());
            dateCol.setCellValueFactory(cellData -> cellData.getValue().getMov_date());
            movCol.setCellValueFactory(cellData -> cellData.getValue().getMov_type().asObject());
            this.qtCol.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
            this.userCol.setCellValueFactory(cellData -> cellData.getValue().getId_user().asObject());
            this.vehiCol.setCellValueFactory(cellData -> cellData.getValue().getId_vehicle().asObject());
            this.zoneCol.setCellValueFactory(cellData -> cellData.getValue().getId_zone().asObject());
            warehouseMovesLoadData(session);
            
            
        } catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(EntryMoveListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.close();
        sessionFactory.close();
    }

    private void warehouseMovesLoadData(Session session) throws SQLException, ClassNotFoundException{
        whMoves = FXCollections.observableArrayList();
        whMovesView = FXCollections.observableArrayList();
        whMoves = getWhMoves(session);
        for (int i = 0; i < whMoves.size(); i++) {
            WarehouseMove whMov = (WarehouseMove) whMoves.get(i);
            WarehouseMoveDisplay whMov_display = new WarehouseMoveDisplay(whMov);
            whMovesView.add(whMov_display);
        }
        whEntryMoveTable.setItems(null);
        whEntryMoveTable.setItems(whMovesView);
    }

    private ObservableList<WarehouseMove> getWhMoves(Session session) {
        
        Criteria criteria = session.createCriteria(WarehouseMove.class);
        System.out.println(this.warehouse_id);
        
        criteria.add(Restrictions.eq("id_warehouse",this.warehouse_id));
        criteria.add(Restrictions.lt("mov_type", 2));
        List whList = criteria.list();
        ObservableList<WarehouseMove> returnable=FXCollections.observableArrayList();
        for (int i = 0; i < whList.size(); i++) {
            returnable.add((WarehouseMove) whList.get(i));
        }
        
        return returnable;
    }

    private void loadCbWarehouse(Session session) {
        Query q = session.createSQLQuery("SELECT w.* FROM campis.warehouse w\n"+
                                        "INNER JOIN\n"
                                        +"campis.wh_config c ON c.id_warehouse=w.id_warehouse\n"+
                                        "ORDER BY wh_type");
            List<Object[]> list_wh = (List<Object[]>)q.list();
            if (list_wh.size()==0) return;
            this.listWarehouses = new ArrayList<>();
            for (Object[] row : list_wh) {
                this.listWarehouses.add(new Warehouse((int)row[0], (String)row[1], (int)row[2], (int)row[3], (boolean)row[4]));
            }
            
            ArrayList<String> wh_names = new ArrayList<>();
            for (Warehouse listWarehouse : this.listWarehouses) {
                wh_names.add(listWarehouse.getName());
            }
            cbWarehouses.getItems().addAll(wh_names);
            cbWarehouses.getSelectionModel().selectFirst();
    }

    private void setupCbWarehouse() {
        cbWarehouses.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            int index_wh=cbWarehouses.getSelectionModel().getSelectedIndex();
            this.warehouse_id= listWarehouses.get(index_wh).getId();
            try {
                Configuration conf2 = new Configuration();
                conf2.configure("hibernate.cfg.xml");
                conf2.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
                SessionFactory sessionF2 = conf2.buildSessionFactory();
                Session s2 = sessionF2.openSession();
                s2.beginTransaction();
                warehouseMovesLoadData(s2);
                s2.close();
                sessionF2.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntryMoveListController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EntryMoveListController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        );
    }
}

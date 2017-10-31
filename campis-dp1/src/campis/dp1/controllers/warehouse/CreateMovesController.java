/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.Main;
import campis.dp1.models.Batch;
import campis.dp1.models.BatchDisplay;
import campis.dp1.models.WarehouseMoveDisplay;
import java.awt.Checkbox;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sergio
 */
public class CreateMovesController implements Initializable{
    private Main main;
    ObservableList<Batch> batches;
    ObservableList<BatchDisplay> batchesView;
    
    
    @FXML
    private TableView<BatchDisplay> batchTable;
    
     @FXML
    private TableColumn<BatchDisplay, Integer> idCol;

    @FXML
    private TableColumn<BatchDisplay, Integer> qtCol;

    @FXML
    private TableColumn<BatchDisplay, Float> costCol;

    @FXML
    private TableColumn<BatchDisplay, String> arrCol;

    @FXML
    private TableColumn<BatchDisplay, String> expCol;

    @FXML
    private TableColumn<BatchDisplay, Integer> prodCol;

    @FXML
    private TableColumn<BatchDisplay, String> herCol;

    @FXML
    private TableColumn<BatchDisplay, Boolean> selCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        batchTable.getSelectionModel().selectedItemProperty().addListener(
//        (observable, oldValue, newValue) -> {
//            if (newValue == null) {
//                return;
//            }
//            this.selected_id = newValue.id_warehouseProperty().getValue().intValue();
//            }
//        );
        try{
            idCol.setCellValueFactory(cellData -> cellData.getValue().getId_batch().asObject());
            qtCol.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
            costCol.setCellValueFactory(cellData -> cellData.getValue().getBatch_cost().asObject());
            arrCol.setCellValueFactory(cellData -> cellData.getValue().getArrival_date());
            expCol.setCellValueFactory(cellData -> cellData.getValue().getExpiration_date());
            prodCol.setCellValueFactory(cellData -> cellData.getValue().getId_product().asObject());
            herCol.setCellValueFactory(cellData-> cellData.getValue().getHeritage());
            // set column for checkbox
            selCol.setCellFactory(
                CheckBoxTableCell.forTableColumn(selCol)
            );
            selCol.setCellValueFactory(
                    cellData->cellData.getValue().getSelected()
            );
            // make sure table is editable so checkbox can be checked n unchecked
            batchTable.setEditable(true);
            batchLoadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListWarehouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void batchLoadData() throws SQLException, ClassNotFoundException{
        batches = FXCollections.observableArrayList();
        batchesView = FXCollections.observableArrayList();
        batches = getBatches();
        for (int i = 0; i < batches.size(); i++) {
            Batch batch = (Batch) batches.get(i);
            BatchDisplay batchDisplay = new BatchDisplay(batch);
            batchesView.add(batchDisplay);
        }
        batchTable.setItems(null);
        batchTable.setItems(batchesView);
        
    }

    private ObservableList<Batch> getBatches() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Batch.class);
        criteria.add(Restrictions.eq("type_batch",2));
        List batchList = criteria.list();
        ObservableList<Batch> returnable = FXCollections.observableArrayList();
        for (int i = 0; i < batchList.size(); i++) {
            returnable.add((Batch)batchList.get(i));
        }
        sessionFactory.close();
        return returnable;
    }
    
}

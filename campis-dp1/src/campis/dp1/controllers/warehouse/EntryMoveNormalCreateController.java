/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Batch;
import campis.dp1.models.BatchDisplay;
import campis.dp1.models.Product;
import campis.dp1.models.WarehouseMoveDisplay;
import java.awt.Checkbox;
import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sergio
 */
public class EntryMoveNormalCreateController implements Initializable{
    private Main main;
    private int id_warehouse_back;
    ObservableList<Batch> batches;
    ObservableList<BatchDisplay> batchesView;
    ArrayList<Product> product_names = new ArrayList<>();
    
    
    @FXML
    private TableView<BatchDisplay> batchTable;
    
     @FXML
    private TableColumn<BatchDisplay, Integer> idCol;

    @FXML
    private TableColumn<BatchDisplay, String> prodCol;
     
    @FXML
    private TableColumn<BatchDisplay, Integer> qtCol;

    @FXML
    private TableColumn<BatchDisplay, String> arrCol;

    @FXML
    private TableColumn<BatchDisplay, String> expCol;

    @FXML
    private TableColumn<BatchDisplay, String> herCol;

    @FXML
    private TableColumn<BatchDisplay, Boolean> selCol;
    
    @FXML
    private TableColumn<BatchDisplay, Integer> numCol;
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.id_warehouse_back = ContextFX.getInstance().getId();
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
            prodCol.setCellValueFactory(cellData -> new SimpleStringProperty(getNameProduct(cellData.getValue().getId_product().get())));
            qtCol.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
            arrCol.setCellValueFactory(cellData -> cellData.getValue().getArrival_date());
            expCol.setCellValueFactory(cellData -> cellData.getValue().getExpiration_date());
            
            herCol.setCellValueFactory(cellData-> cellData.getValue().getHeritage());
            // set column for checkbox
            selCol.setCellFactory(
                CheckBoxTableCell.forTableColumn(selCol)
            );
            selCol.setCellValueFactory(
                    cellData->cellData.getValue().getSelected()
            );
            numCol.setCellFactory(
                TextFieldTableCell.<BatchDisplay,Integer>forTableColumn(new StringConverter<Integer>(){
                    @Override
                    public String toString(Integer value){
                        return value.toString();
                    }
                    @Override
                    public Integer fromString(String string){
                        return Integer.parseInt(string);
                    }
                }));
            numCol.setCellValueFactory(
                    cellData->cellData.getValue().getNumMove().asObject()
            );;
            // make sure table is editable so checkbox can be checked n unchecked   
            batchTable.setEditable(true);
            batchLoadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(WarehouseListController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        // To fill products names
        for (Object batch : batchList) {
            Criteria criteria_name = session.createCriteria(Product.class);
            Batch batch_casted = (Batch) batch;
            criteria_name.add(Restrictions.eq("id_product", batch_casted.getId_product()));
            List prodList=criteria_name.list();
            this.product_names.add((Product) prodList.get(0));
        }
        
        session.close();
        sessionFactory.close();
        return returnable;
    }
    
    private ArrayList<Integer> getMarked(){
        ArrayList<Integer> returnable = new ArrayList<>();
        // TODO
        for (BatchDisplay item : batchTable.getItems()) {
            boolean selected = item.getSelected().getValue();
            if (selected ==  true){
                returnable.add(item.getId_batch().get());
            }
        }
        
        return returnable;
    }
    
    @FXML
    private void goWhEntryMoveNormalCreate() throws IOException{
        ContextFX.getInstance().setId(id_warehouse_back);
        main.showWhEntryMoveNormalCreate();
        
    }
    
    @FXML
    private void goWhEntryMoveRoute() throws IOException{
        List aux = getMarked();
        ContextFX.getInstance().setList(aux);
        ContextFX.getInstance().setId(id_warehouse_back);
        main.showWhEntryMoveRoute();
    }

    private String getNameProduct(int get) {
        for (Product prod: product_names) {
            if (prod.getId_product() == get){
                return prod.getName();
            }
        }
        return " ";
    }
    
}

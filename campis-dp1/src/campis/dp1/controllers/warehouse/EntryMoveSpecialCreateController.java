/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Batch;
import campis.dp1.models.BatchWH_Move;
import campis.dp1.models.Product;
import campis.dp1.models.ProductType;
import campis.dp1.models.ProductWH_Move;
import campis.dp1.models.WarehouseZone;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
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
public class EntryMoveSpecialCreateController implements Initializable {
    private Main main;
    private int id_warehouse;
    private ObservableList<ProductWH_Move> prodList = FXCollections.observableArrayList();
    
    @FXML
    private TableView<ProductWH_Move> batchTable;

    @FXML
    private TableColumn<ProductWH_Move, String> prodCol;

    @FXML
    private TableColumn<ProductWH_Move, Integer> qtCol;

    @FXML
    private TableColumn<ProductWH_Move, Integer> numCol;

    @FXML
    private TableColumn<ProductWH_Move, String> delCol;

    @FXML
    private JFXComboBox<String> cbMotive;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id_warehouse = ContextFX.getInstance().getId();
        cbMotive.getItems().addAll("Hallazgo","Proveedores");
         try{
            prodCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            qtCol.setCellFactory(
                TextFieldTableCell.<ProductWH_Move,Integer>forTableColumn(new StringConverter<Integer>(){
                    @Override
                    public String toString(Integer value){
                        return value.toString();
                    }
                    @Override
                    public Integer fromString(String string){
                        return Integer.parseInt(string);
                    }       
            }));
            qtCol.setCellValueFactory(cellData -> cellData.getValue().getQtLt().asObject());
            numCol.setCellFactory(
                TextFieldTableCell.<ProductWH_Move,Integer>forTableColumn(new StringConverter<Integer>() {
                @Override
                public String toString(Integer object) {
                    return object.toString();
                }

                @Override
                public Integer fromString(String string) {
                    return Integer.parseInt(string);
                }
            })
            );
            numCol.setCellValueFactory(cellData -> cellData.getValue().getNum().asObject());
            delCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
            
            Callback<TableColumn<ProductWH_Move, String>, TableCell<ProductWH_Move, String>> cellFactory
                    = //
                new Callback<TableColumn<ProductWH_Move, String>, TableCell<ProductWH_Move, String>>() {
                @Override
                public TableCell<ProductWH_Move, String> call(TableColumn<ProductWH_Move, String> param) {
                    final TableCell<ProductWH_Move, String> cell = new TableCell<ProductWH_Move, String>() {
                    final Button btn = new Button("Eliminar");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                getTableView().getItems().remove(getIndex());
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                    return cell;
            }
                };
            delCol.setCellFactory(cellFactory);
            batchTable.setEditable(true);
            loadData();
        } catch (Exception ex) {
            Logger.getLogger(WarehouseListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchProds() throws IOException{
        ContextFX.getInstance().setWhMovesProdList(prodList);
        ContextFX.getInstance().setId(id_warehouse);
        main.showWhEntryMoveAddProd();
    }
    
    public void goWhEntryMoveRoute() throws IOException{
        ContextFX.getInstance().setWhMoveType(1);
        ContextFX.getInstance().setList(makeBatches());
        ContextFX.getInstance().setId(this.id_warehouse);
        main.showWhEntryMoveRoute();
    }
    
    public void goWhEntryMoveList() throws IOException{
        ContextFX.getInstance().setId(id_warehouse);
        main.showWhEntryMoveList();
    }
    
    

    private void loadData() {        
        try{
            this.prodList = (ObservableList<ProductWH_Move>) FXCollections.observableArrayList((ArrayList<ProductWH_Move>)ContextFX.getInstance().getWhMovesProdList());
        }catch(Exception e){
            System.out.println("Caught in first exception");
        }
        
        try{
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Product.class);
            criteria.add(Restrictions.eq("id_product",ContextFX.getInstance().getNum()));            
            List rsType = criteria.list();
            Product result = (Product) rsType.get(0);
            this.prodList.add(new ProductWH_Move(result,ContextFX.getInstance().getQuantity()));
            session.close();
            sessionFactory.close();
        }catch(Exception e){
            System.out.println("Caught in second exception ffs");
            e.printStackTrace();
        }
        
        batchTable.setItems(null);
        batchTable.setItems(this.prodList);
    }

    private List makeBatches() {
        ArrayList<BatchWH_Move> returnable = new ArrayList<>();
        ArrayList<Batch> aux_batches = new ArrayList<>();
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        for (int i = 0; i < this.prodList.size(); i++) {
            // qtlt is for quantity of prod per lot
            // num is for total num of lots
            ProductWH_Move prod = this.prodList.get(i);
            int qt = prod.getQtLt().get();
            int num = prod.getNum().get();
            for (int j = 0; j < num; j++) {
                aux_batches.add(new Batch(qt,prod.getBase_price().intValue(),currentTimestamp,currentTimestamp,prod.getId_product(),2,Integer.toString(this.id_warehouse),true));
            }
        }
        ArrayList<WarehouseZone> zones = getZones(aux_batches.size());
        for (int i = 0; i < aux_batches.size(); i++) {
            returnable.add(new BatchWH_Move(aux_batches.get(i), zones.get(i)));
        }
        return returnable;
    }
    
     private ArrayList<WarehouseZone> getZones(int batch_list_size) {
        // insert where free
        // then will add filter per area
        ArrayList<WarehouseZone> returnable =  new ArrayList<>();
        // For the moment, we will use pitagoric distance to select the top areas
        // sorted insert
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(WarehouseZone.class);
        criteria.add(Restrictions.eq("free",true));
        List zones = criteria.list();
        
        for (int i = 0; i < zones.size(); i++) {
            WarehouseZone zz = (WarehouseZone) zones.get(i);
            for (int j = i; j >= 0; j--) {
                if (j==0){
                    returnable.add(zz);
                    break;
                }
                int previous = returnable.get(j-1).getPos_x()+returnable.get(j-1).getPos_y();
                int actual = zz.getPos_x()+zz.getPos_y();
                if (previous<actual){
                    returnable.add(j,zz);
                    break;
                }
                if (j==1){
                    returnable.add(0,zz);
                    break;
                }
            }
        }
        session.close();
        sessionFactory.close();
//        try{
        return new ArrayList<WarehouseZone>(returnable.subList(0, batch_list_size));
//        }catch(Exception e){
//            return new ArrayList<WarehouseZone>();
//        }
    }
    
}

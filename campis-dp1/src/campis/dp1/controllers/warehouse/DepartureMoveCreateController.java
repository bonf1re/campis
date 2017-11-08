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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sergio
 */
public class DepartureMoveCreateController implements Initializable {
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
        cbMotive.getItems().addAll("Perdida","Despacho","Transferencia a otro almacen");
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
        main.showWhDepartureMoveAddProd();
    }
    
    public void goWhDepartureMoveRoute() throws IOException{
        ContextFX.getInstance().setWhMoveType(0);
        List aux_list = getLocations();
        try{
            Batch batch = (Batch) aux_list.get(0);
            System.out.println(batch.getId_batch());
            System.out.println("raro");
            ContextFX.getInstance().setList(aux_list);
            ContextFX.getInstance().setId(this.id_warehouse);
            main.showWhDepartureMoveRoute();
        }catch(Exception e){
            
        }
        
    }
    
    public void goWhDepartureMoveList() throws IOException{
        ContextFX.getInstance().setId(id_warehouse);
        main.showWhDepartureMoveList();
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

    private List getLocations() {
        ArrayList<BatchWH_Move> returnable = new ArrayList<>();        
        //Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int[][] qt_per_prod = qtPerProduct();
        int qt_per_prod_size = qt_per_prod[0][0];
        for (int i = 1; i <= qt_per_prod_size; i++) {
            // Pile up batches until we satisfy the quantity needs of each product
            int prod_id = qt_per_prod[i][0];
            int prod_num = qt_per_prod[i][1];
            SQLQuery query = session.createSQLQuery(" WITH b as (\n" +
                    "        SELECT * from campis.batch b\n" +
                    "        where type_batch = 3 AND\n" +
                    "              id_product = "+Integer.toString(prod_id)+" \n" +
                    "        order by expiration_date\n" +
                    "), mov as (\n" +
                    "        SELECT b.*, id_zone from campis.movement m, b\n" +
                    "        where b.id_batch = m.id_batch and\n" +
                    "              m.mov_type = 1 and\n" +
                    "              m.id_warehouse = "+Integer.toString(this.id_warehouse)+"\n" +
                    ")\n" +
                    "\n" +
                    "SELECT \n" +
                    "        mov.*, z.id_rack, z.pos_x, z.pos_y, z.pos_z, z.free\n" +
                    "        from mov, campis.zone z\n" +
                    "        where mov.id_zone = z.id_zone;");
            
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                int batchQt = Integer.parseInt(row[1].toString());
                Batch batch = new Batch();
                batch.setId_batch(Integer.parseInt(row[0].toString()));
                batch.setId_product(prod_id);
                batch.setArrival_date(null);
                batch.setExpiration_date(null);
                batch.setBatch_cost(Float.parseFloat(row[2].toString()));
                batch.setQuantity(batchQt);
                batch.setLocation(" ");
                batch.setType_batch(3);
                batch.setState(true);
                batch.setHeritage(" ");
                
                WarehouseZone zone = new WarehouseZone();
                zone.setFree(false);
                zone.setId_zone(Integer.parseInt(row[11].toString()));
                zone.setId_rack(Integer.parseInt(row[12].toString()));
                zone.setId_warehouse(id_warehouse);
                zone.setPos_x(Integer.parseInt(row[13].toString()));
                zone.setPos_y(Integer.parseInt(row[14].toString()));
                zone.setPos_z(Integer.parseInt(row[15].toString()));
                
                int diff = prod_num-batchQt;
                //save on list
                returnable.add(new BatchWH_Move(batch,zone));
                if (diff <= 0){
                    break;
                }
            }
            
        }
        return returnable;
    }

    private int[][] qtPerProduct() {
        int[][] returnable= new int[100][100];
        int counter = 0;
        for (ProductWH_Move productWH_Move : prodList) {
            if (counter==0){
                counter++;
                returnable[counter][0] = productWH_Move.getId_product();
                returnable[counter][1] = productWH_Move.getNum().get()*productWH_Move.getQtLt().get();
            }else{
                counter++;
                int found=0;
                for (int i = 1; i < counter; i++) {
                    if (returnable[i][0]==productWH_Move.getId_product()){
                        returnable[i][1]+=productWH_Move.getNum().get()*productWH_Move.getQtLt().get();
                        found=1;
                        break;
                    }
                }
                if (found==0){
                    // not found
                    returnable[counter][0]=productWH_Move.getId_product();
                    returnable[counter][1]=productWH_Move.getNum().get()*productWH_Move.getQtLt().get();
                }
                
            }
            
        }
        returnable[0][0]=counter;
        return returnable;
    }
    
    
}

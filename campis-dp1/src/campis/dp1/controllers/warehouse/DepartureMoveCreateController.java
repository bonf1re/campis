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
import campis.dp1.models.CGraph;
import campis.dp1.models.CRack;
import campis.dp1.models.ProductWH_Move;
import campis.dp1.models.Vehicle;
import campis.dp1.models.Warehouse;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
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
public class DepartureMoveCreateController implements Initializable{
    private Main main;
    private int id_warehouse;
    
    
    /* vehicles */
    private int selected_vh1;
    private int selected_vh2;
    ObservableList<Vehicle> vh1View;
    ObservableList<Vehicle> vh2View;
    /* end vehicles */
    
    /* routing */
    private ArrayList<Object> polymorphic_list;
    private ArrayList<CRack> crackList = new ArrayList<>();
    private CGraph routesGraph = new CGraph();
    private int[][] real_map;
    private int x;
    private int y;
    private Warehouse wh;
    /* end routing */
    
    /* search_prod */
    private ObservableList<ProductWH_Move> prodList = FXCollections.observableArrayList();
    private int mode;
    /* end search_prod */ 
    
    
    @FXML
    private TableView<ProductWH_Move> productsTable;

    @FXML
    private TableColumn<ProductWH_Move, String> prodCol;

    @FXML
    private TableColumn<ProductWH_Move, Integer> cantCol;
    
    @FXML
    private TableColumn<ProductWH_Move, Integer> stockCol;

    @FXML
    private TableColumn<ProductWH_Move, String> delCol;

    @FXML
    private JFXComboBox<String> cbMotive;
 
    @FXML
    private TableView<Vehicle> vh1Table;
    @FXML
    private TableColumn<Vehicle, String> pc1Col;
    @FXML
    private TableColumn<Vehicle, String> cp1Col;
    @FXML
    private TableView<Vehicle> vh2Table;
    @FXML
    private TableColumn<Vehicle, String> pc2Col;
    @FXML
    private TableColumn<Vehicle, String> cp2Col;
    
    
    
    
    
    
    public void goDepartureMoveRoute(){
        // Verify stock and capacity
        double total_weight = weight_check();
        if (total_weight<0){
            System.out.println("No hay suficientes carritos para el peso a transportar, se ha sobrepasado la cantidad maxima de un producto por lote o no se ha seleccionado algo.");
            return;
        }
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        
        if (enoughStock(session)==false){
            System.out.println("No existe stock suficiente para abastecer requerimientos");
        }

        // get batches to remove
        ArrayList<Batch> batches = getBatches(session);
        
        
        
        
    }
    
    private double weight_check() {
        double total_weight=0;
        if (vh2View.size()==0) return -1;
        for (int i = 0; i < vh2View.size(); i++) {
            total_weight+=vh2View.get(i).getMax_weight();
        }
        
        int prods_counter=0;
        for (ProductWH_Move prod : productsTable.getItems()) {
                //if (prod.getQtLt().get()>prod.getMax_qt()) return -1;
                //if (prod.getQtLt().get() <= 0 || prod.getNum().get() <= 0) return -1;
                if (prod.getCant().get() <= 0) return -1;
                //total_weight-=prod.getWeight()*prod.getNum().get()*prod.getQtLt().get();
                total_weight-=prod.getWeight()*prod.getCant().get();
                prods_counter++;
        }
        if (prods_counter==0) return -1;
        return total_weight;
    }
    
    public void goWhDepartureMoveList() throws IOException{
        ContextFX.getInstance().setId(this.id_warehouse);
        main.showWhDepartureMoveList();
    }
    
    public void searchProds() throws IOException{
        ContextFX.getInstance().setId(this.id_warehouse);
        this.polymorphic_list = new ArrayList<>();
        polymorphic_list.add(this.prodList);
        polymorphic_list.add(this.vh1View);
        polymorphic_list.add(this.vh2View);
        // 0 - prodList
        // 1 - vh1View
        // 2 - vh2View
        ContextFX.getInstance().setPolymorphic_list(polymorphic_list);
        main.showWhDepartureAddItem();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id_warehouse = ContextFX.getInstance().getId();
        this.mode = ContextFX.getInstance().getMode();
        if(this.mode!=0){
            this.polymorphic_list = ContextFX.getInstance().getPolymorphic_list();
            System.out.println(((ObservableList)this.polymorphic_list.get(0)).size());
        }
        setupProductsTable();
        setupVh1Table();
        setupVh2Table();
        
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        load_ProductsData(session);
        load_Vh1Data(session);
        load_Vh2Data(session);
        
        
        session.close();
        sessionFactory.close();
    }

    private void setupProductsTable() {
        
        cbMotive.getItems().addAll("Transferencia","Despacho","Perdida","Roto");
         try{
            prodCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            cantCol.setCellFactory(
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
            cantCol.setCellValueFactory(cellData -> cellData.getValue().getCant().asObject());
            stockCol.setCellValueFactory(cellData -> cellData.getValue().getStock().asObject());
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
            productsTable.setEditable(true);
    } catch (Exception ex) {
            Logger.getLogger(WarehouseListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    private void load_ProductsData(Session session) {
        if (this.mode!=0){
            // Add previously added products and their quantities
            // 0 - prodList
            // 1 - vh1View
            // 2 - vh2View
            ObservableList aux_prod = (ObservableList) this.polymorphic_list.get(0);
            this.prodList = FXCollections.observableArrayList(aux_prod);
            this.productsTable.setItems(null);
            this.productsTable.setItems(prodList);
        }
    }

    private void load_Vh1Data(Session session) {
        if (this.mode==0){
            vh1View = FXCollections.observableArrayList();
            Criteria criteria = session.createCriteria(Vehicle.class);
            criteria.add(Restrictions.eq("id_warehouse", this.id_warehouse));
            List rs = criteria.list();
            for (int i = 0; i < rs.size(); i++) {
                vh1View.add((Vehicle) rs.get(i));
            }
            this.vh1Table.setItems(null);
            this.vh1Table.setItems(vh1View);
        }else{
            // Previous list
            // 0 - prodList
            // 1 - vh1View
            // 2 - vh2View
            ObservableList aux_vh1 = (ObservableList) this.polymorphic_list.get(1);
            this.vh1View = FXCollections.observableArrayList(aux_vh1);
            this.vh1Table.setItems(null);
            this.vh1Table.setItems(vh1View);
        }
    }

    private void load_Vh2Data(Session session) {
        vh2View = FXCollections.observableArrayList();
        if (this.mode!=0){
            // Previous list
            // 0 - prodList
            // 1 - vh1View
            // 2 - vh2View
            ObservableList aux_vh2 = (ObservableList) this.polymorphic_list.get(2);
            this.vh2View = FXCollections.observableArrayList(aux_vh2);
            this.vh2Table.setItems(null);
            this.vh2Table.setItems(vh2View);
        }
    }

    private void setupVh1Table() {
        this.pc1Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlate()));
        this.cp1Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMax_weight().toString()));
    }

    private void setupVh2Table() {
        this.pc2Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlate()));
        this.cp2Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMax_weight().toString()));
    }
    

    
    public void addVh(){
        try{
            vh2View.add(vh1View.remove(selected_vh1));
            vh1Table.setItems(null);
            vh1Table.setItems(vh1View);
            vh2Table.setItems(null);
            vh2Table.setItems(vh2View);
            System.out.println("Passes through add");
        }catch (Exception e){
            System.out.println("xd");
        }
    }   
    
    public void delVh(){
        try{
            vh1View.add(vh2View.remove(selected_vh2));
            vh1Table.setItems(null);
            vh1Table.setItems(vh1View);
            vh2Table.setItems(null);
            vh2Table.setItems(vh2View);
            System.out.println("Passes through del");
        }catch (Exception e){
            System.out.println("xd");
        }
    }

    private boolean enoughStock(Session session) {
        class stockDict{
            public int id_prod;
            public int stock;
            public stockDict(int id_prod, int stock){
                super();
                this.id_prod = id_prod;
                this.stock = stock;
            }
        }
        ArrayList<stockDict> stocks = new ArrayList<>();
        
        Query query = session.createSQLQuery("SELECT id_product,p_stock FROM campis.productxwarehouse WHERE id_warehouse = "+this.id_warehouse);
        List<Object[]> rs = query.list();
        for (Object[] r : rs) {
            stocks.add(new stockDict( (int)r[0], (int)r[1]));
        }
        
        for (ProductWH_Move prod : this.prodList) {
            for (stockDict r : stocks) {
                if (r.id_prod == prod.getId_product()){
                    r.stock -= prod.getCant().get();
                    if (r.stock<0) return false;
                }
            }
        }
        
        return true;
    }

    private ArrayList<Batch> getBatches(Session session) {
        String conditional_ids = " ";
        int str_c = 0;
        for (ProductWH_Move prod : this.prodList) {
            if (str_c>0) conditional_ids = conditional_ids + " OR ";
            conditional_ids = conditional_ids + "b.id_product = " + prod.getId_product();
            str_c++;
        }
        System.out.println(conditional_ids);
        Query query = session.createSQLQuery("SELECT b.* FROM campis.batch b\n" +
                                                "INNER JOIN\n" +
                                                "campis.movement m on m.id_batch = b.id_batch\n" +
                                                "WHERE ("+conditional_ids+")AND m.mov_type < 2 AND b.type_batch = 3 AND m.id_warehouse="+this.id_warehouse+"\n"+
                                                "ORDER BY b.expiration_date");
        List<Object[]> rs = query.list();
        ArrayList<Batch> batches = new ArrayList<>();
        for (Object[] r : rs) {
            batches.add(new Batch((int)r[1],(float)(double)r[2],(Timestamp)r[3],(Timestamp)r[4],(int)r[5],3,"--",true));
        }
        //  already sorted by query
        
        ArrayList<Batch> returnable = new ArrayList<>();
        
        for (ProductWH_Move productWH_Move : prodList) {
            int qt = productWH_Move.getCant().get();
            for (int i = batches.size()-1; i >= 0; i--) {
                if (productWH_Move.getId_product() == batches.get(i).getId_product()){
                    if (qt<batches.get(i).getQuantity()){
                        // split batch in two
                        Batch batch = new Batch(batches.get(i));
                        batch.setId_batch(-1);
                        batch.setHeritage("["+batches.get(i).getId_batch()+"]");
                        
                    }else{
                        qt-=batches.get(i).getQuantity();
                        returnable.add(batches.remove(i));
                    }
                }
            }
        }
        
        
        return null;
    }
}

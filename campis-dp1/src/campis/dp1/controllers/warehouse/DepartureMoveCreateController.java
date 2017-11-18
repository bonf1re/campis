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
import campis.dp1.models.CNode;
import campis.dp1.models.CRack;
import campis.dp1.models.Coord;
import campis.dp1.models.Coordinates;
import campis.dp1.models.ProductWH_Move;
import campis.dp1.models.TabuProblem;
import campis.dp1.models.TabuSolution;
import campis.dp1.models.Vehicle;
import campis.dp1.models.Warehouse;
import campis.dp1.models.WarehouseZone;
import campis.dp1.models.routing.Grasp;
import campis.dp1.models.routing.GraspResults;
import campis.dp1.models.routing.RouteGen;
import campis.dp1.models.utils.GraphicsUtils;
import campis.dp1.models.utils.RoutingUtils;
import campis.dp1.services.TabuSearchService;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
    private ArrayList<Batch> original_batches;
    private ArrayList<Batch> created_batches;
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
    @FXML
    private JFXComboBox<String>  cbWh;
    private ArrayList<Warehouse> warehouses = new ArrayList<>();
    
    
    
    
    
    
    public void goDepartureMoveRoute() throws IOException{
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
        getBatches(session);
        
        System.out.println("After lambdas:");
        for (Batch created_batche : this.created_batches) {
            created_batche.printBatch();
        }
        
        System.out.println("After lambdas:");
        for (Batch created_batche : this.original_batches) {
            created_batche.printBatch();
        }
        
        
        ArrayList<WarehouseZone> zone_sel = getZones(this.created_batches,session);
        if (zone_sel.size()<created_batches.size()){
            System.out.println("Mala generacion de zonas.");
            System.out.println("Se cancelara el movimiento.");
            return;
        }
        
        // Dynamic route generation per vehicle
        sortPerWeight(this.created_batches,zone_sel,session);
        
        sendBatches_n_Routes(this.created_batches,this.original_batches,zone_sel,session);
        
        ContextFX.getInstance().setId(this.id_warehouse);
        session.close();
        sessionFactory.close();
        
        main.showWhDepartureMoveRoute();
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
        
        // DB connection
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        
        // ComboBoxes
        setupComboBoxes(session);
        
        // Listeners
        vh1Table_listener();
        vh2Table_listener();
        cbMotive_Listener();
        
        
        // Table Setups
        setupProductsTable();
        setupVh1Table();
        setupVh2Table();
        
        load_ProductsData(session);
        load_Vh1Data(session);
        load_Vh2Data(session);
        
        
        session.close();
        sessionFactory.close();
    }

    private void setupProductsTable() {
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

    private void getBatches(Session session) {
        String conditional_ids = " ";
        int str_c = 0;
        for (ProductWH_Move prod : this.prodList) {
            if (str_c>0) conditional_ids = conditional_ids + " OR ";
            conditional_ids = conditional_ids + "b.id_product = " + prod.getId_product();
            str_c++;
        }
        System.out.println(conditional_ids);
        Query query = session.createSQLQuery("SELECT DISTINCT b.* FROM campis.batch b\n" +
                                                "INNER JOIN\n" +
                                                "campis.movement m on m.id_batch = b.id_batch\n" +
                                                "WHERE  b.state=true AND ("+conditional_ids+")AND m.mov_type < 2 AND b.type_batch = 3 AND m.id_warehouse="+this.id_warehouse+"\n"+
                                                "ORDER BY b.expiration_date");
        List<Object[]> rs = query.list();
        this.original_batches = new ArrayList<>();
        for (Object[] r : rs) {
            Batch batch = new Batch((int)r[1],(float)(double)r[2],(Timestamp)r[3],(Timestamp)r[4],(int)r[5],3,"--",true);
            batch.setId_batch((int)r[0]);
            original_batches.add(batch);
            
        }
        System.out.println("Query results size: ");
        System.out.println(original_batches.size());
        //  already sorted by query
        
        this.created_batches = new ArrayList<>();
        
        // will tag them with state 
        for (ProductWH_Move productWH_Move : prodList) {
            int qt = productWH_Move.getCant().get();
            for (int i = original_batches.size()-1; i >= 0; i--) {
                if (qt==0) break;
                if (productWH_Move.getId_product() == original_batches.get(i).getId_product()){
                    if (original_batches.get(i).getQuantity()>0 && qt<original_batches.get(i).getQuantity()){
                        // split batch in two and set to true its state
                        Batch batch = new Batch(original_batches.get(i));
                        batch.setId_batch(-1);
                        batch.setHeritage("["+original_batches.get(i).getId_batch()+"]");
                        batch.setQuantity(qt);
                        batch.setState(true);
                        created_batches.add(batch);
                        // set batch to false and diminish its quantity
                        Batch org_batch = original_batches.get(i);
                        org_batch.setQuantity(org_batch.getQuantity()-qt);
                        org_batch.setState(false);
                        original_batches.set(i, org_batch);
                    }else{
                        qt-=original_batches.get(i).getQuantity();
                        Batch org_batch = original_batches.get(i);
                        org_batch.setState(true);
                        created_batches.add(org_batch);
                        original_batches.remove(i);
                    }
                }
            }
        }
        
        System.out.println("Before lambdas:");
        for (Batch created_batche : this.created_batches) {
            created_batche.printBatch();
        }
        
        System.out.println("Before lambdas:");
        for (Batch created_batche : this.original_batches) {
            created_batche.printBatch();
        }
        
        System.out.println("Batch partitioning results size:");
        System.out.println("-Results size:");
        System.out.println(created_batches.size());
        System.out.println("-Partitioned batches size:");
        System.out.println(original_batches.stream().filter(b -> b.isState()==false).collect(Collectors.toList()).size());
        System.out.println("-Newly created batches:");
        System.out.println(created_batches.stream().filter(b -> b.getId_batch()==-1).collect(Collectors.toList()).size());
    }
    
    public void vh1Table_listener() {
        this.vh1Table.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_vh1 = this.vh1Table.getSelectionModel().getSelectedIndex();
            }
        );
    }

    public void vh2Table_listener() {
        this.vh2Table.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_vh2 = this.vh2Table.getSelectionModel().getSelectedIndex();
            }
        );
    }

    private ArrayList<WarehouseZone> getZones(ArrayList<Batch> batch_list, Session session) {
        ArrayList<WarehouseZone> returnable = new ArrayList<>();
        System.out.println("\n\nZones");
        for (int i = 0; i < batch_list.size(); i++) {
            Batch batch = batch_list.get(i);
            int parsed_id_batch;
            batch.printBatch();
            if (batch.getId_batch()>0){
                parsed_id_batch = batch.getId_batch();
            }else{
                parsed_id_batch=singleHer(batch);
            }
            Query query = session.createSQLQuery("SELECT z.* FROM campis.zone z\n" +
                                                    "INNER JOIN\n" +
                                                    "campis.movement m on m.id_zone = z.id_zone\n" +
                                                    "WHERE m.id_batch ="+parsed_id_batch+"\n" +
                                                    "ORDER BY mov_date");
            List<Object[]> rs = query.list();
            if (rs.size()<=0) continue;
            Object[] raw_zone = rs.get(0);
            WarehouseZone zzz =new WarehouseZone((int)raw_zone[1], (int)raw_zone[2], (int)raw_zone[3], (int)raw_zone[4], (int)raw_zone[5], true);
            zzz.setId_zone((int)raw_zone[0]);
            returnable.add(zzz);
        }
        
        
        
        return returnable;
    }
    
    private void sortPerWeight(ArrayList<Batch> batch_list, ArrayList<WarehouseZone> zone_list, Session session) {
       for (int i = 0; i < batch_list.size(); i++) {
           Batch batch_i = batch_list.get(i);
           Query query_i = session.createSQLQuery("SELECT weight FROM campis.product WHERE id_product = "+batch_i.getId_product());
           double weight_i = batch_i.getQuantity()*(Double)query_i.list().get(0);
            for (int j = 0; j < batch_list.size(); j++) {
                Batch batch_j = batch_list.get(j);
                Query query_j = session.createSQLQuery("SELECT weight FROM campis.product WHERE id_product = "+batch_j.getId_product());
                double weight_j = batch_j.getQuantity()*(Double)query_j.list().get(0);
                if (weight_i > weight_j && j<i){
                    // For Zone
                    WarehouseZone swap_z = new WarehouseZone(zone_list.get(i),0);
                    zone_list.set(i, zone_list.get(j));
                    zone_list.set(j, swap_z);
                    
                    // For Batch
                    Batch swap_b = new Batch(batch_list.get(i),0);
                    batch_list.set(i, batch_list.get(j));
                    batch_list.set(j, swap_b);
                }
            }
        }
    }
    
     private void sortPerCapacity(ObservableList<Vehicle> vh_list) {
        for (int i = 0; i < vh_list.size(); i++) {
            for (int j = 0; j < vh_list.size(); j++) {
                if (vh_list.get(i).getMax_weight() < vh_list.get(j).getMax_weight() && j<i){
                    // For Vehicle
                    Vehicle swap_v = new Vehicle(vh_list.get(i),0);
                    vh_list.set(i,vh_list.get(j));
                    vh_list.set(j, swap_v);
                }
            }
        }
    }
    
    
    private int singleHer(Batch batch) {
        String her = batch.getHeritage();
        System.out.println(her);
        StringBuilder str = new StringBuilder(her);
        str.deleteCharAt(her.indexOf('['));
        str.deleteCharAt(her.indexOf(']')-1);
        return Integer.parseInt(str.toString());
    }

    private void sendBatches_n_Routes(ArrayList<Batch> batch_list, ArrayList<Batch> original_batches, ArrayList<WarehouseZone> zone_list, Session session) {
        routingSetup(session);
        ArrayList<Object> sendable = new ArrayList<>();
        sendable.add(3); // index + 2
        sendable.add(new ArrayList<>(this.original_batches)); // 1
        int[] motive_arr = new int[2];
        motive_arr[0] = cbMotive.getSelectionModel().getSelectedIndex()+3;
        motive_arr[1] = -1;
        if (motive_arr[0]==4) motive_arr[1] = warehouses.get(cbWh.getSelectionModel().getSelectedIndex()).getId();
        sendable.add(motive_arr);
        // the idea is to send list of zones, list of batches, vehicle and route per row
        ObservableList<Vehicle> vh_list = FXCollections.observableArrayList(this.vh2View);
        sortPerCapacity(vh_list);            
        for (int i = 0; i < vh_list.size(); i++) {
            Vehicle vh = vh_list.get(i);
            double max_cp = vh.getMax_weight();
            ArrayList<WarehouseZone> r_zones = new ArrayList<>();
            ArrayList<Batch> r_batches = new ArrayList<>();
            int counter = 0;
            for (int j=batch_list.size()-1;j>=0;j--) {
                Query query = session.createSQLQuery("SELECT weight FROM campis.product WHERE id_product = "+String.valueOf(batch_list.get(j).getId_product()));
                double total_batch_weight = batch_list.get(j).getQuantity()*(double)(query.list().get(0));
                max_cp=max_cp-total_batch_weight;
                if (max_cp<=0){
                    // here it ends
                    break;
                }
                r_zones.add(zone_list.remove(j));
                r_batches.add(batch_list.remove(j));
            }
            ArrayList<Object> aux = new ArrayList<Object>();
            // 0 - batches
            // 1 - zones
            // 2 - vehicle
            // 3 - route
            if (r_batches.size()==0) continue;
            aux.add(r_batches);
            aux.add(r_zones);
            aux.add(vh);
            System.out.println(r_zones.toString());
            ArrayList<Coord> route =generateRoute(r_zones,session);
            aux.add(new ArrayList<Coord>(route));
            aux.add(false); // to know whether this move has been saved or not
            sendable.add(aux);
            // Corregir otros loops con removal al reves csm
        }
        ContextFX.getInstance().setPolymorphic_list(sendable);
    }
    
    
    
    private void routingSetup(Session session){
        //ArrayList<Coord> batchesCoords = readPositions(r_zones);
        //System.out.println(batchesCoords);
        Criteria criteria = session.createCriteria(Warehouse.class);
        criteria.add(Restrictions.eq("id_warehouse", this.id_warehouse));
        List rs = criteria.list();
        this.wh = (Warehouse) rs.get(0);
        GraphicsUtils gu = new GraphicsUtils();
        this.y=this.wh.getWidth();
        this.x=this.wh.getLength();
        this.real_map=gu.initMap(this.y,this.x);
        this.crackList=gu.putCRacks(this.id_warehouse, this.real_map);
    }
    
    
    private ArrayList<Coord> generateRoute(ArrayList<WarehouseZone> r_zones, Session session){
        RoutingUtils utils=new RoutingUtils();
        ArrayList<Coord> batchesCoords = readPositions(r_zones);
        System.out.println(batchesCoords);
        setupCRacksGraph();
        Grasp graspSolution = new Grasp(this.real_map,this.routesGraph, batchesCoords);
        GraspResults gResults = graspSolution.execute();
        ArrayList<Coordinates> tabuInput = utils.toCoordinates(gResults.getProducts()); // orden de productos
        RouteGen routeGen = graspSolution.getRouteGen();
        routeGen.printDict();
        utils.printCoords(gResults.getRoute());
        utils.printCoordinates(tabuInput);
        TabuSearchService tabu = new TabuSearchService();
        TabuProblem tabuProblem = new TabuProblem(routeGen);
        TabuSolution tabuSolution = tabu.search(tabuProblem, tabuInput);
        
        return new ArrayList<Coord>(utils.getRoute(tabuSolution.getOrder(),routeGen));
        
    }

    private ArrayList<Coord> readPositions(ArrayList<WarehouseZone> r_zones) {
        ArrayList<Coord> returnable =  new ArrayList<>();
        for (WarehouseZone r_zone : r_zones) {
            returnable.add(new Coord(r_zone.getPos_y(),r_zone.getPos_x()));
        }
        
        return returnable;
    }
    
     private void setupCRacksGraph() {
         if (this.routesGraph.getSize()>0) return;
        for (CRack rack : this.crackList) {
            for (int i = 0; i < 4; i++) {
                Coord corner = rack.getCorner(i);
                this.routesGraph.addNode(new CNode(corner));
            }
        }
        if (this.routesGraph.getSize()>0){
            this.routesGraph.addNode(new CNode(new Coord(0,0)));
        }
        this.routesGraph.setup(this.real_map);
        
    }

    private void cbMotive_Listener() {
        this.cbMotive.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (this.cbMotive.getSelectionModel().getSelectedIndex()==1){
                this.cbWh.setDisable(false);
            }else{
                this.cbWh.setDisable(true);
            }
            
        }
        );
    }

    private void setupComboBoxes(Session session) {
        cbMotive.getItems().addAll("Despacho","Transferencia","Perdida","Roto");
        Criteria criteria = session.createCriteria(Warehouse.class);
        List rs = criteria.list();
        ObservableList<String> wh_names = FXCollections.observableArrayList();
        for (Object r : rs) {
            Warehouse whhh = (Warehouse)r;
            if (whhh.getId() != this.id_warehouse){
                warehouses.add(whhh);
                wh_names.add(whhh.getName());
            }
        }        
        cbWh.getItems().addAll(wh_names);
        this.cbWh.setDisable(true);
    }
}

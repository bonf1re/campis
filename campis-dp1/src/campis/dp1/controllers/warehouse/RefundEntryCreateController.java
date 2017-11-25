/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Area;
import campis.dp1.models.Batch;
import campis.dp1.models.BatchWH_Move;
import campis.dp1.models.CGraph;
import campis.dp1.models.CNode;
import campis.dp1.models.CRack;
import campis.dp1.models.Coord;
import campis.dp1.models.Coordinates;
import campis.dp1.models.ProductWH_Move;
import campis.dp1.models.Supplier;
import campis.dp1.models.TabuProblem;
import campis.dp1.models.TabuSolution;
import campis.dp1.models.Vehicle;
import campis.dp1.models.Warehouse;
import campis.dp1.models.WarehouseZone;
import campis.dp1.models.routing.Grasp;
import campis.dp1.models.routing.GraspResults;
import campis.dp1.models.routing.RouteGen;
import campis.dp1.models.utils.GraphicsUtils;
import campis.dp1.models.utils.ListUtils;
import campis.dp1.models.utils.RoutingUtils;
import campis.dp1.services.TabuSearchService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

/**
 *
 * @author sergio
 */
public class RefundEntryCreateController implements Initializable {
    private Main main;
    private int id_warehouse;
    //private ObservableList<ProductWH_Move> prodList = FXCollections.observableArrayList();
        
    /*Vehicles*/
    private int selected_vh1;
    private int selected_vh2;
    ObservableList<Vehicle> vh1View;
    ObservableList<Vehicle> vh2View;
    /*End vehicles*/
    
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
    private TableView<ProductWH_Move> tableProd;

    @FXML
    private TableColumn<ProductWH_Move, Integer> id_prod;
     
    @FXML
    private TableColumn<ProductWH_Move, String> prodCol;

    @FXML
    private TableColumn<ProductWH_Move, Integer> cantLote;
    
    @FXML
    private TableColumn<ProductWH_Move, Integer> cant_x_lote;
     
    
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
    private int id_refund;
    
    


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id_warehouse = ContextFX.getInstance().getId();
        this.polymorphic_list = ContextFX.getInstance().getPolymorphic_list();
        
        // Listeners
        vh1Table_listener();
        vh2Table_listener();
        //cbMotive_Listener();
        
        // Table Setups
        setupProductsTable();
        vh1Table_Setup();
        vh2Table_Setup();
               
        try{
            // Load data
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            //batch_LoadData(session);
            vh1_LoadData(session);
            vh2_LoadData(session);
            load_ProductsData(session);
            session.close();
            sessionFactory.close();
        } catch (Exception ex) {
            Logger.getLogger(WarehouseListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void load_ProductsData(Session session) {
        this.id_refund = (int) this.polymorphic_list.remove(0);
        ArrayList<ProductWH_Move> aux_prod = new ArrayList<ProductWH_Move>((Collection<? extends ProductWH_Move>) this.polymorphic_list.get(0));
        this.prodList = FXCollections.observableArrayList(aux_prod);
        this.tableProd.setItems(null);
        this.tableProd.setItems(prodList);

    }
    
    private void setupProductsTable() {
         try{
            id_prod.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_product()).asObject());
            prodCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            cantLote.setCellValueFactory(cellData -> cellData.getValue().getQtLt().asObject());
            cant_x_lote.setCellValueFactory(cellData -> cellData.getValue().getNum().asObject());

        } catch (Exception ex) {
                Logger.getLogger(WarehouseListController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    private void vh1Table_Setup() {
        this.pc1Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlate()));
        this.cp1Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMax_weight().toString()));
    }

    private void vh2Table_Setup() {
        this.pc2Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlate()));
        this.cp2Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMax_weight().toString()));
    }
    
    private void vh1_LoadData(Session session) {
        if (this.mode==0){
            vh1View = FXCollections.observableArrayList();
            Criteria criteria = session.createCriteria(Vehicle.class);
            criteria.add(Restrictions.eq("id_warehouse", this.id_warehouse));
            criteria.add(Restrictions.eq("active", true));
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

    private void vh2_LoadData(Session session) {
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
    
    private double weight_check() {
        double total_weight=0;
        if (vh2View.size()==0) return -1;
        for (int i = 0; i < vh2View.size(); i++) {
            total_weight+=vh2View.get(i).getMax_weight();
        }
        
        int batches_counter=0;
        for (ProductWH_Move p : tableProd.getItems()) {
            
            System.out.println(p.getWeight());
            System.out.println("campis.dp1.controllers.warehouse.EntryMoveSpecialCreateController.weight_check()");
            System.out.println(p.getWeight());
            System.out.println(p.getCant());
            System.out.println(p.getQtLt());
            total_weight-=p.getWeight()*p.getCant().get()*p.getQtLt().get();
            batches_counter++;
        }
        if (batches_counter==0) return -1;
        return total_weight;
    }
    
    public void goWhEntryMoveRoute() throws IOException{
        // Weight check
        double total_weight = weight_check();
        if (total_weight<0){
            System.out.println("No hay suficientes carritos para el piso a transportar o no se ha seleccionado algo.");
            return;
        }
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        List aux = getMarked(session);
        List zone_sel = getZones((ArrayList<Batch>)aux,session);
        
        if (zone_sel.size() != aux.size()){
            System.out.println("No se pudo colocar todos los lotes, abortando.");
            return;
        }

        ContextFX.getInstance().setId(id_warehouse);
        
        // Dynamic route generation per vehicle
        sortPerWeight((ArrayList<Batch>)aux,(ArrayList<WarehouseZone>)zone_sel,session);
        
        sendBatches_n_Routes((ArrayList<Batch>)aux,(ArrayList<WarehouseZone>)zone_sel,session);
        session.close();
        sessionFactory.close();
        
        ContextFX.getInstance().setId_Refund(id_refund);
        main.showWhSpecialEntryMoveRoute();    
    }
    
    private ArrayList<WarehouseZone> getZones(ArrayList<Batch> batch_route_list, Session session) {
        // insert where free
        // then will add filter per area
        ArrayList<WarehouseZone> returnable =  new ArrayList<>();
        // For the moment, we will use pitagoric distance to select the top areas
        // sorted insert
        
        Criteria criteria = session.createCriteria(WarehouseZone.class);
        criteria.add(Restrictions.eq("free",true));
        List zones = criteria.list();
        
        criteria = session.createCriteria(Area.class);
        criteria.add(Restrictions.eq("id_warehouse", this.id_warehouse));
        ArrayList<Area> areas = new ArrayList<>((List)criteria.list());
        
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
        
        ListUtils listUtils = new ListUtils();
        listUtils.reverseList(returnable);
        System.out.println(returnable.size());
        ArrayList<WarehouseZone>  true_returnable = new ArrayList<>();
        for (int i = batch_route_list.size()-1;i>=0;i--) {
            Batch batch = batch_route_list.get(i);
            Query query = session.createSQLQuery("SELECT id_product_type FROM campis.product WHERE id_product = "+String.valueOf(batch.getId_product()));
            int product_type_id = (int) query.list().get(0);
            Area curr_area = null;
            for (Area area : areas) {
                if (area.getProduct_type()==product_type_id){
                    curr_area=area;
                    break;
                }
            }
            if (curr_area == null){
                try{
                true_returnable.add(returnable.remove(free_zone(returnable,areas)));
                }catch(Exception e){
                    System.out.println("No free zone found.");
                }
                continue;
            }
            boolean zoned = false;
            for (int k =0; k<returnable.size();k++) {
                WarehouseZone zone = returnable.get(k);
                if (inArea(zone,curr_area)){
                    true_returnable.add(returnable.remove(k));
                    zoned=true;
                    break;
                }
            }
            if (zoned==false){
                try{
                true_returnable.add(returnable.remove(free_zone(returnable,areas)));
                }catch(Exception e){
                    System.out.println("No free zone found.");
                }
                continue;
            }
        }

        return new ArrayList<WarehouseZone>(true_returnable);

    }
    
    private int free_zone(ArrayList<WarehouseZone> returnable, ArrayList<Area> areas) {
        ArrayList<WarehouseZone> cp_zones = new ArrayList<>(returnable);
        int counter=0;
        for (Area area : areas) {
            for (int i=cp_zones.size()-1;i>=0;i--) {
                WarehouseZone cp_zone = cp_zones.get(i);
                if (inArea(cp_zone,area)){
                    cp_zones.remove(cp_zone);
                    counter++;
                }
            }
        }
        if (cp_zones.size()>0){
            return counter;
        }else{
            return -1;
        }
    }
    
    private boolean inArea(WarehouseZone cp_zone, Area area) {
        int range_y_init=area.getPos_y();
        int range_y_end=area.getPos_y()+area.getWidth()-1;
        int range_x_init=area.getPos_x();
        int range_x_end=area.getPos_x()+area.getLength()-1;
        return (range_y_init<=cp_zone.getPos_y() && cp_zone.getPos_y()<=range_y_end &&
                        range_x_init<=cp_zone.getPos_x() && cp_zone.getPos_x()<=range_x_end);
    }

    
    private void sendBatches_n_Routes(ArrayList<Batch> batch_list,ArrayList<WarehouseZone> zone_list,Session session) {
        routingSetup(session);
        ArrayList<Object> sendable = new ArrayList<Object>();
        sendable.add(1); // index
        // the idea is to send list of zones, list of batches, vehicle and route per row
        ObservableList<Vehicle> vh_list = FXCollections.observableArrayList(this.vh2View);
        sortPerCapacity(vh_list);
        
         class weightDict{
            public int id_prod;
            public double weight;
            public weightDict(int id_prod, double weight){
                super();
                this.id_prod = id_prod;
                this.weight = weight;
            }
        }
         
        ArrayList<weightDict> weights = new ArrayList<>();
        
        for (int i = 0; i < vh_list.size(); i++) {
            Vehicle vh = vh_list.get(i);
            double max_cp = vh.getMax_weight();
            ArrayList<WarehouseZone> r_zones = new ArrayList<>();
            ArrayList<Batch> r_batches = new ArrayList<>();
            int counter = 0;
            for (int j=batch_list.size()-1;j>=0;j--) {
                double total_batch_weight = -1;
                for (weightDict weight : weights) {
                    if (weight.id_prod==batch_list.get(j).getId_product()){
                        total_batch_weight=weight.weight;
                        break;
                    }
                }
                if (total_batch_weight<0){
                    Query query = session.createSQLQuery("SELECT weight FROM campis.product WHERE id_product = "+String.valueOf(batch_list.get(j).getId_product()));
                    total_batch_weight = batch_list.get(j).getQuantity()*((BigDecimal)(query.list().get(0))).floatValue();
                    weightDict wd = new weightDict(batch_list.get(j).getId_product(), total_batch_weight);
                    weights.add(wd);
                }
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
            //ArrayList<Coord> route =generateRoute(r_zones,session);
            ArrayList<Coord> route = new ArrayList<>();
            aux.add(new ArrayList<Coord>(route));
            aux.add(false); // to know whether this move has been saved or not
            sendable.add(aux);
            // Corregir otros loops con removal al reves csm
        }
        ContextFX.getInstance().setPolymorphic_list(sendable);
    }
    
     private ArrayList<Batch> getMarked(Session session){
         
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        // Here will divide batches if larger than max_qt of each product
        ArrayList<Batch> returnable = new ArrayList<>();
        // TODOProductWH_Move
        for (int i=0; i<tableProd.getItems().size();i++) {
            ProductWH_Move item = tableProd.getItems().get(i);
            //boolean selected = item.getSelected().getValue();
           
            /// Check if batch is larger than should be
            Query query = session.createSQLQuery("SELECT max_qt FROM campis.product WHERE id_product = "+String.valueOf(item.getId_product()));
            int max_qt = (int) query.list().get(0);
            System.out.println("La cantidad del lote es: "+item.getNum().get());
            System.out.println("La cantidad maxima permitida para el producto es: "+max_qt);
                        
            int b_num= item.getNum().get();
            int id_supplier = 3;
            
            /*
             id_supplier = 0 HALLAZGO
             id_supplier != 0 id_provedor
            */

            if (b_num < max_qt){
                for (int j = 0; j < item.getQtLt().get(); j++) {
                    Batch new_batch = new Batch();

                    new_batch.setId_product(item.getId_product());
                    new_batch.setId_batch(-1);
                    new_batch.setQuantity(b_num);
                    new_batch.setArrival_date(currentTimestamp);
                    new_batch.setExpiration_date(item.getExp_date());
                    new_batch.setId_supplier(id_supplier);
                    
                    returnable.add(new_batch);
                }                 
            } else {
                for (int j = 0; j < item.getQtLt().get(); j++) {
                    // divide batches in max_qt and one with not so much
                    int number_batches = (int) Math.ceil((double)item.getNum().get()/(double)max_qt);
                    System.out.println(number_batches);
                    for (int k = 1; k < number_batches; k++) {
                        Batch new_batch = new Batch();
                        
                        new_batch.setId_product(item.getId_product());
                        new_batch.setId_batch(-1);
                        new_batch.setQuantity(max_qt);
                        new_batch.setArrival_date(currentTimestamp);
                        new_batch.setExpiration_date(item.getExp_date());
                        new_batch.setId_supplier(id_supplier);
                    
                        returnable.add(new_batch);
                    }
                    
                    Batch new_batch = new Batch();
                    
                    new_batch.setId_product(item.getId_product());
                    new_batch.setId_batch(-1);
                    //new_batch.setQuantity(b_num);
                    new_batch.setQuantity(item.getNum().get() - max_qt*(number_batches-1));
                    new_batch.setArrival_date(currentTimestamp);
                    new_batch.setExpiration_date(item.getExp_date());
                    new_batch.setId_supplier(id_supplier);
                    
                    returnable.add(new_batch);
                }    
                System.out.println("Lote no agregado");
            }             
        }
        
        return returnable;
    }
     
   
    
     private void sortPerWeight(ArrayList<Batch> batch_list, ArrayList<WarehouseZone> zone_list, Session session) {
       for (int i = 0; i < batch_list.size(); i++) {
           Batch batch_i = batch_list.get(i);
           Query query_i = session.createSQLQuery("SELECT weight FROM campis.product WHERE id_product = "+batch_i.getId_product());
           double weight_i = batch_i.getQuantity()*((BigDecimal)query_i.list().get(0)).floatValue();
            for (int j = 0; j < batch_list.size(); j++) {
                Batch batch_j = batch_list.get(j);
                Query query_j = session.createSQLQuery("SELECT weight FROM campis.product WHERE id_product = "+batch_j.getId_product());
                double weight_j = batch_j.getQuantity()*((BigDecimal)query_j.list().get(0)).floatValue();
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
    
    
    private int singleHer(Batch batch) {
        String her = batch.getHeritage();
        System.out.println(her);
        StringBuilder str = new StringBuilder(her);
        str.deleteCharAt(her.indexOf('['));
        str.deleteCharAt(her.indexOf(']')-1);
        return Integer.parseInt(str.toString());
    }

    
   
    
    public void goWhEntryMoveList() throws IOException{
        ContextFX.getInstance().setId(id_warehouse);
        main.showWhEntryMoveList();
    }
    
    
//
//    private void loadData() {        
//        try{
//            this.prodList = (ObservableList<ProductWH_Move>) FXCollections.observableArrayList((ArrayList<ProductWH_Move>)ContextFX.getInstance().getWhMovesProdList());
//        }catch(Exception e){
//            System.out.println("Caught in first exception");
//        }
//        
//        try{
//            Configuration configuration = new Configuration();
//            configuration.configure("hibernate.cfg.xml");
//            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
//            SessionFactory sessionFactory = configuration.buildSessionFactory();
//            Session session = sessionFactory.openSession();
//            session.beginTransaction();
//            Criteria criteria = session.createCriteria(Product.class);
//            criteria.add(Restrictions.eq("id_product",ContextFX.getInstance().getNum()));            
//            List rsType = criteria.list();
//            Product result = (Product) rsType.get(0);
//            this.prodList.add(new ProductWH_Move(result,ContextFX.getInstance().getQuantity()));
//            session.close();
//            sessionFactory.close();
//        }catch(Exception e){
//            System.out.println("Caught in second exception ffs");
//            e.printStackTrace();
//        }
//        
//        batchTable.setItems(null);
//        batchTable.setItems(this.prodList);
//    }

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

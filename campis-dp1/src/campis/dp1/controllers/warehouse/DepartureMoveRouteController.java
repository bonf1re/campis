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
import campis.dp1.models.CGraph;
import campis.dp1.models.CNode;
import campis.dp1.models.CRack;
import campis.dp1.models.Coord;
import campis.dp1.models.Coordinates;
import campis.dp1.models.DispatchOrder;
import campis.dp1.models.DispatchOrderLine;
import campis.dp1.models.Product;
import campis.dp1.models.Rack;
import campis.dp1.models.TabuProblem;
import campis.dp1.models.TabuSolution;
import campis.dp1.models.Vehicle;
import campis.dp1.models.utils.GraphicsUtils;
import campis.dp1.models.Warehouse;
import campis.dp1.models.WarehouseMove;
import campis.dp1.models.WarehouseZone;
import campis.dp1.models.routing.Grasp;
import campis.dp1.models.routing.GraspResults;
import campis.dp1.models.routing.RouteGen;
import campis.dp1.models.utils.RoutingUtils;
import campis.dp1.services.TabuSearchService;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import oracle.jrockit.jfr.tools.ConCatRepository;
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
public class DepartureMoveRouteController implements Initializable{
    private Main main;
    private int id_warehouse;
    private Warehouse wh;
    private ArrayList<Object> routing_data = new ArrayList<>();
    private ArrayList<Integer> id_batchesList = new ArrayList<>();
//    private ArrayList<Integer> numList = new ArrayList<>();
    private ArrayList<WarehouseZone> zoneList = new ArrayList<>();
    private ObservableList<BatchWH_Move> batchesList;
    private ArrayList<CRack> crackList = new ArrayList<>();
    private CGraph routesGraph = new CGraph();
    ArrayList<String> product_names = new ArrayList<>();
    ArrayList<Batch> save_batches = new ArrayList<>();
    private int mode=0;
    //private ArrayList<Coord> batchesCoords = new ArrayList<>();
    //private ArrayList<Coord> routeGenerated;
    
    /* Drawing variables section */
    @FXML
    private Canvas mapCanvas;
    private GraphicsContext gc;
    private int[][] real_map;
    private int x;
    private int y;    
    /* End Drawing variables section */
    
    @FXML
    private TableView<BatchWH_Move> batchTable;
   
    @FXML
    private TableColumn<BatchWH_Move, Integer> qtCol;
    @FXML
    private TableColumn<BatchWH_Move, String> prodCol;
    @FXML
    private TableColumn<BatchWH_Move, String> zoneCol;
    @FXML
    private Text pCounterField;
    @FXML
    private Text vhText;
    
    @FXML
    public void VisualizeMyRoute(){
        GraphicsUtils gu = new GraphicsUtils();
        this.y=this.wh.getWidth();
        this.x=this.wh.getLength();
        this.real_map=gu.initMap(this.y,this.x);
        this.crackList=gu.putCRacks(id_warehouse, real_map);
        setupCRacksGraph();
        gc = mapCanvas.getGraphicsContext2D();
        int index= (int) this.routing_data.get(0);
        ArrayList<Object> routing_sub_data = (ArrayList<Object>) this.routing_data.get(index);
        //ArrayList<Coord> route = (ArrayList<Coord>) routing_sub_data.get(3);
        ArrayList<Coord> route = generateRoute((ArrayList<WarehouseZone>)routing_sub_data.get(1));
        gu.drawVisualizationMap(gc, y, x,this.real_map, route, this.batchesList);
    }
    
    private ArrayList<Coord> generateRoute(ArrayList<WarehouseZone> r_zones){
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

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.id_warehouse=ContextFX.getInstance().getId();
        this.routing_data=ContextFX.getInstance().getPolymorphic_list();
        Vehicle vh =(Vehicle) ((ArrayList<Object>) this.routing_data.get((int)this.routing_data.get(0))).get(2); //wtf
        this.vhText.setText("Vehicle: "+ vh.getPlate() + " \nde capacidad : "+vh.getMax_weight());
        this.pCounterField.setText("<"+String.valueOf((int)this.routing_data.get(0)-2)+"/"+String.valueOf(this.routing_data.size()-3)+">");
        try{
            qtCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());            
            prodCol.setCellValueFactory(cellData -> new SimpleStringProperty(this.product_names.get(this.batchesList.indexOf(cellData.getValue()))));
            zoneCol.setCellValueFactory(cellData -> cellData.getValue().getZoneStr());
            
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            
            
            loadData(session);
            drawMap(session);
            session.close();
            sessionFactory.close();
            //generateRoute();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(WarehouseListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void saveDepartureMove() throws IOException{
        // Will save all
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        // Previous verification
        int[] motive_arr = (int[]) this.routing_data.get(2);
        int type_batch=1;
        int warehouse_save_id = this.id_warehouse;
        int mov_type=3; // till cbMotive
        
        if (motive_arr[0]==3){ // To dispatch
            mov_type=3;
            type_batch=1;
            saveDispatchOrder(session);
        }else if (motive_arr[0]==4){ // Transfer to another warehouse
            mov_type=2;
            type_batch=7;
            warehouse_save_id = motive_arr[1]; // New warehouse product
        }else if (motive_arr[0]==5){ // Broken
            mov_type=5;
            type_batch=-1;
        }else if (motive_arr[0]==6){
            mov_type=6;
            type_batch=-1;
        }
        
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        
        ArrayList<Batch> original_batches = (ArrayList<Batch>)this.routing_data.get(1);
        for (Batch original_b : original_batches) {
            if (original_b.isState() == false){ // fue partido, necesita actualizarse
                Query b_query = session.createSQLQuery("UPDATE campis.batch \n"+
                                                       "SET quantity = "+original_b.getQuantity()+"\n"+
                                                       " WHERE id_batch = "+original_b.getId_batch());
                b_query.executeUpdate();
            }
        }
        
        
        
        
        for (int i = 3; i < this.routing_data.size(); i++) {
            ArrayList<Object> r_d_iterator = (ArrayList<Object>) this.routing_data.get(i);
            boolean saved = (boolean) r_d_iterator.get(4);
            if (saved == true ){
                System.out.println("Este movimiento ya ha sido grabado.");
                continue;
            }
            
            ArrayList<Batch> batch_list =(ArrayList<Batch>) r_d_iterator.get(0);
            ArrayList<WarehouseZone> zone_list = (ArrayList<WarehouseZone>) r_d_iterator.get(1);
            Vehicle vh = (Vehicle) r_d_iterator.get(2);
            //ArrayList<Coord> route= (ArrayList<Coord>) r_d_iterator.get(3);
            
            for (int j = 0; j < batch_list.size(); j++) {  
                Batch batch = batch_list.get(j);
                if (batch.getId_batch()==-1){ // batch creation
                    // batch save
                    batch.setType_batch(type_batch);
                    int id_batch = (int) session.save(new Batch(batch));
                    // move/s save
                    WarehouseMove move = new WarehouseMove(currentTimestamp, ContextFX.getInstance().getId_User(), batch.getQuantity(), zone_list.get(j).getId_zone(), vh.getId_vehicle(), mov_type, warehouse_save_id,id_batch);
                    session.save(move);
                    if (mov_type==2){
                        WarehouseMove t_move = new WarehouseMove(currentTimestamp, ContextFX.getInstance().getId_User(), batch.getQuantity(), zone_list.get(j).getId_zone(), vh.getId_vehicle(), 4, this.id_warehouse,id_batch);
                        session.save(t_move);
                    }
                    continue;
                }
                // zone update
                Query zone_q = session.createQuery(
                        "update WarehouseZone set free = :s_status "+" where id_zone = :zoneId");
                if (batch.getId_batch()==-1){
                    zone_q.setParameter("s_status",false);
                }else{
                    zone_q.setParameter("s_status",true);
                }
                zone_q.setParameter("zoneId", zone_list.get(j).getId_zone());
                zone_q.executeUpdate();
                // batch update
                Query query = session.createQuery(
                    "update Batch set type_batch = :t_batch , quantity = :b_quantity" + " where id_batch = :batchId ");
                query.setParameter("t_batch", type_batch);
                query.setParameter("b_quantity",batch.getQuantity());
                query.setParameter("batchId", batch.getId_batch());
                query.executeUpdate();
                // move save
                WarehouseMove move = new WarehouseMove(currentTimestamp, ContextFX.getInstance().getId_User(), batch.getQuantity(), zone_list.get(j).getId_zone(), vh.getId_vehicle(), mov_type, warehouse_save_id,batch.getId_batch());
                session.save(move);
                if (mov_type==2){
                    WarehouseMove t_move = new WarehouseMove(currentTimestamp, ContextFX.getInstance().getId_User(), batch.getQuantity(), zone_list.get(j).getId_zone(), vh.getId_vehicle(), 4, this.id_warehouse,batch.getId_batch());
                    session.save(t_move);
                }
            }
            r_d_iterator.set(4, true);
            this.routing_data.set((int)this.routing_data.get(0), r_d_iterator);
        }
        
        
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        GraphicsUtils gu = new GraphicsUtils();
        gu.popupNotif("Movimiento Completado", "Ha grabado exitosamente la orden de despacho.","Terminar");
        goDepartureMoveList();
    }
    
    @FXML
    private void goDepartureMoveList() throws IOException{
        ContextFX.getInstance().setId(this.id_warehouse);
        main.showWhDepartureMoveList();
    }
    
    
    @FXML
    public void nextRoute() throws IOException{
        int size = this.routing_data.size();
        int index = (int) this.routing_data.get(0);
        if (index+1>size-1){
            System.out.println("No hay mas rutas");
            return;
        }
        this.routing_data.set(0, index+1);
        ContextFX.getInstance().setPolymorphic_list(routing_data);
        ContextFX.getInstance().setId(this.id_warehouse);
        main.showWhDepartureMoveRoute();
    }
    
    @FXML
    public void prevRoute() throws IOException{
        int index = (int) this.routing_data.get(0);
        if (index-3<=0){
            System.out.println("No rutas previas");
            return;
        }
        this.routing_data.set(0, index-1);
        ContextFX.getInstance().setPolymorphic_list(routing_data);
        ContextFX.getInstance().setId(this.id_warehouse);
        main.showWhDepartureMoveRoute();
    }

    private void loadData(Session session) throws SQLException, ClassNotFoundException{
        // we need to get each batch selected and add it to the table
        this.batchesList = FXCollections.observableArrayList();
        if (this.mode==0){
            int index= (int) this.routing_data.get(0);
            ArrayList<Object> routing_sub_data = (ArrayList<Object>) this.routing_data.get(index);
            // 0 - batches
            ArrayList<Batch> batches = (ArrayList<Batch>) routing_sub_data.get(0);
            // 1 - zones
            ArrayList<WarehouseZone> zones = (ArrayList<WarehouseZone>) routing_sub_data.get(1);
            // 2 - vehicle
            // 3 - route
            ArrayList<Coord> route = (ArrayList<Coord>) routing_sub_data.get(3);
            //routingUtils.printCoords(route);
            for (int i=0; i< batches.size(); i++) {
                this.batchesList.add(new BatchWH_Move(batches.get(i),zones.get(i)));
                this.product_names.add(getNameProduct(batches.get(i).getId_product(), session));
            }
        }else{ // special move
            ArrayList<BatchWH_Move> aux_list = (ArrayList<BatchWH_Move>) ContextFX.getInstance().getList();
            this.zoneList=new ArrayList<WarehouseZone>();
            for (int i = 0; i < aux_list.size(); i++) {
                this.zoneList.add(aux_list.get(i).getZone());
                this.batchesList.add(aux_list.get(i));
            }
        }
        
        batchTable.setItems(null);
        batchTable.setItems(batchesList);
    }

    private void drawMap(Session session) {
        Criteria criteria = session.createCriteria(Warehouse.class);
        criteria.add(Restrictions.eq("id_warehouse", this.id_warehouse));
        List rs = criteria.list();
        this.wh = (Warehouse) rs.get(0);
        GraphicsUtils gu = new GraphicsUtils();
        this.y=this.wh.getWidth();
        this.x=this.wh.getLength();
        this.real_map=gu.initMap(this.y,this.x);
        this.crackList=gu.putCRacks(id_warehouse, real_map);
        setupCRacksGraph();
        gc = mapCanvas.getGraphicsContext2D();
        int index= (int) this.routing_data.get(0);
        ArrayList<Object> routing_sub_data = (ArrayList<Object>) this.routing_data.get(index);
        ArrayList<Coord> route = (ArrayList<Coord>) routing_sub_data.get(3);
        gu.drawVisualizationMap(gc, y, x,this.real_map, route, this.batchesList);
    }
    
    
    private void setupCRacksGraph() {      
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

   private String getNameProduct(int prod_id,Session session) {
        Query query=session.createSQLQuery("SELECT name FROM campis.product WHERE id_product = "+String.valueOf(prod_id));
        List rs = query.list();
        if (rs.size()==0) return " ";
        else{
            return (String) rs.get(0);
        }
    }
    

    private int singleHer(Batch batch) {
        String her = batch.getHeritage();
        StringBuilder str = new StringBuilder(her);
        str.deleteCharAt(her.indexOf('['));
        str.deleteCharAt(her.indexOf(']')-1);
        
        return Integer.parseInt(str.toString());
    }
    
    public void recordLog(int id) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("logs.txt", true));
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        writer.append(System.lineSeparator() + dateFormat.format(date));
        writer.append(System.lineSeparator() + ContextFX.getInstance().getUser().getFirstname() + " " + ContextFX.getInstance().getUser().getLastname()  +" creo una orden de despacho de id: " + id);     
        writer.close();
    }

    private void saveDispatchOrder(Session session) {
        ArrayList<Object> dispatchOrder_and_lines= ContextFX.getInstance().getDispatchOrder();
        DispatchOrder d_order = (DispatchOrder)dispatchOrder_and_lines.remove(0);
        int id_request_order = d_order.getId_request_order();
        int id_d_order = (int) session.save(d_order);
        
        try {
                this.recordLog(id_d_order);
        } catch (IOException e) {            
        }
        
        for (int i = 0; i < dispatchOrder_and_lines.size(); i++) {
            DispatchOrderLine d_order_line = (DispatchOrderLine) dispatchOrder_and_lines.get(i);
            d_order_line.setId_dispatch_order(id_d_order);
            session.save(d_order_line);
        }
        
        // Verfify id_request_order
        if (orderComplete(session,id_request_order) == true){
            Query query = session.createSQLQuery("UPDATE campis.request_order SET satus = 'FINALIZADO' WHERE id_request_order = "+id_request_order);
        }
    }

    private boolean orderComplete(Session session,int id_request_order) {
        
        Query request_query = session.createSQLQuery("SELECT id_product,quantity FROM campis.request_order_line WHERE id_request_order = "+id_request_order);
        ArrayList<Object> request_rs = new ArrayList<Object>(request_query.list());
        
        for (int i = 0; i < request_rs.size(); i++) {
            Object[] rs_pair = (Object[]) request_rs.get(i);
            int id_product =(int) rs_pair[0];
            int prod_qt = (int) rs_pair[1];
            Query dispatch_query = session.createSQLQuery("SELECT id_dispatch_order FROM campis.dispatch_order WHERE \n"+
                                                    "id_request_order = "+id_request_order);
            ArrayList<Object> dispatch_rs = new ArrayList<>(dispatch_query.list());
            
            for (int j = 0; j < dispatch_rs.size(); j++) {
                int id_dispatch_order = (int) dispatch_rs.get(j);
                Query dispatch_line_query = session.createSQLQuery("SELECT quantity FROM campis.dispatch_order_line WHERE \n"+
                                                    "id_dispatch_order = "+id_dispatch_order+" AND id_product = "+id_product);
                
                ArrayList<Object> dispatch_line_rs = new ArrayList<Object>(dispatch_line_query.list());
                for (int k = 0; k < dispatch_line_rs.size(); k++) {
                    prod_qt -= (int) dispatch_line_rs.get(k);
                }
            }
                            

            if (prod_qt >0 ) return false;
        }

        
        return true;
    }
}

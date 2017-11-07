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
import campis.dp1.models.Product;
import campis.dp1.models.Rack;
import campis.dp1.models.TabuProblem;
import campis.dp1.models.TabuSolution;
import campis.dp1.models.utils.GraphicsUtils;
import campis.dp1.models.Warehouse;
import campis.dp1.models.WarehouseMove;
import campis.dp1.models.WarehouseZone;
import campis.dp1.models.routing.Grasp;
import campis.dp1.models.routing.GraspResults;
import campis.dp1.models.routing.RouteGen;
import campis.dp1.models.utils.RoutingUtils;
import campis.dp1.services.TabuSearchService;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import javafx.util.StringConverter;
import javax.persistence.Query;
import oracle.jrockit.jfr.tools.ConCatRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sergio
 */
public class EntryMoveRouteController implements Initializable{
    private Main main;
    private int id_warehouse;
    private Warehouse wh;
    private ArrayList<Integer> id_batchesList = new ArrayList<>();
//    private ArrayList<Integer> numList = new ArrayList<>();
    private ArrayList<WarehouseZone> zoneList = new ArrayList<>();
    private ObservableList<BatchWH_Move> batchesList;
    private ArrayList<CRack> crackList = new ArrayList<>();
    private CGraph routesGraph = new CGraph();
    ArrayList<Product> product_names = new ArrayList<>();
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

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.id_warehouse=ContextFX.getInstance().getId();
        try{
            int m_test = ContextFX.getInstance().getWhMoveType() + 1;
            this.mode=1;
        }catch(Exception e){
            this.mode=0;
        }
        try{
            qtCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());            
            prodCol.setCellValueFactory(cellData -> new SimpleStringProperty(getNameProduct(cellData.getValue().getId_product())));
            zoneCol.setCellValueFactory(cellData -> cellData.getValue().getZoneStr());
            
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            
            batchLoadData(session);
            drawMap(session);
            session.close();
            sessionFactory.close();
            generateRoute();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(WarehouseListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void saveEntryMove() throws IOException{
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        if (this.mode==1) saveBatches(session);
        for (int i=0; i<batchesList.size();i++) {
            Batch batch = this.save_batches.get(i);
            Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            WarehouseMove move = new WarehouseMove(currentTimestamp, ContextFX.getInstance().getId_User(), batch.getQuantity(), batchesList.get(i).getZone().getId_zone(), 2, 1, id_warehouse,batch.getId_batch());
            batch.setType_batch(3);
            Query query = session.createQuery(
                "update Batch set type_batch = :t_batch " + " where id_batch = :batchId ");
            query.setParameter("t_batch", 3);
            query.setParameter("batchId", batch.getId_batch());
            query.executeUpdate();
            Query zone_q = session.createQuery(
                    "update WarehouseZone set free = :s_status "+" where id_zone = :zoneId");
            zone_q.setParameter("s_status",false);
            zone_q.setParameter("zoneId", batchesList.get(i).getZone().getId_zone());
            zone_q.executeUpdate();
            session.save(move);
        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        goEntryMoveList();
    }
    
    @FXML
    private void goEntryMoveList() throws IOException{
        ContextFX.getInstance().setId(this.id_warehouse);
        main.showWhEntryMoveList();
    }
    
    @FXML
    private void generateRoute(){
        RoutingUtils utils=new RoutingUtils();
        ArrayList<Coord> batchesCoords = readPositions();
        System.out.println(batchesCoords);
//        RouteGen routeGen = new RouteGen(this.real_map,this.routesGraph, this.batchesCoords);
        Grasp graspSolution = new Grasp(this.real_map,this.routesGraph, batchesCoords);
        GraspResults gResults = graspSolution.execute();
        ArrayList<Coordinates> tabuInput = toCoordinates(gResults.getProducts()); // orden de productos
        RouteGen routeGen = graspSolution.getRouteGen();
        routeGen.printDict();
        utils.printCoords(gResults.getRoute());
        utils.printCoordinates(tabuInput);
        TabuSearchService tabu = new TabuSearchService();
        TabuProblem tabuProblem = new TabuProblem(routeGen);
        TabuSolution tabuSolution = tabu.search(tabuProblem, tabuInput);
//        System.out.println("Solución final del tabu fue:\n");
//        tabuSolution.printOrder();
        
    }

    private void batchLoadData(Session session) throws SQLException, ClassNotFoundException{
        // we need to get each batch selected and add it to the table
        this.batchesList = FXCollections.observableArrayList();
        if (this.mode==0){
            this.id_batchesList=(ArrayList<Integer>) ContextFX.getInstance().getList();
            this.zoneList=(ArrayList<WarehouseZone>) ContextFX.getInstance().get2ndList();    
            for (int i=0; i< id_batchesList.size(); i++) {
                Criteria criteria = session.createCriteria(Batch.class);
                criteria.add(Restrictions.eq("id_batch",id_batchesList.get(i)));
                List rs = criteria.list();
                Batch batch = (Batch)rs.get(0);
                this.save_batches.add(batch);
                this.batchesList.add(new BatchWH_Move(batch,zoneList.get(i)));
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
        gu.drawVisualizationMap(gc, y, x, real_map);
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

   private String getNameProduct(int get) {
        for (Product prod: product_names) {
            if (prod.getId_product() == get){
                return prod.getName();
            }
        }
        return " ";
    }

    private ArrayList<Coord> readPositions() {
        ObservableList<BatchWH_Move> aux_list = batchTable.getItems();
        ArrayList<Coord> returnable = new ArrayList<>();
        int list_size = aux_list.size();
        for (int i = 0; i < list_size; i++) {
            int pos_y = aux_list.get(i).getZone().getPos_y();
            int pos_x = aux_list.get(i).getZone().getPos_x();
            System.out.println("[ "+pos_y+", "+pos_x+"]");
            if (pos_y != -1 && pos_x != -1){
                returnable.add(new Coord(pos_y,pos_x));
            }
        }
        return returnable;
    }

    

    private ArrayList<Coordinates> toCoordinates(ArrayList<Coord> products) {
        ArrayList<Coordinates> returnable = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Coord aux = products.get(i);
            returnable.add(new Coordinates(aux.x,aux.y));
        }
        return returnable;
    }

    private void saveBatches(Session session) {
        for (int i = 0; i < this.batchesList.size(); i++) {
            Batch batch = new Batch(this.batchesList.get(i));
            this.id_batchesList.add((Integer) session.save(batch));
        }
        for (int i=0; i<this.batchesList.size();i++){
            this.batchesList.get(i).setId_batch(this.id_batchesList.get(i));
            this.save_batches.add(this.batchesList.get(i));
        }
    }
}

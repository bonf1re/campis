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
import campis.dp1.models.Rack;
import campis.dp1.models.TabuProblem;
import campis.dp1.models.TabuSolution;
import campis.dp1.models.utils.GraphicsUtils;
import campis.dp1.models.Warehouse;
import campis.dp1.models.WarehouseZone;
import campis.dp1.models.routing.Grasp;
import campis.dp1.models.routing.GraspResults;
import campis.dp1.models.routing.RouteGen;
import campis.dp1.models.utils.RoutingUtils;
import campis.dp1.services.TabuSearchService;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
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
public class DepartureMoveRouteController implements Initializable{
    private Main main;
    private int id_warehouse;
    private Warehouse wh;
    private ArrayList<Integer> id_batchesList = new ArrayList<>();
    private ArrayList<WarehouseZone> zoneList = new ArrayList<>();
    private ObservableList<BatchWH_Move> batchesList;
    private ArrayList<CRack> crackList = new ArrayList<>();
    private CGraph routesGraph = new CGraph();
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
    private TableColumn<BatchWH_Move,Integer> idCol;
    @FXML
    private TableColumn<BatchWH_Move, Integer> qtCol;
    @FXML
    private TableColumn<BatchWH_Move, Integer> prodCol;
    @FXML
    private TableColumn<BatchWH_Move, Integer> posX;
    @FXML
    private TableColumn<BatchWH_Move, Integer> posY;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.id_warehouse = ContextFX.getInstance().getId();
        this.id_batchesList=(ArrayList<Integer>) ContextFX.getInstance().getList();
        this.zoneList=(ArrayList<WarehouseZone>) ContextFX.getInstance().get2ndList();
        try{
            idCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_batch()).asObject());
            qtCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());            
            prodCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_product()).asObject());
            
            // set column for pos_x
            posX.setCellFactory(
                TextFieldTableCell.<BatchWH_Move,Integer>forTableColumn(new StringConverter<Integer>(){
                    @Override
                    public String toString(Integer value){
                        return value.toString();
                    }
                    @Override
                    public Integer fromString(String string){
                        return Integer.parseInt(string);
                    }
                }));
            
            
            
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            
            batchLoadData(session);
            drawMap(session);
            
            session.close();;
            sessionFactory.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(WarehouseListController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        for (int i=0;i<this.id_batchesList.size();i++) {
            Criteria criteria = session.createCriteria(Batch.class);
            criteria.add(Restrictions.eq("id_batch",id_batchesList.get(i)));
            List rs = criteria.list();
            this.batchesList.add(new BatchWH_Move((Batch)rs.get(0),this.zoneList.get(i)));
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
        //gu.drawVisualizationMap(gc, y, x, real_map);
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
}

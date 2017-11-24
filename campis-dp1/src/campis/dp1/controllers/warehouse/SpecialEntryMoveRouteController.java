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
import campis.dp1.models.Vehicle;
import campis.dp1.models.utils.GraphicsUtils;
import campis.dp1.models.Warehouse;
import campis.dp1.models.WarehouseMove;
import campis.dp1.models.WarehouseZone;
import campis.dp1.models.utils.RoutingUtils;
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
import javafx.scene.text.Text;
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
public class SpecialEntryMoveRouteController implements Initializable{
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

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.id_warehouse=ContextFX.getInstance().getId();
        
        this.routing_data = ContextFX.getInstance().getPolymorphic_list();
        
        Vehicle vh =(Vehicle) ((ArrayList<Object>) this.routing_data.get((int)this.routing_data.get(0))).get(2);
        /*
        0 - indice
        1 -
        */
        
        this.vhText.setText("Vehicle: "+ vh.getPlate() + " \nde capacidad : "+vh.getMax_weight());
        this.pCounterField.setText("<"+String.valueOf(this.routing_data.get(0))+"/"+String.valueOf(this.routing_data.size()-1)+">");
        
        try{
            int m_test = ContextFX.getInstance().getWhMoveType() + 1;
            this.mode=1;
        }catch(Exception e){
            this.mode=0;
        }
        
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
    private void saveEntryMove() throws IOException{
        // Will save all
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        // Previous verification
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        for (int i = 1; i < this.routing_data.size(); i++) {
            ArrayList<Object> r_d_iterator = (ArrayList<Object>) this.routing_data.get(i);
            boolean saved = (boolean) r_d_iterator.get(4);
            if (saved == true ){
                System.out.println("Este movimiento ya ha sido grabado.");
                continue;
            }
            
            ArrayList<Batch> batch_list =(ArrayList<Batch>) r_d_iterator.get(0);
            ArrayList<WarehouseZone> zone_list = (ArrayList<WarehouseZone>) r_d_iterator.get(1);
            Vehicle vh = (Vehicle) r_d_iterator.get(2);
            ArrayList<Coord> route= (ArrayList<Coord>) r_d_iterator.get(3);
            
            for (int j = 0; j < batch_list.size(); j++) {
                // zone update
                Query zone_q = session.createQuery(
                        "update WarehouseZone set free = :s_status "+" where id_zone = :zoneId");
                zone_q.setParameter("s_status",false);
                zone_q.setParameter("zoneId", zone_list.get(j).getId_zone());
                zone_q.executeUpdate();
                
                Batch batch = batch_list.get(j);
                System.out.println("campis.dp1.controllers.warehouse.SpecialEntryMoveRouteController.saveEntryMove()");
                System.out.println(batch.getId_supplier());
                // batch save
                batch.setType_batch(3);
                batch.setState(true);
                int id_batch = (int) session.save(new Batch(batch));

                // move save
                WarehouseMove move = new WarehouseMove(currentTimestamp, ContextFX.getInstance().getId_User(), batch.getQuantity(), zone_list.get(j).getId_zone(), vh.getId_vehicle(), 0, id_warehouse,id_batch);
                session.save(move);
                   
            }
            r_d_iterator.set(4, true);
            this.routing_data.set((int)this.routing_data.get(0), r_d_iterator);
        }
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();    
        
        this.goEntryMoveList();
    }
    
    public void goWhEntryMoveList() throws IOException{
        ContextFX.getInstance().setId(id_warehouse);
        ContextFX.getInstance().setPolymorphic_list(null);
        ContextFX.getInstance().setMode(0);
        
        main.showWhEntryMoveList();
    }
    
    @FXML
    private void goEntryMoveList() throws IOException{
        ContextFX.getInstance().setId(this.id_warehouse);
        main.showWhEntryMoveList();
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
        
        main.showWhEntryMoveRoute();
    }
    
    @FXML
    public void prevRoute() throws IOException{
        int size = this.routing_data.size();
        int index = (int) this.routing_data.get(0);
        if (index-1<=0){
            System.out.println("No rutas previas");
            return;
        }
        this.routing_data.set(0, index-1);
        ContextFX.getInstance().setPolymorphic_list(routing_data);
        ContextFX.getInstance().setId(this.id_warehouse);
        main.showWhEntryMoveRoute();
    }

    private void loadData(Session session) throws SQLException, ClassNotFoundException{
        // we need to get each batch selected and add it to the table
        this.batchesList = FXCollections.observableArrayList();
        RoutingUtils routingUtils = new RoutingUtils();
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
        gu.drawVisualizationMap(gc, y, x,this.real_map, route);
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

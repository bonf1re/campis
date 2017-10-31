/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Batch;
import campis.dp1.models.CGraph;
import campis.dp1.models.CNode;
import campis.dp1.models.CRack;
import campis.dp1.models.Coord;
import campis.dp1.models.Rack;
import campis.dp1.models.Warehouse;
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
public class RouteMoveController implements Initializable{
    private Main main;
    private int id_warehouse;
    private Warehouse wh;
    private ArrayList<Integer> id_batchesList = new ArrayList<>();
    private ObservableList<Batch> batchesList;
    private ArrayList<CRack> crackList = new ArrayList<>();
    private CGraph routesGraph = new CGraph();
    
    
    @FXML
    private Canvas mapCanvas;
    private GraphicsContext gc;
    private int[][] real_map;
    private int x;
    private int y;
    
    @FXML
    private TableView<Batch> batchTable;
    @FXML
    private TableColumn<Batch,Integer> idCol;
    @FXML
    private TableColumn<Batch, Integer> qtCol;
    @FXML
    private TableColumn<Batch, Integer> prodCol;
    @FXML
    private TableColumn<Batch, Integer> posX;
    @FXML
    private TableColumn<Batch, Integer> posY;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.id_warehouse = ContextFX.getInstance().getId();

        try{
            idCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_batch()).asObject());
            qtCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());            
            prodCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_product()).asObject());
            
            // set column for pos_x
            posX.setCellFactory(
                TextFieldTableCell.<Batch,Integer>forTableColumn(new StringConverter<Integer>(){
                    @Override
                    public String toString(Integer value){
                        return value.toString();
                    }
                    @Override
                    public Integer fromString(String string){
                        return Integer.parseInt(string);
                    }
                }));
            posX.setCellValueFactory(
                    cellData->new SimpleIntegerProperty(-1).asObject()
            );
            // set column for pos_y
            posY.setCellFactory(
                TextFieldTableCell.<Batch,Integer>forTableColumn(new StringConverter<Integer>(){
                    @Override
                    public String toString(Integer value){
                        return value.toString();
                    }
                    @Override
                    public Integer fromString(String string){
                        return Integer.parseInt(string);
                    }
                }));
            posY.setCellValueFactory(
                    cellData->new SimpleIntegerProperty(-1).asObject()
            );
            
            // make sure table is editable so fields can be edited
            batchTable.setEditable(true);
            
            
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
            Logger.getLogger(ListWarehouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void batchLoadData(Session session) throws SQLException, ClassNotFoundException{
        // we need to get each batch selected and add it to the table
        this.batchesList = FXCollections.observableArrayList();
        this.id_batchesList=(ArrayList<Integer>) ContextFX.getInstance().getList();
        for (Integer integer : id_batchesList) {
            Criteria criteria = session.createCriteria(Batch.class);
            criteria.add(Restrictions.eq("id_batch",integer));
            List rs = criteria.list();
            this.batchesList.add((Batch) rs.get(0));
        }
        batchTable.setItems(null);
        batchTable.setItems(batchesList);
    }
    
    private void initializeMap() {
        this.y=wh.getWidth();
        this.x=wh.getLength();
        this.real_map = new int[this.y][this.x];
        for (int i = 0; i < this.y; i++) {
            for (int j = 0; j < this.x; j++) {
                this.real_map[i][j]=0;
            }
        }
    }

    private void drawMap(Session session) {
        Criteria criteria = session.createCriteria(Warehouse.class);
        criteria.add(Restrictions.eq("id_warehouse", this.id_warehouse));
        List rs = criteria.list();
        this.wh = (Warehouse) rs.get(0);
        initializeMap();
        putCRacks(session);
        gc = mapCanvas.getGraphicsContext2D();
        draw(gc);
    }
    
    
    private void putCRacks(Session session) {      
        Criteria criteria =  session.createCriteria(Rack.class);
        criteria.add(Restrictions.eq("id_warehouse",this.id_warehouse));
        List racks = criteria.list();
        for (int i = 0; i < racks.size(); i++) {
            this.crackList.add(new CRack((Rack) racks.get(i)));
        }
        
        for (CRack rack : this.crackList) {
            int rack_y = rack.getPos_y();
            int rack_x = rack.getPos_x();
            int rack_length = rack.getN_columns();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < rack_length; j++) {
                    this.real_map[i+rack_y][j+rack_x]=1;
                }
            }
            for (int i = 0; i < 4; i++) {
                Coord corner = rack.getCorner(i);
                this.routesGraph.addNode(new CNode(corner));
            }
        }
        this.routesGraph.setup();
        
    }

    @FXML
    private void draw(GraphicsContext gc){
        //System.out.println(ContextFX.getInstance().getId());
        // String filename = "/media/Multimedia/Projects/GitProjects/GRASP-OPT2/Inputs/map_0.txt";
        // read_map(filename);
        
        // First we will draw the warehouse without racks
        
       
       float canvas_height=(float) gc.getCanvas().getHeight();
       float canvas_width =(float) gc.getCanvas().getWidth();

       int mult = 0;
       if (canvas_width/x > canvas_height/y){
           mult = (int) canvas_height/y;
           //mult = (int) canvas_height/x;
       }else{
           mult = (int) canvas_width/x;
           //mult = (int) canvas_width/y;
       }
       
       int padding_y=(int)(((int)canvas_height)-mult*y)/4;
       int padding_x=(int)(((int)canvas_width)-mult*x)/4;
       
       if (padding_y>0){
           padding_y+=mult/2;
       }
       if (padding_x>0){
           padding_x+=mult/2;
       }
       
       System.out.println(padding_x);
       System.out.println(padding_y);
       System.out.println(mult);
       
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                if (this.real_map[j][i]==1){
                   gc.setFill(Color.BLACK); 
                   gc.fillRect(i*mult+padding_x, j*mult+padding_y, mult, mult);
                }
                if (this.real_map[j][i]==0){
                    gc.setFill(Color.WHITE);
                    gc.setStroke(Color.BLACK);
                    gc.strokeRect(i*mult+padding_x, j*mult+padding_y, mult, mult);
                }
                if (this.real_map[j][i]==2){
                    gc.setFill(Color.RED); 
                    gc.fillRect(i*mult+padding_x, j*mult+padding_y, mult, mult);
                }
                 
               
            }
        }
    }
}

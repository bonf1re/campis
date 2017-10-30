/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.CRack;
import campis.dp1.models.Coord;
import campis.dp1.models.Rack;
import campis.dp1.models.Warehouse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sergio
 */
public class VisualizeWarehouseController implements Initializable {
    private Main main;
    @FXML
    private Canvas mapCanvas;
    private GraphicsContext gc;
    private int[][] real_map;
    private int x;
    private int y;
    private int warehouse_id;
    private ArrayList<CRack> crackList = new ArrayList<>();
    
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField lengthField;
    @FXML
    private JFXTextField widthField;
    @FXML
    private JFXComboBox  statusCb;
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void draw(GraphicsContext gc){
        //System.out.println(ContextFX.getInstance().getId());
        // String filename = "/media/Multimedia/Projects/GitProjects/GRASP-OPT2/Inputs/map_0.txt";
        // read_map(filename);
        
        // First we will draw the warehouse without racks
        initializeMap();
        putCRacks();
        
       
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.warehouse_id = ContextFX.getInstance().getId();
        statusCb.getItems().addAll("Habilitado","Deshabilitado");
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Warehouse.class);
        criteria.add(Restrictions.eq("id_warehouse",this.warehouse_id));
        Warehouse wh = (Warehouse) criteria.list().get(0);
        this.nameField.setText(wh.getName());
        this.lengthField.setText(Integer.toString(wh.getLength()));
        this.widthField.setText(Integer.toString(wh.getWidth()));
        if (wh.isStatus() == true){
            this.statusCb.getSelectionModel().select(0);
        }else{
            this.statusCb.getSelectionModel().select(1);
        }
        
        gc = mapCanvas.getGraphicsContext2D();
        draw(gc);
    }


    public void read_map(String mapPath){
        File file = new File(mapPath);
        try {
            // For reading content
            Scanner inputFile = new Scanner(file);

            // We will store them in aux array 1000x1000
            int[][] auxMap = new int[1000][1000];
            while (inputFile.hasNext()){
                String line =inputFile.nextLine();
                this.x=0;
                for(char c:line.toCharArray()){
                    auxMap[this.y][this.x]=Character.getNumericValue(c);
                    this.x+=1;
                }
                this.y+=1;
            }
            inputFile.close();

            // Logging
            // System.out.println(this.y);
            // System.out.println(this.x);

            // Build real real_map
            this.real_map = new int[this.y][this.x];
            for(int j = 0; j < this.y; j++){
                for(int i = 0; i < this.x; i++){
                    this.real_map[j][i] = auxMap[j][i];
                }
            }

            // this.show();


        }catch(IOException e){
            System.out.println("No se encontro archivo.");
            return;
        }
    }
    
    @FXML
    private void goListWarehouse() throws IOException{
        main.showListWarehouse();
    }
    
    @FXML
    private void editWarehouse() throws IOException{
        ContextFX.getInstance().setId(warehouse_id);
        main.showEditWarehouse();
    }

    private void initializeMap() {
        this.y=Integer.parseInt(this.lengthField.getText());
        this.x=Integer.parseInt(this.widthField.getText());
        this.real_map = new int[this.y][this.x];
        for (int i = 0; i < this.y; i++) {
            for (int j = 0; j < this.x; j++) {
                this.real_map[i][j]=0;
            }
        }
    }

    private void putCRacks() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria =  session.createCriteria(Rack.class);
        criteria.add(Restrictions.eq("id_warehouse",this.warehouse_id));
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
                this.real_map[corner.y][corner.x] = 2;
            }
        }
        
    }

}

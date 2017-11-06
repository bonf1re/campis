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
import campis.dp1.models.Utils.GraphicsUtils;
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
public class WarehouseVisualizeController implements Initializable {
    private Main main;
    @FXML
    private Canvas mapCanvas;
    private GraphicsContext gc;
    private int[][] real_map;
    private int x;
    private int y;
    private int warehouse_id;
    private ArrayList<CRack> crackList;
    
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
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.warehouse_id = ContextFX.getInstance().getId();
        statusCb.getItems().addAll("Habilitado","Deshabilitado");
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
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
        
        GraphicsUtils gu = new GraphicsUtils();
        gc = mapCanvas.getGraphicsContext2D();
        this.y=Integer.parseInt(this.widthField.getText());
        this.x=Integer.parseInt(this.lengthField.getText());
        this.real_map=gu.initMap(this.y,this.x);
        this.crackList=gu.putCRacks(warehouse_id, real_map);
        gu.drawVisualizationMap(gc,this.y,this.x,this.real_map);
    }
    
    @FXML
    private void goWhList() throws IOException{
        main.showWhList();
    }
    
    @FXML
    private void whEdit() throws IOException{
        ContextFX.getInstance().setId(warehouse_id);
        main.showWhEdit();
    }

    

    

}

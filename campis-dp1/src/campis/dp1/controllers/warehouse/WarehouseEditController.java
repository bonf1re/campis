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
import campis.dp1.models.Utils.GraphicsUtils;
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
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Gina Bustamante
 */
public class WarehouseEditController implements Initializable {
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
    private void whUpdate() throws IOException{
        boolean verified = verifiyUpdate();
        if (verified == false){
            return;
        }
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Warehouse set name=:newName,"+
                                            "length=:newLength,"+
                                            "width=:newWidth,"+
                                            "status=:newStatus"+
                                            " where id_warehouse=:oldIdWh");
        query.setParameter("newName", nameField.getText());
        query.setParameter("newLength", Integer.parseInt(lengthField.getText()));
        query.setParameter("newWidth", Integer.parseInt(widthField.getText()));
        boolean aux_status;
        if (statusCb.getSelectionModel().getSelectedIndex()==0){
            aux_status=true;
        }else{
            aux_status=false;
        }
        query.setParameter("newStatus", aux_status);
        query.setParameter("oldIdWh", this.warehouse_id);
        int result = query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goWhList();
    }


    private boolean verifiyUpdate() {
        int new_length=Integer.parseInt(lengthField.getText());
        int new_width=Integer.parseInt(widthField.getText());
        for (CRack crack: this.crackList) {
            if (crack.getOrientation() == 0){ // horizontal
                if (crack.getPos_y()+2>=new_width) return false;
                if (crack.getPos_x()+crack.getN_columns()>=new_length) return false;
            }else{  // vertical
                if (crack.getPos_x()+2>=new_length) return false;
                if (crack.getPos_y()+crack.getN_columns()>=new_width) return false;
            }
        }
        return true;
    }
    
}

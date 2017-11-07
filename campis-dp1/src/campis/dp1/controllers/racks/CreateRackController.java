/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.racks;

import campis.dp1.Main;
import campis.dp1.models.Rack;
import campis.dp1.models.WarehouseZone;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Gina Bustamante
 */
public class CreateRackController implements Initializable{
    private Main main;
    
    @FXML
    private JFXTextField columnsField;
    @FXML
    private JFXTextField floorsField;
    @FXML
    private JFXTextField x_Field;
    @FXML
    private JFXTextField y_Field;
    @FXML
    private JFXComboBox orientationCb;
   
    @FXML
    private void goListRacks() throws IOException {
        main.showListRacks();
    }
    
    @FXML
    private void insertRack() throws IOException {
        
        //int orientation = orientationCb.getSelectionModel().getSelectedIndex();
        
        Rack r = new Rack(1, Integer.parseInt(x_Field.getText()), Integer.parseInt(y_Field.getText()), 
                            Integer.parseInt(columnsField.getText()), Integer.parseInt(floorsField.getText()),
                            0);
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(r);
        // create a zone for each column and floor
        int orientation = r.getOrientation();
        int init_y = r.getPos_y();
        int init_x = r.getPos_x();
        for (int i = 0; i < r.getN_columns(); i++) {
            for (int j = 0; j < r.getN_floors(); j++) {
                for (int k = 0; k < 2; k++) {
                    int y=init_y;
                    int x=init_x;
                    int z = j;
                    if (orientation==0){
                        x+=i;
                        y+=k;
                    }else{
                        y+=1;
                        x+=k;
                    }
                    WarehouseZone zone =  new WarehouseZone(r.getId_warehouse(), r.getId_rack(), x, y, z, true);
                    //session.beginTransaction();
                    session.save(zone);
                }
            }
        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListRacks();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
}

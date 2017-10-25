/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.Main;
import campis.dp1.models.WarehouseDAO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author sergio
 */
public class WarehouseController implements Initializable {
    private Main main;
    /* CreateView fields   */
    @FXML
    TextField create_name_tf;
    @FXML
    TextField create_length_tf;
    @FXML
    TextField create_width_tf;
    @FXML
    ComboBox create_status_cb;
    
    
    /* VisualizeView fields */
    @FXML
    private Canvas warehouseCanvas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void goWarehouseMainView() throws IOException{
        main.showWarehouseMainView();
    }
    
    @FXML
    private void goWarehouseCreateView() throws IOException{
        main.showWarehouseCreateView();
    }

    @FXML
    private void goSaveWarehouse() throws IOException{
        
    }
    
    @FXML
    private void goWarehouseVisualizeView() throws IOException{
        /* Canvas initialization */ 
//        GraphicsContext gc = 
//          warehouseCanvas.getGraphicsContext2D();
//        draw( gc );
        /* End canvas initialization */
        main.showWarehouseVisualizeView();
    }

    private void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillOval(50, 100, 200, 200);
        gc.setFill(Color.RED);
        gc.fillRect(300, 100, 200, 200);
    }
    
    @FXML
    private void insertWarehouse(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException{
        try{
            WarehouseDAO.insertWarehouse(create_name_tf.getText(),
                                        Integer.parseInt(create_length_tf.getText()),
                                        Integer.parseInt(create_width_tf.getText()),
                                        true);
            main.showWarehouseMainView();
        }catch(Exception e){
            throw e;
        }
    }
    
}

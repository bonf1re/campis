/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;
import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.BatchDisplay;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import campis.dp1.models.Permission;
import campis.dp1.models.View;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Gina Bustamante
 */
public class ListDispatchBatchController implements Initializable {
    
    @FXML
    private TableView<BatchDisplay> batchTable;
    @FXML
    private TableColumn<BatchDisplay, Integer> id_batchCol;
    @FXML
    private TableColumn<BatchDisplay, Integer> quantityCol;
    @FXML
    private TableColumn<BatchDisplay, String> arrival_dateCol;
    @FXML
    private TableColumn<BatchDisplay, String> exp_dateCol;
    @FXML
    private TableColumn<BatchDisplay, Integer> id_pordCol;
    @FXML
    private TableColumn<BatchDisplay, Integer> type_batchCol;
    @FXML
    private TableColumn<BatchDisplay, Integer> herCol;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

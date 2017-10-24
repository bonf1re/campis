package campis.dp1.controllers;

import campis.dp1.Main;
import static campis.dp1.controllers.productsController.searchCodMeasure;
import static campis.dp1.controllers.productsController.searchCodType;
import campis.dp1.model.RackDAO;
import campis.dp1.model.Rack;
import campis.dp1.util.DBUtil;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Gina Bustamante
 */
public class RacksController implements Initializable {
    private Main main;
    private ObservableList<Rack> racks;
    private ActionEvent actionEvent;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void goListRacks() throws IOException {
        main.showListRacks();
    }
    
    @FXML
    private void goNewRack() throws IOException {
        main.showNewRack();
    }
    
    @FXML
    private void goEditRack() throws IOException {
        main.showEditRack();
    }
    
    @FXML
    private JFXTextField columnasField;
    @FXML
    private JFXTextField pisosField;
    @FXML
    private JFXTextField x_Field;
    @FXML
    private JFXTextField y_Field;
    
    @FXML
    private void insertRack (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        try{
             RackDAO.insertRack(Integer.parseInt(columnasField.getText()), 
                                Integer.parseInt(pisosField.getText()), 
                                Integer.parseInt(x_Field.getText()), 
                                Integer.parseInt(y_Field.getText()));
            
            System.out.println("Rack inserted! \n");
            this.goListRacks();
        }catch (SQLException e){
            System.out.println("Error al insertar rack : " + e);
        }
    }
}

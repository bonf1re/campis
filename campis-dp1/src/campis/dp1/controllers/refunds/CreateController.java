/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.refunds;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Eddy
 */
public class CreateController implements Initializable{

    @FXML
    private JFXTextField nameClientField;
    @FXML
    private JFXDatePicker creationDate;
    @FXML
    private JFXDatePicker deliveryDate;
    @FXML
    private TableView<?> tablaProd;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> typeColumn;
    @FXML
    private TableColumn<?, ?> totalQtColumn;
    @FXML
    private TableColumn<?, ?> refundQtColumn;
    @FXML
    private TableColumn<?, ?> refundMaxQtColumn;
    @FXML
    private Label messageField1;
    @FXML
    private Label messageField2;
    @FXML
    private JFXTextField clientField;
    @FXML
    private JFXTextField districtField;
    @FXML
    private JFXTextField stateField;
    @FXML
    private JFXTextField priorityField;
    @FXML
    private JFXTextField igvField;
    @FXML
    private JFXComboBox<?> cbRequestOrderId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void goListDepartureMove(ActionEvent event) {
    }

    @FXML
    private void goDispatchMoveCreate(ActionEvent event) {
    }
    
}

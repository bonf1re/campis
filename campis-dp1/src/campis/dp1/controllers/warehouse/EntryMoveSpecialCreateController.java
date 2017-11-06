/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Product;
import campis.dp1.models.ProductWH_Move;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author sergio
 */
public class EntryMoveSpecialCreateController implements Initializable {
    private Main main;
    private int id_warehouse;
    
    @FXML
    private TableView<ProductWH_Move> batchTable;

    @FXML
    private TableColumn<ProductWH_Move, String> prodCol;

    @FXML
    private TableColumn<ProductWH_Move, Integer> qtCol;

    @FXML
    private TableColumn<ProductWH_Move, Integer> numCol;

    @FXML
    private TableColumn<ProductWH_Move, String> delCol;

    @FXML
    private JFXComboBox<String> cbMotive;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id_warehouse = ContextFX.getInstance().getId();
        cbMotive.getItems().addAll("Hallazgo","Proveedores");
         try{
            prodCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            qtCol.setCellValueFactory(cellData -> cellData.getValue().getQtLt().asObject());
            numCol.setCellValueFactory(cellData -> cellData.getValue().getNum().asObject());
            //delCol.setCellValueFactory(cellData -> cellData.getValue().getExpiration_date());
            delCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
            
            Callback<TableColumn<ProductWH_Move, String>, TableCell<ProductWH_Move, String>> cellFactory
                    = //
                new Callback<TableColumn<ProductWH_Move, String>, TableCell<ProductWH_Move, String>>() {
                @Override
                public TableCell<ProductWH_Move, String> call(TableColumn<ProductWH_Move, String> param) {
                    final TableCell<ProductWH_Move, String> cell = new TableCell<ProductWH_Move, String>() {
                    final Button btn = new Button("Just Do It");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                getTableView().getItems().remove(getIndex());
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                    return cell;
            }
                };
            delCol.setCellFactory(cellFactory);
            batchTable.setEditable(true);
        } catch (Exception ex) {
            Logger.getLogger(WarehouseListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchProds(){
        
    }
    
    public void goWhEntryMoveRoute() throws IOException{
        
    }
    
    public void goWhEntryMoveList() throws IOException{
        ContextFX.getInstance().setId(id_warehouse);
        main.showWhEntryMoveList();
    }
    
}

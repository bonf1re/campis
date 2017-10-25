
package campis.dp1.controllers;

import campis.dp1.Main;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDAO;
import campis.dp1.models.ProductType;
import campis.dp1.models.UnitOfMeasure;
import campis.dp1.util.DBUtil;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class productsController implements Initializable {
    private Main main;
    private ObservableList<Product> productos;
    private DBUtil dc;
    
    @FXML
    private void goListProduct() throws IOException{
        main.showListProduct();
    }
    
    private JFXTextField nameField;
    private JFXTextField weightField;
    private JFXComboBox measureField;
    private JFXTextField trademarkField;
    private JFXTextField priceField;
    private JFXComboBox typeField;
    private JFXTextArea descripField;
    @FXML
    private TableView<Product> tablaProd;
    @FXML
    private TableColumn<Product,String> itemCol;
    @FXML
    private TableColumn<Product,String> nomCol;
    @FXML
    private TableColumn<Product,String> tipoCol;
    @FXML
    private TableColumn<Product,Float> pesoCol;
    @FXML
    private TableColumn<Product,Integer> medidaCol;
    @FXML
    private TableColumn<Product,Integer> pStockCol;
    @FXML
    private TableColumn<Product,Integer> cStockCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dc = new DBUtil();
            itemCol.setCellValueFactory(cellData -> cellData.getValue().codProdProperty());
            nomCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            tipoCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
            pesoCol.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
            medidaCol.setCellValueFactory(cellData -> cellData.getValue().medidaProperty().asObject());
            pStockCol.setCellValueFactory(cellData -> cellData.getValue().pStockProperty().asObject());
            cStockCol.setCellValueFactory(cellData -> cellData.getValue().cStockProperty().asObject());
            /**/
            cargarData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(productsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarData() throws SQLException, ClassNotFoundException {
        try{
            productos = FXCollections.observableArrayList();
            productos = ProductDAO.getProducts();
        }catch(SQLException e){
            System.out.println("Lista de productos no encontrado, sentencia Select fallo: " + e);
            throw e;
        }
        
        tablaProd.setItems(null);
        tablaProd.setItems(productos);
    }
    
    public static int searchCodMeasure(String measure) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT DISTINCT id_unit_of_measure FROM campis.unit_of_measure WHERE  description='" + measure +"';";
        try {
            int codMeasure;
            UnitOfMeasure meas = null;
            ResultSet rsMeasure = DBUtil.dbExecuteQuery(selectStmt);
            while(rsMeasure.next()){
                meas = new UnitOfMeasure();
                meas.setId_measure(rsMeasure.getInt(1));
            }
            codMeasure = meas.getId_measure();
            return codMeasure;
        } catch (SQLException e) {
            System.out.print("No se encuentra la unidad de medida con esa descripcion, error ocurrido: " + e);
            throw e;
        }
    }
    
    public static String searchCodType(String type) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT DISTINCT id_product_type FROM campis.product_type WHERE  description='" + type +"';";
        try {
            String codType = null;
            ProductType typ = null;
            ResultSet rsType = DBUtil.dbExecuteQuery(selectStmt);
            while(rsType.next()){
                typ = new ProductType();
                typ.setId_prodType(rsType.getString(1));
            }
            codType = typ.getId_prodType();
            return codType;
        } catch (SQLException e) {
            System.out.print("No se encuentra la unidad de medida con esa descripcion, error ocurrido: " + e);
            throw e;
        }
    }
    
    @FXML
    private void insertProduct (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        try{
            int measure = searchCodMeasure(measureField.getEditor().getText());
            String type = searchCodType(typeField.getEditor().getText());
            ProductDAO.insertProduct(nameField.getText(), descripField.getText(), 1, 1, Float.parseFloat(weightField.getText()),
                                     trademarkField.getText(), Float.parseFloat(priceField.getText()), measure, type);
            System.out.println("Product inserted! \n");
            this.goListProduct();
        }catch (SQLException e){
            System.out.println("Error al insertar producto : " + e);
        }
    }
    
    @FXML
    private void goCreateProduct(ActionEvent event) throws IOException{
        main.showCreateProduct();
    }
}


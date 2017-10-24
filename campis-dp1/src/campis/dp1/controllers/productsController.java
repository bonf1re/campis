package campis.dp1.controllers;

import campis.dp1.Main;
import campis.dp1.model.Product;
import campis.dp1.model.ProductDAO;
import campis.dp1.model.ProductType;
import campis.dp1.model.UnitOfMeasure;
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

public class productsController implements Initializable {
    private Main main;
    private ObservableList<Product> productos;
    private ActionEvent actionEvent;
    
    @FXML
    private void goListProduct() throws IOException, SQLException, ClassNotFoundException {
        //fillTableProd(actionEvent);
        main.showListProduct();
    }
    
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField weightField;
    @FXML
    private JFXComboBox measureField;
    @FXML
    private JFXTextField trademarkField;
    @FXML
    private JFXTextField priceField;
    @FXML
    private JFXComboBox typeField;
    @FXML
    private JFXDatePicker expDateField;
    @FXML
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
        /*itemCol.setCellValueFactory(new PropertyValueFactory<Product,String>("id_poduct"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("id_product_type"));
        pesoCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        medidaCol.setCellValueFactory(new PropertyValueFactory<>("id_unit_of_measure"));
        pStockCol.setCellValueFactory(new PropertyValueFactory<>("p_stock"));
        cStockCol.setCellValueFactory(new PropertyValueFactory<>("c_stock"));*/
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
    
    public ObservableList<Product> cargarGrilla() throws SQLException, ClassNotFoundException{
        ObservableList<Product> listaProd = ProductDAO.getProducts();
        for(int i = 0; i<listaProd.size(); i++){
           Product prod = new Product();
           prod.setCodProd(listaProd.get(i).getCodProd());
           prod.setNombre(listaProd.get(i).getNombre());
           prod.setDescripcion(listaProd.get(i).getDescripcion());
           prod.setPhy_stock(listaProd.get(i).getPhy_stock());
           prod.setComm_stock(listaProd.get(i).getComm_stock());
           prod.setPeso(listaProd.get(i).getPeso());
           prod.setMarca(listaProd.get(i).getMarca());
           prod.setPrecio_base(listaProd.get(i).getPrecio_base());
           prod.setId_medida(listaProd.get(i).getId_medida());
           prod.setId_type(listaProd.get(i).getId_type());
        }
        return listaProd;
    }
    
    @FXML
    private void fillTableProd (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        ObservableList<Product> prod = cargarGrilla();
        tablaProd.setItems(prod);
    }
}

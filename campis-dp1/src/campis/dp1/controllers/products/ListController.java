package campis.dp1.controllers.products;

import campis.dp1.Main;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.ProductType;
import campis.dp1.models.UnitOfMeasure;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ListController implements Initializable {
    private Main main;
    private ObservableList<Product> productos;
    private ObservableList<ProductDisplay> productosView;
    
    @FXML
    private void goListProduct() throws IOException{
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
    private JFXTextArea descripField;
    @FXML
    private TableView<ProductDisplay> tablaProd;
    @FXML
    private TableColumn<ProductDisplay,Integer> itemCol;
    @FXML
    private TableColumn<ProductDisplay,String> nomCol;
    @FXML
    private TableColumn<ProductDisplay,Integer> tipoCol;
    @FXML
    private TableColumn<ProductDisplay,Float> pesoCol;
    @FXML
    private TableColumn<ProductDisplay,Integer> medidaCol;
    @FXML
    private TableColumn<ProductDisplay,Integer> pStockCol;
    @FXML
    private TableColumn<ProductDisplay,Integer> cStockCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            itemCol.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
            nomCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            tipoCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
            pesoCol.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
            medidaCol.setCellValueFactory(cellData -> cellData.getValue().medidaProperty().asObject());
            pStockCol.setCellValueFactory(cellData -> cellData.getValue().pStockProperty().asObject());
            cStockCol.setCellValueFactory(cellData -> cellData.getValue().cStockProperty().asObject());
            /**/
            cargarData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ObservableList<Product> getProducts() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        List lista = criteria.list();
        ObservableList<Product> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Product)lista.get(i));
        }
        sessionFactory.close();
        return returnable;
    }
    
    private void cargarData() throws SQLException, ClassNotFoundException {
        productos = FXCollections.observableArrayList();
        productosView = FXCollections.observableArrayList();
        productos = getProducts();
        for (int i = 0; i < productos.size(); i++) {
            ProductDisplay prod = new ProductDisplay(productos.get(i).getId_product(), productos.get(i).getName(), 
                                productos.get(i).getDescription(), productos.get(i).getP_stock(), productos.get(i).getC_stock(), 
                                productos.get(i).getWeight(), productos.get(i).getTrademark(), productos.get(i).getBase_price(), 
                                productos.get(i).getId_unit_of_measure(), productos.get(i).getId_product_type());
            productosView.add(prod);
        }
        tablaProd.setItems(null);
        tablaProd.setItems(productosView);
    }
    
    @FXML
    private void goCreateProduct(ActionEvent event) throws IOException{
        main.showCreateProduct();
    }
    
    @FXML
    private void goEditProduct(ActionEvent event) throws IOException{
        main.showEditProduct();
    }
}

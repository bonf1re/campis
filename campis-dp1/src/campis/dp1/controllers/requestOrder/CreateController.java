/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.requestOrder;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDisplay;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eddy
 */
public class CreateController implements Initializable {

    Main main;
    Integer id, quantity;
    float totalAmount = 0; 
    private ObservableList<Product> products;
    private ObservableList<ProductDisplay> productsView = FXCollections.observableArrayList();
    
    
    @FXML
    private JFXTextField amountField;
    
    @FXML
    private TableView<ProductDisplay> tablaProd;
    @FXML
    private TableColumn<ProductDisplay, Integer> idColumn;
    @FXML
    private TableColumn<ProductDisplay, String> nameColumn;
    @FXML
    private TableColumn<ProductDisplay, Integer> typeColumn;
    @FXML
    private TableColumn<ProductDisplay, Integer> quantityColumn;
    @FXML
    private TableColumn<ProductDisplay, Float> unitaryAmountColumn;
    @FXML
    private TableColumn<ProductDisplay, Float> finalAmountColumn;
    @FXML
    private TableColumn<ProductDisplay, String> stateColumn;
    @FXML
    private JFXTextField nameClientField;
    @FXML
    private JFXDatePicker creationDate;
    @FXML
    private JFXDatePicker deliveryDate;
    @FXML
    private JFXTextField codClientField;

    @FXML
    private void goAddItem() throws IOException {
        main.showAddItem();
    }

    @FXML
    private void goListRequestOrder() throws IOException {
        main.showListRequestOrder();
    }
    
    private ObservableList<Product> getProduct(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product", cod));
        List<Product> list = criteria.list();
        ObservableList<Product> returnable;
        returnable = FXCollections.observableArrayList();
        returnable.add(list.get(0));
        return returnable;
    }

    private void loadData(int cod, int quant) {
        products = FXCollections.observableArrayList();
        productsView = ContextFX.getInstance().getTempList();
        products = getProduct(cod);
        Float amount = quant*products.get(0).getBase_price();
        String state = "ENTREGA";
        totalAmount = ContextFX.getInstance().getTotAmount();
        totalAmount = totalAmount + amount;
        ContextFX.getInstance().setTotAmount(totalAmount);
        this.amountField.setText(Float.toString(totalAmount));
        
        ProductDisplay prod = new ProductDisplay(products.get(0).getId_product(), products.get(0).getName(),
                products.get(0).getDescription(), products.get(0).getP_stock(), quantity,
                amount, state, products.get(0).getBase_price(),
                products.get(0).getId_unit_of_measure(), products.get(0).getId_product_type());
        productsView.add(prod);
        ContextFX.getInstance().setTempList(productsView);
        productsView = ContextFX.getInstance().getTempList();
        tablaProd.setItems(null);
        tablaProd.setItems(productsView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            id = ContextFX.getInstance().getId();
            quantity = ContextFX.getInstance().getQuantity();
            
            idColumn.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
            quantityColumn.setCellValueFactory(cellData -> cellData.getValue().cStockProperty().asObject());
            unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().precioBProperty().asObject());
            finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
            stateColumn.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
            loadData(id,quantity);
        } catch (NullPointerException e) {
            idColumn.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
            quantityColumn.setCellValueFactory(cellData -> cellData.getValue().cStockProperty().asObject());
            unitaryAmountColumn.setCellValueFactory(cellData -> cellData.getValue().precioBProperty().asObject());
            finalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
            stateColumn.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        }
    }

}

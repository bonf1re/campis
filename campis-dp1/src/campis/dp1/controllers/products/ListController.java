package campis.dp1.controllers.products;

import campis.dp1.ContextFX;
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
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class ListController implements Initializable {

    private Main main;
    private ObservableList<Product> productos;
    private ObservableList<ProductDisplay> productosView;
    private int selected_id;

    private void goListProduct() throws IOException {
        main.showListProduct();
    }

    @FXML
    private TableView<ProductDisplay> tablaProd;
    @FXML
    private TableColumn<ProductDisplay, Integer> itemCol;
    @FXML
    private TableColumn<ProductDisplay, String> nomCol;
    @FXML
    private TableColumn<ProductDisplay, Integer> tipoCol;
    @FXML
    private TableColumn<ProductDisplay, Float> pesoCol;
    @FXML
    private TableColumn<ProductDisplay, Integer> medidaCol;
    @FXML
    private TableColumn<ProductDisplay, Integer> pStockCol;
    @FXML
    private TableColumn<ProductDisplay, Integer> cStockCol;
    @FXML
    private Button searchButton;
    @FXML
    private JFXTextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablaProd.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.codProdProperty().getValue().intValue();
            }
        );
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
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        List lista = criteria.list();
        ObservableList<Product> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Product) lista.get(i));
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

    private ObservableList<Product> getSearchList(String text) {
        ObservableList<Product> returnable;
        returnable = FXCollections.observableArrayList();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(text) == TRUE) {
                returnable.add(list.get(i));
            }
        }
        sessionFactory.close();
        return returnable;
    }

    @FXML
    private void searchButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String text = this.searchField.getText();
        if (text.compareTo("") == 0) {
            cargarData();
        } else {
            productos = FXCollections.observableArrayList();
            productosView = FXCollections.observableArrayList();
            productos = getSearchList(text);
            if (productos == null) {
                tablaProd.setItems(null);
            } else {
                for (int i = 0; i < productos.size(); i++) {
                    ProductDisplay prod = new ProductDisplay(productos.get(i).getId_product(), productos.get(i).getName(),
                            productos.get(i).getDescription(), productos.get(i).getP_stock(), productos.get(i).getC_stock(),
                            productos.get(i).getWeight(), productos.get(i).getTrademark(), productos.get(i).getBase_price(),
                            productos.get(i).getId_unit_of_measure(), productos.get(i).getId_product_type());
                    productosView.add(prod);
                }
            }
            tablaProd.setItems(null);
            tablaProd.setItems(productosView);
        }
    }

    @FXML
    private void goCreateProduct(ActionEvent event) throws IOException {
        main.showCreateProduct();
    }

    @FXML
    private void goEditProduct(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(selected_id);
        main.showEditProduct();
    }
    
    @FXML
    private void goViewProduct(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(selected_id);
        main.showViewProduct();
    }

    private void deleteProduct(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        Product prod = new Product();
        prod.setId_product(cod);
        session.delete(prod);
        session.getTransaction().commit();

        sessionFactory.close();
    }

    @FXML
    private void deleteProduct(ActionEvent event) throws SQLException, ClassNotFoundException {
        ContextFX.getInstance().setId(selected_id);
        Integer id_product = ContextFX.getInstance().getId();
        deleteProduct(selected_id);
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getId_product().compareTo(id_product) == 0){
                productos.remove(i);
            }
        }
        cargarData();
    }
    
}


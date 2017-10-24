package campis.dp1.controllers;

import javafx.fxml.FXML;
import campis.dp1.Main;
import campis.dp1.models.Product;
import java.io.IOException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Marco
 */
public class MainViewController {
    private Main main;
    /*@FXML
    private TableView<Product> tablaProd;
    @FXML
    private TableColumn<Product, String> itemCol;
    @FXML
    private TableColumn<Product, String> nomCol;
    @FXML
    private TableColumn<Product, String> tipoCol;
    @FXML
    private TableColumn<Product, Float> pesoCol;
    @FXML
    private TableColumn<Product, String> medidaCol;
    @FXML
    private TableColumn<Product, Integer> pStockCol;
    @FXML
    private TableColumn<Product, Integer> cStockCol;*/
    
    @FXML
    private void goSecurity() throws IOException {
        main.showSecuritySidebar();
    }
    
    @FXML
    private void goWarehouse() throws IOException {
        main.showWarehouseSidebar();
    }
    
    @FXML
    private void goListProduct() throws IOException {
        main.showListProduct();
    }
    
    @FXML
    private void goCreateProduct() throws IOException {
        main.showCreateProduct();
    }
    
    @FXML
    private void goEditProduct() throws IOException {
        main.showEditProduct();
    }
}

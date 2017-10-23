package campis.dp1.controllers;

import javafx.fxml.FXML;
import campis.dp1.Main;
import java.io.IOException;

/**
 *
 * @author Marco
 */
public class MainViewController {
    private Main main;
    @FXML
    private void goSecurity() throws IOException {
        main.showSecuritySidebar();
    }
    
    @FXML
    private void goWarehouse() throws IOException {
        main.showWarehouseSidebar();
    }
    
    @FXML

    private void goWarehouseMainView() throws IOException{
        main.showWarehouseMainView();
    }
    

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

    @FXML
    private void goListUser() throws IOException {
        main.showListUser();
    }

}

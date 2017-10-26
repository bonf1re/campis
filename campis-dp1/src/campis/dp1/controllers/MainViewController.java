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
    private void goDispatch() throws IOException {
        main.showDispatchSidebar();
    }
    
    @FXML
    private void goWarehouse() throws IOException {
        main.showWarehouseSidebar();
    }
    
    @FXML
    private void goWarehouseMainView() throws IOException{
        main.showWarehouseMainView();
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
    private void goListUser() throws IOException {
        main.showListUser();
    }
    
    @FXML
    private void goListVehicle() throws IOException {
        main.showListVehicle();
    }
    
    @FXML
    private void goListRoles() throws IOException {
        main.showListRoles();

    }

}

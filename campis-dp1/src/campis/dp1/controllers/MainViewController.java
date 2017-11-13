package campis.dp1.controllers;

import campis.dp1.ContextFX;
import javafx.fxml.FXML;
import campis.dp1.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


/**
 *
 * @author Marco
 */
public class MainViewController implements Initializable {
    private Main main;
    @FXML
    private Label user_name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            user_name.setText(ContextFX.getInstance().getUser().getFirstname());
        } catch(NullPointerException e) {
        }
    }

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
    private void goCommerce() throws IOException {
        main.showCommerceSidebar();
    }

    @FXML
    private void goReport() throws IOException {
        main.showReportSidebar();
    }
    
    @FXML
    private void goListWarehouse() throws IOException{
        main.showWhList();
    }
    
    @FXML
    private void goCreateWarehouse() throws IOException{
        main.showWhCreate();
    }
    
    @FXML
    private void goEditWarehouse() throws IOException{
        main.showWhEdit();
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
    
    @FXML
    private void goListRequestStatuses() throws IOException {
        main.showListRequestStatuses();
    }

    @FXML
    private void goLogin() throws IOException {
        main.showLogin();
    }

    @FXML
    private void goShowUser() throws IOException {
        ContextFX.getInstance().setId(ContextFX.getInstance().getUser().getId_user());
        main.showViewUser();
    }
}
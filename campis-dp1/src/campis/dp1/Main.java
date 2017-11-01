package campis.dp1;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Marco
 */
public class Main extends Application {
    private Stage primaryStage;
    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CAMPIS");
        showMainView();
        showLogin();
    }
    
    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void showLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/login.fxml"));
        AnchorPane login = loader.load();
        mainLayout.setCenter(login);    
    } 
    
    public static void showTopMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/TopMenu.fxml"));
        AnchorPane securitySidebar = loader.load();
        mainLayout.setTop(securitySidebar);
        mainLayout.setCenter(null);
    }

    public static void showSecuritySidebar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/SecuritySidebar.fxml"));
        VBox securitySidebar = loader.load();
        mainLayout.setLeft(securitySidebar);
        mainLayout.setCenter(null);
    }
    
    public static void showWarehouseSidebar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/WarehouseSidebar.fxml"));
        VBox warehouseSidebar = loader.load();
        mainLayout.setLeft(warehouseSidebar);
        mainLayout.setCenter(null);
    }
    
    public static void showDispatchSidebar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/DispatchSidebar.fxml"));
        VBox dispatchSidebar = loader.load();
        mainLayout.setLeft(dispatchSidebar);
    }
    
    public static void showCommerceSidebar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/CommerceSidebar.fxml"));
        VBox commerceSidebar = loader.load();
        mainLayout.setLeft(commerceSidebar);
        mainLayout.setCenter(null);
    }
    
    /* Warehouse */
    public static void showListWarehouse() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/list.fxml"));
        BorderPane listWarehouse = loader.load();
        mainLayout.setCenter(listWarehouse);  
    }
    
    public static void showNewWarehouse() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/create.fxml"));
        BorderPane newWarehouse = loader.load();
        mainLayout.setCenter(newWarehouse);    
    }
    
     public static void showEditWarehouse() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/edit.fxml"));
        BorderPane editWarehouse = loader.load();
        mainLayout.setCenter(editWarehouse);      
    }
     
     public static void showVisualizeWarehouse() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/visualize.fxml"));
        BorderPane visualizeWarehouse = loader.load();
        mainLayout.setCenter(visualizeWarehouse);       
     }
     
     public static void showWarehouseMoves() throws IOException{
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Main.class.getResource("views/warehouse/moves.fxml"));
         BorderPane warehouseMoves = loader.load();
         mainLayout.setCenter(warehouseMoves);
     }
     
     public static void showWarehouseCreateMove() throws IOException{
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Main.class.getResource("views/warehouse/createmove.fxml"));
         BorderPane creamove = loader.load();
         mainLayout.setCenter(creamove);
     }
     
    /* End Warehouse */

    public static void showListUser() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/users/list.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }
    
    public static void showCreateUser() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/users/create.fxml"));
        BorderPane createUser = loader.load();
        mainLayout.setCenter(createUser);
    }

    public static void showEditUser() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/users/edit.fxml"));
        BorderPane editUser = loader.load();
        mainLayout.setCenter(editUser);
    }

    public static void showViewUser() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/users/show.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }

    public static void showListProduct() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/products/list.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }

    public static void showCreateProduct() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/products/create.fxml"));
        BorderPane createProd = loader.load();
        mainLayout.setCenter(createProd);
    }
    
    public static void showEditProduct() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/products/edit.fxml"));
        BorderPane editProd = loader.load();
        mainLayout.setCenter(editProd);
    }
    
    public static void showViewProduct() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/products/view.fxml"));
        BorderPane viewProd = loader.load();
        mainLayout.setCenter(viewProd);
    }
    
    /* Rack */
    public static void showListRacks() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/racks/list.fxml"));
        AnchorPane listRacks = loader.load();
        mainLayout.setCenter(listRacks);
    }
    
    public static void showNewRack() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/racks/create.fxml"));
        BorderPane newRack = loader.load();
        mainLayout.setCenter(newRack);
    }
    
    public static void showEditRack() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/racks/edit.fxml"));
        BorderPane editRack = loader.load();
        mainLayout.setCenter(editRack);
    }

    /* Roles */
    public static void showListRoles() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/roles/list.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }
    
    public static void showNewRole() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/roles/create.fxml"));
        BorderPane createProd = loader.load();
        mainLayout.setCenter(createProd);

    }
    
    public static void showEditRole() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/roles/edit.fxml"));
        BorderPane createProd = loader.load();
        mainLayout.setCenter(createProd);
    }

    public static void showPermission() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/roles/addPermission.fxml"));
        BorderPane createProd = loader.load();
        mainLayout.setCenter(createProd);
    }
    
    public static void showViewRole() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/roles/show.fxml"));
        BorderPane viewProd = loader.load();
        mainLayout.setCenter(viewProd);
    }

    /* Vehículos */
    
    public static void showListVehicle() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/vehicles/list.fxml"));
        BorderPane listVehicle = loader.load();
        mainLayout.setCenter(listVehicle);
     }
    
    public static void showNewVehicle() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/vehicles/create.fxml"));
        BorderPane newVehicle = loader.load();
        mainLayout.setCenter(newVehicle);
     }
      
    public static void showEditVehicle() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/vehicles/edit.fxml"));
        BorderPane editVehicle = loader.load();
        mainLayout.setCenter(editVehicle);
    }
    public static void showVisualizeEntry() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/entries/view.fxml"));
        BorderPane seeEntry = loader.load();
        mainLayout.setCenter(seeEntry);
     }
    
    public static void showNewEntry() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/entries/create.fxml"));
        BorderPane newEntry = loader.load();
        mainLayout.setCenter(newEntry);
     }
    
    public static void showNewBatch() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/entries/createBatchEntry.fxml"));
        BorderPane newEntry = loader.load();
        mainLayout.setCenter(newEntry);
     }
    
    public static void showListEntries() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/entries/list.fxml"));
        BorderPane listEntries = loader.load();
        mainLayout.setCenter(listEntries);
     }
    
    public static void showListDepartures() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/departures/list.fxml"));
        BorderPane listDepartures = loader.load();
        mainLayout.setCenter(listDepartures);
     }
    public static void showVisualizeDeparture() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/departures/view.fxml"));
        BorderPane seeDeparture = loader.load();
        mainLayout.setCenter(seeDeparture);
     }
    
    public static void showNewDeparture() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/departures/create.fxml"));
        BorderPane newDeparture = loader.load();
        mainLayout.setCenter(newDeparture);
     }
    
    /* BuyOrder */
    
    public static void showListRequestOrder() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/requestOrder/list.fxml"));
        BorderPane listRequestOrder = loader.load();
        mainLayout.setCenter(listRequestOrder);
     }
    
    public static void showCreateRequestOrder() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/requestOrder/create.fxml"));
        BorderPane createRequestOrder = loader.load();
        mainLayout.setCenter(createRequestOrder);
    }
    
    public static void showAddItem() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/requestOrder/addItem.fxml"));
        BorderPane addItem = loader.load();
        mainLayout.setCenter(addItem);
    }
    
    /**  
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   

    public static   void showRouteMove() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/routemove.fxml"));
        BorderPane routeMove = loader.load();
        mainLayout.setCenter(routeMove);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1;

import java.io.IOException;
import javafx.application.Application;
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
    }
    
    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void showSecuritySidebar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/SecuritySidebar.fxml"));
        VBox securitySidebar = loader.load();
        mainLayout.setLeft(securitySidebar);
    }
    
    public static void showWarehouseSidebar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/WarehouseSidebar.fxml"));
        VBox warehouseSidebar = loader.load();
        mainLayout.setLeft(warehouseSidebar);
    }
    

    
    /* Warehouse methods
    */
    public static void showWarehouseMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/MainView.fxml"));
        BorderPane warehouseMainView = loader.load();
        mainLayout.setCenter(warehouseMainView);
        
    }
    
    public static void showWarehouseCreateView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/CreateView.fxml"));
        BorderPane warehouseMainView = loader.load();
        mainLayout.setCenter(warehouseMainView);
        
    }
    
     public static void showWarehouseVisualizeView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/VisualizeView.fxml"));
        BorderPane warehouseMainView = loader.load();
        mainLayout.setCenter(warehouseMainView);
        
    }
    /* End Warehouse methods  */

    public static void showListUser() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/users/list.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }
    
    public static void showCreateUser() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/users/create.fxml"));
        BorderPane createProd = loader.load();
        mainLayout.setCenter(createProd);
    }

    public static void showListProduct() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/products/listProducts.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }

    public static void showCreateProduct() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/products/createProduct.fxml"));
        BorderPane createProd = loader.load();
        mainLayout.setCenter(createProd);
    }
    
    public static void showEditProduct() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/products/editProduct.fxml"));
        BorderPane editProd = loader.load();
        mainLayout.setCenter(editProd);
    }
    
    
            
     public static void showListVehicle() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/vehicles/listVehicle.fxml"));
        BorderPane listVehicle = loader.load();
        mainLayout.setCenter(listVehicle);
    }

    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

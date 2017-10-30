package campis.dp1;

import campis.dp1.models.TabuProblem;
import campis.dp1.models.InputManager;
import campis.dp1.models.Coordinates;
import campis.dp1.models.TabuSolution;
import campis.dp1.services.TabuSearchService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    public static void showDispatchSidebar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/DispatchSidebar.fxml"));
        VBox dispatchSidebar = loader.load();
        mainLayout.setLeft(dispatchSidebar);
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
        BorderPane listRacks = loader.load();
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
    
    /**  
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	InputManager inputM = new InputManager();
        Integer[][] mapa = new Integer[60][30];

        try {
            mapa = inputM.cargarMatriz("map.txt");
        } catch (IOException ex) {
            out.println("Error con el archivo map.txt!");
        }

        out.println("==================");
        out.println("MAPA DEL DEPOSITO:");

        for (int i = 0; i < 30; i++) {
            out.println(Arrays.toString(mapa[i]));
        }
        
        ArrayList<Coordinates> orden = new ArrayList<Coordinates>();

        try {
            orden = inputM.cargarOrden("coordinates.txt");
        } catch (IOException ex) {
            out.println("Error con el archivo de orden!");
        }

        out.println("==================");
        out.println("ORDEN A ATENDER:");

        for (int i = 0; i < 50; i++) {
            out.println(orden.get(i).stringFormat());
        }

        TabuProblem problema = new TabuProblem(mapa);
        TabuSearchService busqueda = new TabuSearchService();
        TabuSolution mejor = busqueda.search(problema, orden);
        out.println("==================");
        out.println("SOLUCIÓN FINAL:"); 
        mejor.printOrder();

        try {
            PrintWriter output = new PrintWriter("output.txt", "UTF-8");
            output.println("Archivo: output.txt");
            output.println("Distancia: " + problema.distance(mejor));
            output.close();
        } catch (IOException e) {
            out.println("Error al imprimir resultados!");
        }

    	launch(args);
    }   
}

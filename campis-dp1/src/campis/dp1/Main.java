package campis.dp1;

import java.io.IOException;
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
        Locale.setDefault(Locale.US);
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
        mainLayout.setTop(null);
        mainLayout.setLeft(null);
    } 
    
    public static void showTopMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/TopMenu.fxml"));
        AnchorPane sidebar = loader.load();
        mainLayout.setTop(sidebar);
        
        //mainLayout.setCenter(null);
        // TODO
        if (true) showBulkLoad();
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
        mainLayout.setCenter(null);
    }
    
    public static void showCommerceSidebar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/CommerceSidebar.fxml"));
        VBox commerceSidebar = loader.load();
        mainLayout.setLeft(commerceSidebar);
        mainLayout.setCenter(null);
    }
    
    public static void showReportSidebar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/layouts/ReportSidebar.fxml"));
        VBox reportSidebar = loader.load();
        mainLayout.setLeft(reportSidebar);
        mainLayout.setCenter(null);
    }
    
    /* Warehouse */
    public static void showWhList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/whlist.fxml"));
        BorderPane listWarehouse = loader.load();
        mainLayout.setCenter(listWarehouse);  
    }
    
    public static void showWhCreate() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/whcreate.fxml"));
        BorderPane newWarehouse = loader.load();
        mainLayout.setCenter(newWarehouse);    
    }
    
     public static void showWhEdit() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/whedit.fxml"));
        BorderPane editWarehouse = loader.load();
        mainLayout.setCenter(editWarehouse);      
    }
     
     public static void showWhVisualize() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/whvisualize.fxml"));
        BorderPane visualizeWarehouse = loader.load();
        mainLayout.setCenter(visualizeWarehouse);       
     }
     
     public static void showWhEntryMoveList() throws IOException{
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Main.class.getResource("views/warehouse/entrymovelist.fxml"));
         BorderPane warehouseMoves = loader.load();
         mainLayout.setCenter(warehouseMoves);
     }
     
     public static void showWhEntryMoveNormalCreate() throws IOException{
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Main.class.getResource("views/warehouse/entrymovenormalcreate.fxml"));
         BorderPane creamove = loader.load();
         mainLayout.setCenter(creamove);
     }
     
     public static void showWhEntryMoveSpecialCreate() throws IOException{
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Main.class.getResource("views/warehouse/entrymovespecialcreate.fxml"));
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

    public static void showChangePassUser() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/users/changePassword.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }

    public static void showLoggedUser() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/users/loggedUser.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }
    
    public static void showAreaCreate() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/areacreate.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }
    
    public static void showAreaEdit() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/areaedit.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }
    
    public static void showAreaList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/arealist.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }
    
    public static void showCreateProductType() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/productTypes/create.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }
    
    public static void showListProductType() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/productTypes/list.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }
    
    public static void showEditProductType() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/productTypes/edit.fxml"));
        BorderPane editProd = loader.load();
        mainLayout.setCenter(editProd);
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
    
    public static void showListRequestStatuses() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/requestStatus/list.fxml"));
        BorderPane listRequestStatus = loader.load();
        mainLayout.setCenter(listRequestStatus);
    }

    public static void showEditRequestStatuses() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/requestStatus/edit.fxml"));
        BorderPane editRequestStatus = loader.load();
        mainLayout.setCenter(editRequestStatus);
    }
    
    /* Freights */
    
    public static void showListFreights() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/freights/list.fxml"));
        BorderPane listProd = loader.load();
        mainLayout.setCenter(listProd);
    }
    
    public static void showEditFreight() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/freights/edit.fxml"));
        BorderPane editFreight = loader.load();
        mainLayout.setCenter(editFreight);
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
    
    /* Entries */
    
    public static void showVisualizeEntry() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/viewEntry.fxml"));
        BorderPane seeEntry = loader.load();
        mainLayout.setCenter(seeEntry);
     }
    
    public static void showNewEntry() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/createEntry.fxml"));
        BorderPane newEntry = loader.load();
        mainLayout.setCenter(newEntry);
     }
    
    public static void showNewBatch() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/createBatchEntry.fxml"));
        BorderPane newEntry = loader.load();
        mainLayout.setCenter(newEntry);
     }
    
    public static void showListEntries() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/listEntry.fxml"));
        BorderPane listEntries = loader.load();
        mainLayout.setCenter(listEntries);
     }
    
    /* Departures */
    
    public static void showListDepartures() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/list.fxml"));
        BorderPane listDepartures = loader.load();
        mainLayout.setCenter(listDepartures);
     }
    public static void showVisualizeDeparture() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/view.fxml"));
        BorderPane seeDeparture = loader.load();
        mainLayout.setCenter(seeDeparture);
     }
    
    public static void showNewDeparture() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/specialCreate.fxml"));
        BorderPane newDeparture = loader.load();
        mainLayout.setCenter(newDeparture);
     }
    
    public static void showNewNormalDeparture() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/normalCreate.fxml"));
        BorderPane newNormalDeparture = loader.load();
        mainLayout.setCenter(newNormalDeparture);
     }
    
    /*Deliveries*/
     public static void showDeliveryList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/listDeliveries.fxml"));
        BorderPane newNormalDeparture = loader.load();
        mainLayout.setCenter(newNormalDeparture);
     }
     
     public static void showCreateDelivery() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/createDelivery.fxml"));
        BorderPane newNormalDeparture = loader.load();
        mainLayout.setCenter(newNormalDeparture);
     }
     
     public static void showEditDelivery() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/editDelivery.fxml"));
        BorderPane newNormalDeparture = loader.load();
        mainLayout.setCenter(newNormalDeparture);
     }
     
    /*Batches x dispatch*/
    public static void showListDispatchBatch() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/listDispatchBatch.fxml"));
        BorderPane listDepartures = loader.load();
        mainLayout.setCenter(listDepartures);
     }
    
    /* RequestOrder */
    
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

    public static void showListClient() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/clients/list.fxml"));
        BorderPane listClient = loader.load();
        mainLayout.setCenter(listClient);  
    }
    
    public static void showCreateClient() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/clients/create.fxml"));
        BorderPane newClient = loader.load();
        mainLayout.setCenter(newClient);    
    }

    public static void showEditClient() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/clients/edit.fxml"));
        BorderPane newClient = loader.load();
        mainLayout.setCenter(newClient);    
    }

    public static void showViewClient() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/clients/show.fxml"));
        BorderPane newClient = loader.load();
        mainLayout.setCenter(newClient);    
    }

    public static void showAddItem2() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/requestOrder/addItem2.fxml"));
        BorderPane addItem2 = loader.load();
        mainLayout.setCenter(addItem2);
    }
    
    public static void showEditRequestOrder() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/requestOrder/edit.fxml"));
        BorderPane editRequestOrder = loader.load();
        mainLayout.setCenter(editRequestOrder);
    }
    
    public static void showViewRequest() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/requestOrder/view.fxml"));
        BorderPane RequestOrder = loader.load();
        mainLayout.setCenter(RequestOrder);
    }
    

    public static void showListSaleConditions() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/saleConditions/list.fxml"));
        BorderPane saleConditions = loader.load();
        mainLayout.setCenter(saleConditions);
    }
    
    public static void showNewSaleCondition() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/saleConditions/create.fxml"));
        BorderPane newClient = loader.load();
        mainLayout.setCenter(newClient);    
    }

    public static void showEditSaleCondition() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/saleConditions/edit.fxml"));
        BorderPane newClient = loader.load();
        mainLayout.setCenter(newClient);    
    }

    public static void showViewSaleCondition() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/saleConditions/show.fxml"));
        BorderPane newClient = loader.load();
        mainLayout.setCenter(newClient);    
    }
    
    

    public static void showListComplaint() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/complaints/list.fxml"));
        BorderPane listComplaint = loader.load();
        mainLayout.setCenter(listComplaint);  
    }

    public static void showCreateComplaint() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/complaints/create.fxml"));
        BorderPane newComplaint = loader.load();
        mainLayout.setCenter(newComplaint);    
    }

    public static void goAttendComplaint() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/complaints/attend_complaint.fxml"));
        BorderPane newComplaint = loader.load();
        mainLayout.setCenter(newComplaint);    
    }

    public static void showListRefund() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/refunds/list.fxml"));
        BorderPane listComplaint = loader.load();
        mainLayout.setCenter(listComplaint);  
    }
    
    public static void showCreateRefund() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/refunds/create.fxml"));
        BorderPane newRefund = loader.load();
        mainLayout.setCenter(newRefund);    
    }
    
    public static void showEditRefund() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/refunds/edit.fxml"));
        BorderPane newClient = loader.load();
        mainLayout.setCenter(newClient);    
    }
    
    public static void showViewRefund() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/refunds/view.fxml"));
        BorderPane newClient = loader.load();
        mainLayout.setCenter(newClient);    
    }

    /* End RequestOrder*/
    
    /* Suppliers */
    public static void showListSupplier() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/suppliers/list.fxml"));
        BorderPane listSupplier = loader.load();
        mainLayout.setCenter(listSupplier);  
    }
    
    public static void showSupplierCreate() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/suppliers/create.fxml"));
        BorderPane newSupplier = loader.load();
        mainLayout.setCenter(newSupplier);    
    }
    
     public static void showSupplierEdit() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/suppliers/edit.fxml"));
        BorderPane editSupplier = loader.load();
        mainLayout.setCenter(editSupplier);      
    }
     
     public static void showSupplierVisualize() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/suppliers/view.fxml"));
        BorderPane viewSupplier = loader.load();
        mainLayout.setCenter(viewSupplier);       
     }
     /* End Suppliers */
     
     /* Invoices */
     
     public static void showListInvoice() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/invoices/listInvoice.fxml"));
        BorderPane listSupplier = loader.load();
        mainLayout.setCenter(listSupplier);  
    }
     
     public static void showCreateInvoice() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/invoices/createInvoice.fxml"));
        BorderPane listSupplier = loader.load();
        mainLayout.setCenter(listSupplier);  
    }
     
    public static void showCreateInvoice2() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/invoices/createInvoice2.fxml"));
        BorderPane listSupplier = loader.load();
        mainLayout.setCenter(listSupplier);  
    }
     
     /* End Invoices */
    
    /**  
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   

    public static  void showWhEntryMoveRoute() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/entrymoveroute.fxml"));
        BorderPane routeMove = loader.load();
        mainLayout.setCenter(routeMove);
    }
    
    public static  void showWhSpecialEntryMoveRoute() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/specialentrymoveroute.fxml"));
        BorderPane routeMove = loader.load();
        mainLayout.setCenter(routeMove);
    }

    public static void showWhDepartureMoveCreate() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/departuremovecreate.fxml"));
        BorderPane routeMove = loader.load();
        mainLayout.setCenter(routeMove);
    }

    public static void showWhDepartureMoveList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/departuremovelist.fxml"));
        BorderPane routeMove = loader.load();
        mainLayout.setCenter(routeMove);
    }

    public static void showWhEntryMoveAddProd() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/entryMoveSpecialAddItem.fxml"));
        BorderPane addItem = loader.load();
        mainLayout.setCenter(addItem);
    }
    
    public static void showKardexReportConf() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/reports/KardexConf.fxml"));
        BorderPane addItem = loader.load();
        mainLayout.setCenter(addItem);
    }

            
    public static void showGenerateKardexReport() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/reports/KardexReport.fxml"));
        BorderPane addItem = loader.load();
        mainLayout.setCenter(addItem);
    }

    public static void showExpirationReportConf() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/reports/ExpirationConfig.fxml"));
        BorderPane addItem = loader.load();
        mainLayout.setCenter(addItem);
    }

    public static void showGenerateExpirationReport() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/reports/ExpirationReport.fxml"));
        BorderPane addItem = loader.load();
        mainLayout.setCenter(addItem);
    }

    public static void showBillList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/reports/ListRequestOrder.fxml"));
        BorderPane addItem = loader.load();
        mainLayout.setCenter(addItem);
    }

    public static void showBillDetail() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/reports/showBill.fxml"));
        BorderPane addItem = loader.load();
        mainLayout.setCenter(addItem);
    }
    
    public static void showListCampaigns() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/campaigns/list.fxml"));
        BorderPane listCampaigns = loader.load();
        mainLayout.setCenter(listCampaigns);
    }
    
    public static void showCreateCampaign() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/campaigns/create.fxml"));
        BorderPane newCampaign = loader.load();
        mainLayout.setCenter(newCampaign);
    }
    
    public static void showEditCampaign() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/campaigns/edit.fxml"));
        BorderPane campaign = loader.load();
        mainLayout.setCenter(campaign);
    }
    
    public static void showViewCampaign() throws IOException {

    }
    
    public static void showStocksReportConf() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/reports/StocksConfig.fxml"));
        BorderPane campaign = loader.load();
        mainLayout.setCenter(campaign);
    }
    
    
    
    public static void showGenerateStocksReport() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/reports/StocksReport.fxml"));
        BorderPane campaign = loader.load();
        mainLayout.setCenter(campaign);
    }

    public static void showWhDepartureAddItem() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/whdeparturemoveadditem.fxml"));
        BorderPane addItem = loader.load();
        mainLayout.setCenter(addItem);
    }

    public static void showWhDepartureMoveRoute() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/departuremoveroute.fxml"));
        BorderPane routing = loader.load();
        mainLayout.setCenter(routing);
    }
    
    public static void showCurrencyIGV() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/currencyIGV/edit.fxml"));
        BorderPane currency = loader.load();
        mainLayout.setCenter(currency);
    }
    
    public static void showBulkLoad() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/bulkLoad/execute.fxml"));
        BorderPane ex = loader.load();
        mainLayout.setCenter(ex);
    }

    public static void showEntryMoveList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/entrymovelist.fxml"));
        BorderPane entries = loader.load();
        mainLayout.setCenter(entries);
    }

    public static void showSelectRequestOrder() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/selectRequestOrder.fxml"));
        BorderPane selectreqorder = loader.load();
        mainLayout.setCenter(selectreqorder);
    }


    public static void showDispatchMoveRoute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void showDispatchMoveCreate() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/dispatch/dispatchmovecreate.fxml"));
        BorderPane selectreqorder = loader.load();
        mainLayout.setCenter(selectreqorder);
    }

    public static void showSelectRefund4Entry() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/selectRefund.fxml"));
        BorderPane selectreqorder = loader.load();
        mainLayout.setCenter(selectreqorder);
    }

    public static void showRefundMoveCreate() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/warehouse/createRefundEntry.fxml"));
        BorderPane selectreqorder = loader.load();
        mainLayout.setCenter(selectreqorder);
    }

    
}

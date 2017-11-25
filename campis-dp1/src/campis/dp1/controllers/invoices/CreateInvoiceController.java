/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.invoices;

import campis.dp1.Main;
import campis.dp1.ContextFX;
import com.itextpdf.text.Document;
import campis.dp1.models.Client;
import java.io.File;
import campis.dp1.models.District;
import java.io.FileOutputStream;
import campis.dp1.models.DispatchOrder;
import campis.dp1.controllers.suppliers.ListController;
import campis.dp1.models.Invoice;
import campis.dp1.models.InvoiceLine;
import campis.dp1.models.Delivery;
import campis.dp1.models.Product;
import campis.dp1.models.UnitOfMeasure;
import campis.dp1.models.DispatchOrderLine;
import campis.dp1.models.DispatchOrderLineDisplay;
import campis.dp1.models.Parameters;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.RequestOrderLine;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author Gina Bustamante
 */
public class CreateInvoiceController implements Initializable {
    private Main main;
    
    @FXML
    private Label type_message;
    @FXML
    private Label del_message;

    @FXML
    private JFXComboBox<String> deliveryCb;
    
    private ObservableList<Delivery> deliveries;
    private ObservableList<DispatchOrderLine> do_lines;
    private ObservableList<DispatchOrderLineDisplay>  delieveriesView;
    private Integer selected_del; 
    private Integer selected_do; 
    private Integer selected_ro; 
    private String selected_type; 
    private Integer aux_id_invoice;
    
    @FXML
    private TableView<DispatchOrderLineDisplay> tableDelivery;
    @FXML
    private TableColumn<DispatchOrderLineDisplay, Integer> idCol;
    @FXML
    private TableColumn<DispatchOrderLineDisplay, String> prodCol;
    @FXML
    private TableColumn<DispatchOrderLineDisplay, Integer> cantCol;
    @FXML
    private TableColumn<DispatchOrderLineDisplay, String> unitCol;
    @FXML
    private TableColumn<DispatchOrderLineDisplay, Float> weigthCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpDeliveryCb();      
        setupDeliveriesTable();
        
        deliveryCb.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> options, String oldValue, String newValue) -> {
            System.out.println(newValue);
            this.selected_del = Integer.parseInt(newValue);     
            
            try {
                loadData_deliveries();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
                
    }
    
    private void setupDeliveriesTable() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().id_dispatch_order_lineProperty().asObject());
        prodCol.setCellValueFactory(cellData -> cellData.getValue().product_nameProperty());
        cantCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        unitCol.setCellValueFactory(cellData -> cellData.getValue().unit_of_measure_nameProperty());
        weigthCol.setCellValueFactory(cellData -> cellData.getValue().weigthProperty().asObject());       
    }
    
    private void loadData_deliveries() throws SQLException, ClassNotFoundException {
        //Obtenemos el dispatch order
        for (int i = 0; i < this.deliveries.size(); i++) {
            if (Objects.equals(this.selected_del, this.deliveries.get(i).getId_delivery())) {
                this.selected_do = this.deliveries.get(i).getId_dispatch_order();
            }
        }
       
        //Obtenemos las lineas de las ordenes de despacho
        this.do_lines = getDispatchOrderLines(this.selected_do);
        
        //llenamos la tabla con las líneas de la orden de despacho
        delieveriesView = FXCollections.observableArrayList();
        
        for (int i = 0; i < this.do_lines.size(); i++) {
            DispatchOrderLine aux = this.do_lines.get(i);
            
            Product aux_prod = Product.getProduct(aux.getId_prod());
            
            String nombProd = aux_prod.getName();
            Float w = aux_prod.getWeight()*aux.getQuantity();
            Integer unid  = aux_prod.getId_unit_of_measure();
            String unid_name = UnitOfMeasure.getName(unid);
            
            DispatchOrderLineDisplay d = new DispatchOrderLineDisplay(aux.getId_dispatch_order_line(), 
                                                                     aux.getId_dispatch_order(), 
                                                                     aux.getId_prod(), nombProd, aux.getQuantity(), 
                                                                     unid, unid_name, w, aux.isDelivered());

            
            delieveriesView.add(d);
        }
        
        this.tableDelivery.setItems(null);
        this.tableDelivery.setItems(delieveriesView);
    }    
    
    
     private ObservableList<DispatchOrderLine> getDispatchOrderLines(int aux_id_do) {
         //Enlistamos las dispatch order lines
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr3 = "SELECT * FROM campis.dispatch_order_line WHERE id_dispatch_order = " + aux_id_do;
        SQLQuery qry3 = session.createSQLQuery(qryStr3);
        List<Object[]> rows3 = qry3.list();
        ObservableList<DispatchOrderLine> do_lines = FXCollections.observableArrayList();

        for (Object[] row : rows3) {
            DispatchOrderLine sup = new DispatchOrderLine(Integer.parseInt(row[0].toString()),
                                        Integer.parseInt(row[1].toString()),
                                        Integer.parseInt(row[2].toString()),
                                        Integer.parseInt(row[3].toString()));
            do_lines.add(sup);

        }
        
        session.close();
        sessionFactory.close();
        
        return do_lines;
     }
     
    @FXML
    private void setUpDeliveryCb() {
       this.deliveries = getDeliveries();
       
       System.out.println("campis.dp1.controllers.invoices.CreateInvoiceController.setUpDeliveryCb()");
       System.out.println(deliveries.size());
        
       for (int i = 0; i < deliveries.size(); i++) {
           deliveryCb.getItems().add(this.deliveries.get(i).getId_delivery().toString());
       }

    }
    
    private ObservableList<Delivery> getDeliveries() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT * FROM campis.delivery;";
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        ObservableList<Delivery> returnable = FXCollections.observableArrayList();
        
        for (Object[] row : rows) {
            // Verify that it does not have a invoice already created
            int id_dispatch_order = (int) row[3];
            
            if (session.createSQLQuery("SELECT  * FROM campis.invoice WHERE id_dispatch_order = "+id_dispatch_order).list().size()>0)
                continue;

            Delivery sup = new Delivery(Integer.parseInt(row[0].toString()),
                                        row[1].toString(),
                                        row[2].toString(),
                                        Integer.parseInt(row[3].toString()));
            returnable.add(sup);
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

    @FXML
    private void goListInvoices(ActionEvent event) throws IOException {
        main.showListInvoice();
    }

            
    public boolean validation() {

        boolean delinValid = this.selected_del == 0;       
        
        del_message.setText(" ");

        if (delinValid) {
            del_message.setText("Campo obligatorio");
        }

        return (!delinValid);
    }
    
    @FXML
    private void goListInvoices2(ActionEvent event) throws IOException {
        if (this.validation()){
            ContextFX.getInstance().setSelected_del(selected_del);
            ContextFX.getInstance().setSelected_do(selected_do);

            main.showCreateInvoice2();
        }        
    }
    
}

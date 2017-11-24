/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.invoices;

import campis.dp1.Main;
import campis.dp1.controllers.suppliers.ListController;
import campis.dp1.models.Invoice;
import campis.dp1.models.InvoiceDisplay;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class ListInvoiceController implements Initializable {
    private Main main;
    
    private ObservableList<Invoice> invoices;
    private ObservableList<InvoiceDisplay> invoicesView;
    
    @FXML
    private TableView<InvoiceDisplay> tableInvoice;
    @FXML
    private TableColumn<InvoiceDisplay, Integer> idCol;
    @FXML
    private TableColumn<InvoiceDisplay, Integer> id_doCol;
    @FXML
    private TableColumn<InvoiceDisplay, Integer> typeCol;
    @FXML
    private TableColumn<InvoiceDisplay, Double> totalCol;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            idCol.setCellValueFactory(cellData -> cellData.getValue().getId_invoice().asObject());
            id_doCol.setCellValueFactory(cellData -> cellData.getValue().getId_dispatch_order().asObject());
            typeCol.setCellValueFactory(cellData -> cellData.getValue().getId_type().asObject());
            totalCol.setCellValueFactory(cellData -> cellData.getValue().getTotal().asObject());
 
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        this.invoices = FXCollections.observableArrayList();
        this.invoicesView = FXCollections.observableArrayList();
        
        this.invoices = getInvoices();
        
        for (int i = 0; i < this.invoices.size(); i++) {
            InvoiceDisplay supplier = new InvoiceDisplay(invoices.get(i).getId_invoice(), 
                                                           invoices.get(i).getId_dispatch_order(),
                                                           invoices.get(i).getId_type(), 
                                                           invoices.get(i).getTotal());
            this.invoicesView.add(supplier);
        }
        
        this.tableInvoice.setItems(null);
        this.tableInvoice.setItems(invoicesView);
    }   
    
    private ObservableList<Invoice> getInvoices() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT * FROM campis.invoice;";
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        ObservableList<Invoice> returnable = FXCollections.observableArrayList();
        
        for (Object[] row : rows) {
            Invoice sup = new Invoice(Integer.parseInt(row[0].toString()),
                                      Integer.parseInt(row[1].toString()),
                                      Integer.parseInt(row[2].toString()),
                                      Double.parseDouble(row[3].toString()));
            returnable.add(sup);
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }
    
    @FXML
    private void deleteInvoice(int id) throws IOException {
//        Configuration configuration = new Configuration();
//        configuration.configure("hibernate.cfg.xml");
//        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        String qryStr = "DELETE FROM campis.delivery WHERE id_delivery=" + id;
//        SQLQuery qry = session.createSQLQuery(qryStr);
//        qry.executeUpdate();
//        session.getTransaction().commit();
//        session.close();
//        sessionFactory.close();
    }
    
    @FXML
    private void goDeleteInvoice(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
//        if (selected_id > 0) {
//            deleteDelivery(this.selected_id);       
//            loadData();
//        }
    }
    
    @FXML
    private void goCreateInvoice(ActionEvent event) throws IOException {
        main.showCreateInvoice();
    }

}
